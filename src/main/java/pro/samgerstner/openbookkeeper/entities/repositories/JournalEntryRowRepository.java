package pro.samgerstner.openbookkeeper.entities.repositories;

import org.springframework.data.repository.CrudRepository;
import pro.samgerstner.openbookkeeper.entities.JournalEntryRow;

public interface JournalEntryRowRepository extends CrudRepository<JournalEntryRow, Integer>
{
}