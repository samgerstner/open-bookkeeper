package pro.samgerstner.openbookkeeper.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;

   private String accountNumber;

   private String accountName;

   //@Column(name = "account_type", nullable = false)
   @ManyToOne
   @JoinColumns({
         @JoinColumn(name = "account_type", referencedColumnName = "id")
   })
   private AccountType accountType;

   private double currentBalance;

   public Account() {}

   public Account(String accountNumber, String accountName, AccountType accountType)
   {
      this.accountNumber = accountNumber;
      this.accountName = accountName;
      this.accountType = accountType;
      this.currentBalance = 0.0;
   }

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public String getAccountNumber()
   {
      return accountNumber;
   }

   public void setAccountNumber(String accountNumber)
   {
      this.accountNumber = accountNumber;
   }

   public String getAccountName()
   {
      return accountName;
   }

   public void setAccountName(String accountName)
   {
      this.accountName = accountName;
   }

   public AccountType getAccountType()
   {
      return accountType;
   }

   public void setAccountType(AccountType accountType)
   {
      this.accountType = accountType;
   }

   public double getCurrentBalance()
   {
      return currentBalance;
   }

   public void setCurrentBalance(double currentBalance)
   {
      this.currentBalance = currentBalance;
   }
}