package pro.samgerstner.openbookkeeper.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "journal_entry_rows")
public class JournalEntryRow
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;

   @ManyToOne
   @JoinColumns({
         @JoinColumn(name = "account_id", referencedColumnName = "id")
   })
   private Account account;

   @Column(name = "debit_amount", nullable = false)
   private double debitAmount;

   @Column(name = "credit_amount", nullable = false)
   private double creditAmount;

   @Column(name = "description", nullable = false)
   private String description;

   @ManyToOne
   @JoinColumns({
         @JoinColumn(name = "vendor_id", referencedColumnName = "id")
   })
   private Vendor vendor;

   @ManyToOne
   private JournalEntry journal;

   public JournalEntryRow() {}

   public JournalEntryRow(Account account, double debitAmount, double creditAmount, String description, Vendor vendor, JournalEntry journal)
   {
      this.account = account;
      this.debitAmount = debitAmount;
      this.creditAmount = creditAmount;
      this.description = description;
      this.vendor = vendor;
      this.journal = journal;
   }

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public Account getAccount()
   {
      return account;
   }

   public void setAccount(Account account)
   {
      this.account = account;
   }

   public double getDebitAmount()
   {
      return debitAmount;
   }

   public void setDebitAmount(double debitAmount)
   {
      this.debitAmount = debitAmount;
   }

   public double getCreditAmount()
   {
      return creditAmount;
   }

   public void setCreditAmount(double creditAmount)
   {
      this.creditAmount = creditAmount;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public Vendor getVendor()
   {
      return vendor;
   }

   public void setVendor(Vendor vendor)
   {
      this.vendor = vendor;
   }

   public JournalEntry getJournal()
   {
      return journal;
   }

   public void setJournal(JournalEntry journal)
   {
      this.journal = journal;
   }
}