import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;

public class PrescriptionManagement {



   public static void main(String[] args) throws IOException, ParseException {
//       public static JSONArray readMedicationsFromFile(String filePath) throws FileNotFoundException, IOException, ParseException{
//           JSONParser parser = new JSONParser();
//           try (FileReader fileReader = new FileReader(filePath))
//       }
       ArrayList<Prescription> prescriptions = new ArrayList<>();


       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       int choice, numMedications;
       ArrayList<Medication> medications = new ArrayList<>();
       Prescription prescription = new Prescription("prescriptionID", "customerID", "doctorName", medications, LocalDate.now(), new JSONArray());


       while (true) {

           while (true) {

               // TODO: Add code to display the menu and get the number(choice) a user selected
               System.out.println("1. Add Prescription");
               System.out.println("2. View Prescription");
               System.out.println("3. Delete Prescription");
               System.out.println("4. Exit");
               System.out.println("Enter Your Choice:");

               choice = Integer.parseInt(reader.readLine());
               switch (choice) {
                   case 1:

                       // TODO: Add code to get Prescription ID, Customer ID,  Doctor's Name
                       // Don't forget to add code to save these information in the prescription
                       System.out.println("Enter Prescription ID: ");
                       String prescriptionID = reader.readLine();
                       prescription.setPrescriptionID(prescriptionID);

                       System.out.println("Enter Customer ID: ");
                       String customerID = reader.readLine();
                       prescription.setCustomerID(customerID);

                       System.out.println("Enter Doctor's Name: ");
                       String doctorName = reader.readLine();
                       prescription.setDoctorName(doctorName);


                       prescription.setDate(LocalDate.now());


                       System.out.print("Enter the number of medications to add: ");
                       numMedications = Integer.parseInt(reader.readLine());

//                       medications = new ArrayList<>();
                       String medicationName, medicationDetails, dosage, medicationID;
                       int quantity;


                       // TODO: Add code to display available products/medications before adding them on the prescription
                       String medicationsFilePath = "products.json";
//                       JSONArray medicationsArray = readMedicationsFromFile(medicationsFilePath);

                       System.out.println("Available Medications:");
                       System.out.println("----------------------------------");



                       for (int i = 1; i <= numMedications; i++) {

//                           System.out.println("Enter details for Medication " + i + ":");
                           // TODO: Add code to get Medication ID, Name, Details, Dosage and Quantity
                           System.out.println("Enter details for Medication " + i + ":");
                           System.out.println("Medication ID:");
                           medicationID = reader.readLine();
                           System.out.println("Medication Name:");
                           medicationName = reader.readLine();
                           System.out.println("Medication Details:");
                           medicationDetails = reader.readLine();
                           System.out.println("Dosage:");
                           dosage = reader.readLine();
                           System.out.println("Quantity:");
//                           quantity = Integer.parseInt(reader.readLine());
                           try{
                               System.out.println("Quantity:");
                               quantity = Integer.parseInt(reader.readLine());
                           }
                           catch (NumberFormatException e){
                               System.out.println("Invalid quantity. please enter valid number.");
                               continue;
                           }

                           Medication medication = new Medication(medicationID, medicationName, medicationDetails, dosage, quantity);
                           medications.add(medication);
                       }

                       // TODO: Add code to save all medications inserted by the user on the prescription
                       prescription.setMedications(medications);


//                       PrescriptionManagement.addPrescription(prescription);
                       prescriptions.add(prescription);

                       break;
                   case 2:
                       // TODO: Add code to retrieve all prescriptions in the file
                       // Prescriptions must be returned in the array

                       if (prescriptions.size() == 0) {
                           System.out.println("No precriptions available\n");
                       } else {
                           System.out.println("| PrescriptionID |  DoctorName   |    CustomerID | \tDate\t | ");
                           System.out.println("******************************************************************");

                           for (Prescription p : prescriptions) {
                               System.out.println("|\t  " + p.getPrescriptionID() + "\t\t" + p.getDoctorName() + "\t\t  " + p.getCustomerID() + "\t\t" + p.getDate());

                               System.out.println("");
                               System.out.println("| MedicationID |  \tName    | \t Quantity | ");
                               for (Medication med : p.getMedications()) {
                                   System.out.println("|\t  " + med.getID() + "\t\t" + med.getName() + "\t\t " + med.getQuantity());
                               }

                               System.out.print("\n");
                               System.out.println("*****************************************************************");
                           }

                           System.out.println("");
                       }

                       break;
                   case 3:
                       // TODO: Add code to get the ID of the prescription you want to delete
                       System.out.println("Enter the ID of the prescription to delete:");
                       String prescrID = reader.readLine();
                       Prescription prescriptionToDelete = null;
                       for (Prescription p: prescriptions){
                           if (p.getPrescriptionID().equals(prescrID)){
                               prescriptionToDelete = p;
                               break;
                           }
                       }


//                       prescription.deletePrescription(prescrID);
                       break;
                   case 4:
                       System.out.println("Exiting the Precription Management section...");
                       System.exit(0);
                   default:
                       System.out.println("Invalid choice. Please try again.");
               }


           }


       }
   }

    private static void addPrescription(Prescription prescription) {

    }

    public static void displayMedications(String filePath) throws FileNotFoundException, IOException, ParseException {
	   
	      JSONParser parser = new JSONParser();
	      try(FileReader fileReader = new FileReader(filePath)){
	          if (fileReader.read() == -1) {
	              return;
	          } 
	          else {
	              fileReader.close();
	              JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
	              
                  System.out.println("---------------------------------------------------------------------------------------");
                  System.out.println("|\t"  + "\t\t  "  + "\t\t\t\t");
                  System.out.println("|\t" + "\t\t"  +  "Available Medications" + "\t\t");
                  System.out.println("|\t"  + "\t\t  "  + "\t\t\t\t");
                  System.out.println("---------------------------------------------------------------------------------------");
                  System.out.println("| Medication ID |  Medication Name   |    Medication Price ||    Medication Quantity |");
                  System.out.println("---------------------------------------------------------------------------------------");
                  
	              for (Object obj: jsonArray) {
	                  JSONObject jsonObject = (JSONObject) obj;
	                  
                    // TODO: Add code to get medication ID (it's named as code from medications/products file), name, price and quantity
                    // medication ID, name, price and quantity should be casted to String
                      String medicationID = (String)jsonObject.get("medicationID");
                      String medicationName = (String)jsonObject.get("medicationName");
                      String medicationPrice = (String)jsonObject.get("medicationPrice");
                      String medicationQuantity = (String)jsonObject.get("medicationQuantity");

                      
                      System.out.println("|\t" + medicationID + "\t\t" + medicationName + "\t\t  " + medicationPrice + "\t\t\t  " + medicationQuantity + "\t\t");
	                  
	              }
                  System.out.println("---------------------------------------------------------------------------------------");

	              
	          }  
	      }
	   
   } 

}
