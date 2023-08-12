import org.json.simple.JSONObject;

public class Medication {

   private String ID;
   private String name;
   private String details;
   private String dosage;
   private int quantity;
   private Boolean processedStatus;

   public Medication(String medicationID, String medicationName, int quantity, boolean processedStatus) {
	   this.processedStatus = false;
   }

   public Medication(String _id, String _name, String medicationDetails, String dosage, int qty ) {
      this.ID = _id;
      this.name =_name;
      this.quantity = qty;
      this.processedStatus = false;
   }

   // TODO: Add code to help you to create object/instance for this class in different way


   public Medication(String _id,String _name,int qty,String dosage,Boolean processedStatus ) {
      this.ID = _id;
      this.name =_name;
      this.quantity = qty;
      this.processedStatus = false;
      this.dosage=dosage;
   }



   // TODO: Add code to help you to access or modify data members for this class
public String getID(){
      return this.ID;
}
public void setID(String ID){
      ID=this.ID;
}
public String getName(){
      return this.name;
}
public void setName(String name){
      name=this.name;
}
public String getDetails(){
      return this.details;
}
public void setDetails(String details){
      details=this.details;
}
public String getDosage(){
      return this.dosage;
}
public void setDosage(String dosage){
      dosage=this.details;
}
public int getQuantity(){
      return this.quantity;
}
public void setQuantity(int quantity){
      quantity=this.quantity;
}
public boolean getProcessedStatus(){
      return this.processedStatus;
}
public void setProcessedStatus(boolean processedStatus){
      processedStatus=this.processedStatus;
}



   public String toString() {
      return this.ID + "," + this.name + "," + this.quantity + "," + this.processedStatus;
   }

    public JSONObject toJson() {
       JSONObject jsonObject = new JSONObject();
       jsonObject.put("id", this.ID);
       jsonObject.put("name", this.name);
       jsonObject.put("quantity", this.quantity);
       jsonObject.put("processedStatus", this.processedStatus);
       jsonObject.put("dosage", this.dosage);
       jsonObject.put("details", this.details);
       return jsonObject;

    }
}