package pro.samgerstner.openbookkeeper.entities.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pro.samgerstner.openbookkeeper.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Integer>
{
   @Query("select a from Account a where a.accountNumber like %?1% or a.accountName like %?1%")
   public Page<Account> findByNumberOrName(String search, Pageable pageable);

   @Query("select a from Account a")
   public Page<Account> findAllPageable(Pageable pageable);
}