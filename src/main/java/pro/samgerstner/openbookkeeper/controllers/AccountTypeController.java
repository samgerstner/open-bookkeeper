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
import pro.samgerstner.openbookkeeper.entities.AccountType;
import pro.samgerstner.openbookkeeper.entities.repositories.AccountTypeRepository;
import org.springframework.data.domain.Sort.Order;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/account-types")
public class AccountTypeController
{
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
      model.addAttribute("accountType", new AccountType());
      return "account_type_create";
   }

   @PostMapping(value = "/create")
   public String postCreate(@ModelAttribute AccountType accountType)
   {
      accountTypeRepository.save(accountType);
      return "redirect:/account-types/view";
   }

   @GetMapping(value = "/edit")
   public String getEdit(@RequestParam int id, Model model)
   {
      Optional<AccountType> optional = accountTypeRepository.findById(id);
      if(!optional.isPresent())
      {
         return "redirect:/account-types/bad-request";
      }

      model.addAttribute("accountType", optional.get());
      return "account_type_edit";
   }

   @PostMapping(value = "/edit")
   public String postEdit(@RequestParam int id, @ModelAttribute AccountType formAccountType)
   {
      Optional<AccountType> optional = accountTypeRepository.findById(id);
      if(!optional.isPresent())
      {
         return "redirect:/account-types/bad-request";
      }

      AccountType accountType = optional.get();
      accountType.setType(formAccountType.getType());
      accountTypeRepository.save(accountType);
      System.out.println(accountType.getId());
      return "redirect:/account-types/view";
   }

   @GetMapping(value = "/view")
   public String view(@RequestParam(required = false) String search, @RequestParam(defaultValue = "1") int page,
                      @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id,asc") String[] sort,
                      Model model)
   {
      String sortField = sort[0];
      String sortDirection = sort[1];
      Sort.Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
      Order order = new Order(direction, sortField);
      Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));

      Page<AccountType> pageAccountType;
      if(search == null)
      {
         pageAccountType = accountTypeRepository.findAllPageable(pageable);
      }
      else
      {
         pageAccountType = accountTypeRepository.findByType(search, pageable);
         model.addAttribute("search", search);
      }

      List<AccountType> accountTypes = pageAccountType.getContent();
      model.addAttribute("accountTypes", accountTypes);
      model.addAttribute("currentPage", pageAccountType.getNumber() + 1);
      model.addAttribute("totalItems", pageAccountType.getTotalElements());
      model.addAttribute("totalPages", pageAccountType.getTotalPages());
      model.addAttribute("pageSize", size);
      model.addAttribute("sortField", sortField);
      model.addAttribute("sortDirection", sortDirection);
      model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
      return "account_type_view";
   }

   @GetMapping(value = "/delete")
   public String getDelete(@RequestParam int id, Model model)
   {
      Optional<AccountType> optional = accountTypeRepository.findById(id);
      if(!optional.isPresent())
      {
         return "redirect:/account-types/bad-request";
      }

      model.addAttribute("accountType", optional.get());
      return "account_type_delete";
   }

   @PostMapping(value = "/delete")
   public String postDelete(@RequestParam int id)
   {
      Optional<AccountType> optional = accountTypeRepository.findById(id);
      if(!optional.isPresent())
      {
         return "redirect:/account-types/bad-request";
      }

      accountTypeRepository.deleteById(id);
      return "redirect:/account-types/view";
   }
}