package pro.samgerstner.openbookkeeper.entities;

import java.util.ArrayList;
import java.util.List;

public class JournalEntryRowDTO
{
   private List<JournalEntryRow> rows;

   public JournalEntryRowDTO()
   {
      this.rows = new ArrayList<>();
   }

   public JournalEntryRowDTO(List<JournalEntryRow> rows)
   {
      this.rows = rows;
   }

   public void addRow(JournalEntryRow row)
   {
      this.rows.add(row);
   }

   public List<JournalEntryRow> getRows()
   {
      return rows;
   }

   public void setRows(List<JournalEntryRow> rows)
   {
      this.rows = rows;
   }
}