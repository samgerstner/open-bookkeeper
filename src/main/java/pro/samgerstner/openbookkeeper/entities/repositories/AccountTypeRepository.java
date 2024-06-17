package pro.samgerstner.openbookkeeper.entities.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pro.samgerstner.openbookkeeper.entities.AccountType;

public interface AccountTypeRepository extends CrudRepository<AccountType, Integer>
{
   @Query("select at from AccountType at where at.type like %?1%")
   Page<AccountType> findByType(String searchText, Pageable pageable);

   @Query("select at from AccountType at")
   Page<AccountType> findAllPageable(Pageable pageable);
}