package pro.samgerstner.openbookkeeper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.samgerstner.openbookkeeper.S3Helper;
import pro.samgerstner.openbookkeeper.entities.JournalEntry;
import pro.samgerstner.openbookkeeper.entities.JournalEntryAttachment;
import pro.samgerstner.openbookkeeper.entities.JournalEntryRow;
import pro.samgerstner.openbookkeeper.entities.JournalEntryRowDTO;
import pro.samgerstner.openbookkeeper.entities.repositories.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/journals")
public class JournalController
{
   @Autowired
   private JournalEntryRepository journalEntryRepository;

   @Autowired
   private JournalEntryRowRepository journalEntryRowRepository;

   @Autowired
   private JournalEntryAttachmentRepository journalEntryAttachmentRepository;

   @Autowired
   private AccountRepository accountRepository;

   @Autowired
   private VendorRepository vendorRepository;

   @Value("${aws.access-key}")
   private String awsAccessKey;

   @Value("${aws.secret-key}")
   private String awsSecretKey;

   @Value("${aws.region}")
   private String awsRegion;

   @Value("${aws.bucket-name}")
   private String awsBucketName;

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public String badRequest()
   {
      return "Bad request";
   }

   @PostMapping(value = "/create")
   public String postCreate(@ModelAttribute JournalEntry entry, @RequestParam MultipartFile[] files) throws IOException
   {
      //Save journal entry to database
      journalEntryRepository.save(entry);

      //Save journal entry rows to database
      List<JournalEntryRow> rows = entry.getEntryRows();
      for(JournalEntryRow row : rows)
      {
         row.setJournal(entry);
      }
      journalEntryRowRepository.saveAll(entry.getEntryRows());

      //Generate attachment objects and call S3 helper
      S3Helper s3 = new S3Helper(awsAccessKey, awsSecretKey, awsRegion, awsBucketName);
      s3.createJournalEntryFolder(entry.getId());
      List<JournalEntryAttachment> attachments = new ArrayList<>();

      for(MultipartFile file : files)
      {
         s3.uploadJournalEntryAttachment(entry.getId(), file.getName(), file.getBytes());
         journalEntryAttachmentRepository.save(new JournalEntryAttachment(file.getName(), file.getContentType(), s3.generateDownloadNonce(), entry));
      }

      return "journal_view";
   }

   @GetMapping(value = "/create-rows")
   public String getCreateRows(Model model)
   {
      JournalEntryRowDTO rowsForm = new JournalEntryRowDTO();
      for(int index = 0; index < 10; index++)
      {
         rowsForm.addRow(new JournalEntryRow());
      }

      if(accountRepository.count() == 0)
      {
         return "redirect:/accounts/create";
      }

      if(vendorRepository.count() == 0)
      {
         return "redirect:/vendors/create";
      }

      model.addAttribute("rows", rowsForm);
      model.addAttribute("accounts", accountRepository.findAll());
      model.addAttribute("vendors", vendorRepository.findAll());
      return "journal_create_rows";
   }

   @PostMapping(value = "/create-rows")
   public String postCreateRows(@ModelAttribute JournalEntryRowDTO rows, Model model)
   {
      JournalEntry entry = new JournalEntry();
      entry.setEntryRows(rows.getRows());
      model.addAttribute("journalEntry", entry);
      return "journal_create";
   }
}