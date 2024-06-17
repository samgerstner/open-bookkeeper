package pro.samgerstner.openbookkeeper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pro.samgerstner.openbookkeeper.entities.Vendor;
import pro.samgerstner.openbookkeeper.entities.repositories.VendorRepository;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/vendors")
public class VendorController
{
   @Autowired
   private VendorRepository vendorRepository;

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public String badRequest()
   {
      return "Bad request";
   }

   @GetMapping(value = "/create")
   public String getCreate(Model model)
   {
      model.addAttribute("vendor", new Vendor());
      return "vendor_create";
   }

   @PostMapping(value = "/create")
   public String postCreate(@ModelAttribute Vendor vendor)
   {
      vendorRepository.save(vendor);
      return "redirect:/vendors/view";
   }

   @GetMapping(value = "/edit")
   public String getEdit(@RequestParam int id, Model model)
   {
      Optional<Vendor> vendorOptional = vendorRepository.findById(id);
      if(!vendorOptional.isPresent())
      {
         return "redirect:/vendors/bad-request";
      }

      model.addAttribute("vendor", vendorOptional.get());
      return "vendor_edit";
   }

   @PostMapping(value = "/edit")
   public String postEdit(@RequestParam int id, @ModelAttribute Vendor formVendor)
   {
      Optional<Vendor> vendorOptional = vendorRepository.findById(id);
      if(!vendorOptional.isPresent())
      {
         return "redirect:/vendors/bad-request";
      }

      Vendor vendor = vendorOptional.get();
      vendor.setLegalName(formVendor.getLegalName());
      vendor.setDisplayName(formVendor.getDisplayName());
      vendor.setContactTitle(formVendor.getContactTitle());
      vendor.setContactFirstName(formVendor.getContactFirstName());
      vendor.setContactLastName(formVendor.getContactLastName());
      vendor.setContactSuffix(formVendor.getContactSuffix());
      vendor.setEmail(formVendor.getEmail());
      vendor.setPhone(formVendor.getPhone());
      vendor.setMobile(formVendor.getMobile());
      vendor.setFax(formVendor.getFax());
      vendor.setWebsite(formVendor.getWebsite());
      vendor.setAddress1(formVendor.getAddress1());
      vendor.setAddress2(formVendor.getAddress2());
      vendor.setCity(formVendor.getCity());
      vendor.setState(formVendor.getState());
      vendor.setZip(formVendor.getZip());
      vendor.setCountry(formVendor.getCountry());
      vendor.setNotes(formVendor.getNotes());
      vendor.setTaxID(formVendor.getTaxID());
      vendorRepository.save(vendor);
      return "redirect:/vendors/view";
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

      Page<Vendor> pageVendors;
      if(search == null)
      {
         pageVendors = vendorRepository.findAllPageable(pageable);
      }
      else
      {
         pageVendors = vendorRepository.findBySearchTerm(search, pageable);
         model.addAttribute("search", search);
      }

      List<Vendor> vendors = pageVendors.getContent();
      model.addAttribute("vendors", vendors);
      model.addAttribute("currentPage", pageVendors.getNumber() + 1);
      model.addAttribute("totalItems", pageVendors.getTotalElements());
      model.addAttribute("totalPages", pageVendors.getTotalPages());
      model.addAttribute("pageSize", size);
      model.addAttribute("sortField", sortField);
      model.addAttribute("sortDirection", sortDirection);
      model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
      return "vendor_view";
   }

   @GetMapping(value = "/delete")
   public String getDelete(@RequestParam int id, Model model)
   {
      Optional<Vendor> vendorOptional = vendorRepository.findById(id);
      if(!vendorOptional.isPresent())
      {
         return "redirect:/vendors/bad-request";
      }

      model.addAttribute("vendor", vendorOptional.get());
      return "vendor_delete";
   }

   @PostMapping(value = "/delete")
   public String postDelete(@RequestParam int id)
   {
      Optional<Vendor> vendorOptional = vendorRepository.findById(id);
      if(!vendorOptional.isPresent())
      {
         return "redirect:/vendors/bad-request";
      }

      vendorRepository.deleteById(id);
      return "redirect:/vendors/view";
   }
}