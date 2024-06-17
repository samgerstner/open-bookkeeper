package pro.samgerstner.openbookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pro.samgerstner.openbookkeeper.entities.AccountType;
import pro.samgerstner.openbookkeeper.entities.repositories.AccountTypeRepository;
import java.util.Arrays;

@Component
public class InitializeDatabase implements CommandLineRunner
{
   @Autowired
   AccountTypeRepository accountTypeRepository;

   @Override
   public void run(String... args) throws Exception
   {
      if(!accountTypeRepository.existsById(0))
      {
         accountTypeRepository.saveAll(Arrays.asList(
               new AccountType(0, "Assets"),
               new AccountType(1, "Liabilities"),
               new AccountType(2, "Expenses"),
               new AccountType(3, "Income"),
               new AccountType(4, "Equity"),
               new AccountType(5, "Opening Balance Equity"),
               new AccountType(6, "Other Miscellaneous Expense"),
               new AccountType(7, "Supplies & Materials"),
               new AccountType(8, "Inventory"),
               new AccountType(9, "Cost of Goods Sold"),
               new AccountType(10, "Current Liabilities"),
               new AccountType(11, "Long Term Liabilities"),
               new AccountType(12, "Current Assets"),
               new AccountType(13, "Long Term Assets"),
               new AccountType(14, "Accounts Payable (A/P)"),
               new AccountType(15, "Bad Debts"),
               new AccountType(16, "Bank Charges"),
               new AccountType(17, "Partner Distributions"),
               new AccountType(18, "Retained Earnings"),
               new AccountType(19, "Owner's Equity"),
               new AccountType(20, "Interest Earned"),
               new AccountType(21, "Accounts Receivable (A/R)")
         ));
      }
   }
}