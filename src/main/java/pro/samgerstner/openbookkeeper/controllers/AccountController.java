package pro.samgerstner.openbookkeeper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.samgerstner.openbookkeeper.entities.Account;
import pro.samgerstner.openbookkeeper.entities.repositories.AccountRepository;
import pro.samgerstner.openbookkeeper.entities.repositories.AccountTypeRepository;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/accounts")
public class AccountController
{
   @Autowired
   private AccountRepository accountRepository;

   @Autowired
   private AccountTypeRepository accountTypeRepository;

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public String badRequest()
   {
      return "Bad request";
   }

   @GetMapping(value = "/create")
   public String getCreate(Model model)
   {
      model.addAttribute("account", new Account());
      model.addAttribute("types", accountTypeRepository.findAll());
      return "account_create";
   }

   @PostMapping(value = "/create")
   public String postCreate(@ModelAttribute Account account)
   {
      accountRepository.save(account);
      return "redirect:/accounts/view";
   }

   @GetMapping(value = "/edit")
   public String getEdit(@RequestParam int id, Model model)
   {
      Optional<Account> accountOptional = accountRepository.findById(id);
      if(!accountOptional.isPresent())
      {
         return "redirect:/accounts/bad-request";
      }

      model.addAttribute("account", accountOptional.get());
      model.addAttribute("types", accountTypeRepository.findAll());
      return "account_edit";
   }

   @PostMapping(value = "/edit")
   public String postEdit(@RequestParam int id, @ModelAttribute Account formAccount)
   {
      Optional<Account> accountOptional = accountRepository.findById(id);
      if(!accountOptional.isPresent())
      {
         return "redirect:/accounts/bad-request";
      }

      formAccount.setAccountType(accountOptional.get().getAccountType());
      formAccount.setAccountName(formAccount.getAccountName());
      formAccount.setAccountType(formAccount.getAccountType());
      accountRepository.save(formAccount);
      return "redirect:/accounts/view";
   }

   @GetMapping(value = "/view")
   public String view(@RequestParam(required = false) String search, @RequestParam(defaultValue = "1") int page,
                      @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id,asc") String[] sort,
                      Model model)
   {
      String sortField = sort[0];
      String sortDirection = sort[1];
      Sort.Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
      Sort.Order order = new Sort.Order(direction, sortField);
      Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));

      Page<Account> pageAccounts;
      if(search == null)
      {
         pageAccounts = accountRepository.findAllPageable(pageable);
      }
      else
      {
         pageAccounts = accountRepository.findByNumberOrName(search, pageable);
         model.addAttribute("search", search);
      }

      List<Account> accounts = pageAccounts.getContent();
      model.addAttribute("accounts", accounts);
      model.addAttribute("currentPage", pageAccounts.getNumber() + 1);
      model.addAttribute("totalItems", pageAccounts.getTotalElements());
      model.addAttribute("totalPages", pageAccounts.getTotalPages());
      model.addAttribute("pageSize", size);
      model.addAttribute("sortField", sortField);
      model.addAttribute("sortDirection", sortDirection);
      model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
      return "account_view";
   }

   @GetMapping(value = "/delete")
   public String getDelete(@RequestParam int id, Model model)
   {
      Optional<Account> accountOptional = accountRepository.findById(id);
      if(!accountOptional.isPresent())
      {
         return "redirect:/accounts/bad-request";
      }

      model.addAttribute("account", accountOptional.get());
      return "account_delete";
   }

   @PostMapping(value = "/delete")
   public String postDelete(@RequestParam int id)
   {
      Optional<Account> accountOptional = accountRepository.findById(id);
      if(!accountOptional.isPresent())
      {
         return "redirect:/accounts/bad-request";
      }

      accountRepository.deleteById(id);
      return "redirect:/accounts/view";
   }
}