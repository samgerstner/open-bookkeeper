package pro.samgerstner.openbookkeeper.entities.repositories;

import org.springframework.data.repository.CrudRepository;
import pro.samgerstner.openbookkeeper.entities.JournalEntry;

public interface JournalEntryRepository extends CrudRepository<JournalEntry, Integer>
{
}