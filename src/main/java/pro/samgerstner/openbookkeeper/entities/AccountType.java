package pro.samgerstner.openbookkeeper.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "account_types")
public class AccountType
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;

   @Column(name = "account_type", nullable = false)
   private String type;

   public AccountType() {}

   public AccountType(String type)
   {
      this.type = type;
   }

   public AccountType(int id, String type)
   {
      this.id = id;
      this.type = type;
   }

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public String getType()
   {
      return type;
   }

   public void setType(String type)
   {
      this.type = type;
   }
}