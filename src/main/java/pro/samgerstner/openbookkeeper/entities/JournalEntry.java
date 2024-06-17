package pro.samgerstner.openbookkeeper.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "journal_entries")
public class JournalEntry
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;

   @Column(name = "journal_date", nullable = false)
   private String journalDate;

   @Column(name = "journal_name", nullable = false)
   private String journalName;

   @Column(name = "adjusting_entry", nullable = false)
   private boolean isAdjustingEntry;

   @Column(name = "memo")
   private String memo;

   @OneToMany
   private List<JournalEntryRow> entryRows;

   @OneToMany
   private List<JournalEntryAttachment> attachments;

   public JournalEntry() {}

   public JournalEntry(String journalDate, String journalName, boolean isAdjustingEntry, String memo)
   {
      this.journalDate = journalDate;
      this.journalName = journalName;
      this.isAdjustingEntry = isAdjustingEntry;
      this.memo = memo;
   }

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public String getJournalDate()
   {
      return journalDate;
   }

   public void setJournalDate(String journalDate)
   {
      this.journalDate = journalDate;
   }

   public String getJournalName()
   {
      return journalName;
   }

   public void setJournalName(String journalName)
   {
      this.journalName = journalName;
   }

   public boolean isAdjustingEntry()
   {
      return isAdjustingEntry;
   }

   public void setAdjustingEntry(boolean adjustingEntry)
   {
      isAdjustingEntry = adjustingEntry;
   }

   public String getMemo()
   {
      return memo;
   }

   public void setMemo(String memo)
   {
      this.memo = memo;
   }

   public List<JournalEntryRow> getEntryRows()
   {
      return entryRows;
   }

   public void setEntryRows(List<JournalEntryRow> entryRows)
   {
      this.entryRows = entryRows;
   }

   public List<JournalEntryAttachment> getAttachments()
   {
      return attachments;
   }

   public void setAttachments(List<JournalEntryAttachment> attachments)
   {
      this.attachments = attachments;
   }
}