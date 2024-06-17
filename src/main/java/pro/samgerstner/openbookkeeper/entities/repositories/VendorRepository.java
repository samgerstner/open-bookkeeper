package pro.samgerstner.openbookkeeper.entities.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pro.samgerstner.openbookkeeper.entities.Vendor;

public interface VendorRepository extends CrudRepository<Vendor, Integer>
{
   @Query("select v from Vendor v where v.legalName like %?1% or v.displayName like %?1% or v.email like %?1% or v.taxID like %?1%")
   public Page<Vendor> findBySearchTerm(String search, Pageable pageable);

   @Query("select v from Vendor v")
   public Page<Vendor> findAllPageable(Pageable pageable);
}