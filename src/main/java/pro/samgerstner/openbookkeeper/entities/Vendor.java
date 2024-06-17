package pro.samgerstner.openbookkeeper.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "vendors")
public class Vendor
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;

   @Column(name = "legal_name", nullable = false)
   private String legalName;

   @Column(name = "display_name", nullable = false)
   private String displayName;

   @Column(name = "contact_title")
   private String contactTitle;

   @Column(name = "contact_first_name")
   private String contactFirstName;

   @Column(name = "contact_last_name")
   private String contactLastName;

   @Column(name = "contact_suffix")
   private String contactSuffix;

   private String email;

   private String phone;

   private String mobile;

   private String fax;

   private String website;

   private String address1;

   private String address2;

   private String city;

   private String state;

   private String zip;

   private String country;

   private String notes;

   private String taxID;

   public Vendor() {}

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public String getLegalName()
   {
      return legalName;
   }

   public void setLegalName(String legalName)
   {
      this.legalName = legalName;
   }

   public String getDisplayName()
   {
      return displayName;
   }

   public void setDisplayName(String displayName)
   {
      this.displayName = displayName;
   }

   public String getContactTitle()
   {
      return contactTitle;
   }

   public void setContactTitle(String contactTitle)
   {
      this.contactTitle = contactTitle;
   }

   public String getContactFirstName()
   {
      return contactFirstName;
   }

   public void setContactFirstName(String contactFirstName)
   {
      this.contactFirstName = contactFirstName;
   }

   public String getContactLastName()
   {
      return contactLastName;
   }

   public void setContactLastName(String contactLastName)
   {
      this.contactLastName = contactLastName;
   }

   public String getContactSuffix()
   {
      return contactSuffix;
   }

   public void setContactSuffix(String contactSuffix)
   {
      this.contactSuffix = contactSuffix;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getPhone()
   {
      return phone;
   }

   public void setPhone(String phone)
   {
      this.phone = phone;
   }

   public String getMobile()
   {
      return mobile;
   }

   public void setMobile(String mobile)
   {
      this.mobile = mobile;
   }

   public String getFax()
   {
      return fax;
   }

   public void setFax(String fax)
   {
      this.fax = fax;
   }

   public String getWebsite()
   {
      return website;
   }

   public void setWebsite(String website)
   {
      this.website = website;
   }

   public String getAddress1()
   {
      return address1;
   }

   public void setAddress1(String address1)
   {
      this.address1 = address1;
   }

   public String getAddress2()
   {
      return address2;
   }

   public void setAddress2(String address2)
   {
      this.address2 = address2;
   }

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public String getState()
   {
      return state;
   }

   public void setState(String state)
   {
      this.state = state;
   }

   public String getZip()
   {
      return zip;
   }

   public void setZip(String zip)
   {
      this.zip = zip;
   }

   public String getCountry()
   {
      return country;
   }

   public void setCountry(String country)
   {
      this.country = country;
   }

   public String getNotes()
   {
      return notes;
   }

   public void setNotes(String notes)
   {
      this.notes = notes;
   }

   public String getTaxID()
   {
      return taxID;
   }

   public void setTaxID(String taxID)
   {
      this.taxID = taxID;
   }
}
