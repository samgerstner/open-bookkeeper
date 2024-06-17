package pro.samgerstner.openbookkeeper.entities.repositories;

import org.springframework.data.repository.CrudRepository;
import pro.samgerstner.openbookkeeper.entities.JournalEntryAttachment;

public interface JournalEntryAttachmentRepository extends CrudRepository<JournalEntryAttachment, Integer>
{
}