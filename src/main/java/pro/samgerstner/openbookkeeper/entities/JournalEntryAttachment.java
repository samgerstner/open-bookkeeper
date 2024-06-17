package pro.samgerstner.openbookkeeper.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "journal_entry_attachments")
public class JournalEntryAttachment
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;

   @Column(name = "file_name", nullable = false)
   private String fileName;

   @Column(name = "file_type", nullable = false)
   private String fileType;

   @Column(name = "download_nonce")
   private String downloadNonce;

   @ManyToOne
   private JournalEntry journalEntry;

   public JournalEntryAttachment()
   {
   }

   public JournalEntryAttachment(String fileName, String fileType, String downloadNonce, JournalEntry journalEntry)
   {
      this.fileName = fileName;
      this.fileType = fileType;
      this.downloadNonce = downloadNonce;
      this.journalEntry = journalEntry;
   }

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public String getFileName()
   {
      return fileName;
   }

   public void setFileName(String fileName)
   {
      this.fileName = fileName;
   }

   public String getFileType()
   {
      return fileType;
   }

   public void setFileType(String fileType)
   {
      this.fileType = fileType;
   }

   public String getDownloadNonce()
   {
      return downloadNonce;
   }

   public void setDownloadNonce(String downloadNonce)
   {
      this.downloadNonce = downloadNonce;
   }

   public JournalEntry getJournalEntry()
   {
      return journalEntry;
   }

   public void setJournalEntry(JournalEntry journalEntry)
   {
      this.journalEntry = journalEntry;
   }
}