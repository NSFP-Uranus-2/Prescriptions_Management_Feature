import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.ParseException;


public class Prescription {
	   private String prescriptionID;
	   private String customerID;
	   private String doctorName;
	   private ArrayList<Medication> medications;
	   private LocalDate date;
	   private static JSONArray prescriptionList;
	   

	   public Prescription(String prescriptionID, String customerID, String doctorName, LocalDate dateToPrint, ArrayList<Medication> medications, LocalDate toPrint, JSONArray jsonArray) {
		   prescriptionList = new JSONArray();
	   }
	   
	   public Prescription(String _prescriptionID, String _customerID, String _doctorName, ArrayList<Medication> _medication)
	   {
	       prescriptionID = _prescriptionID;
	       customerID = _customerID;
	       doctorName = _doctorName;
	       medications = _medication;
	       date = LocalDate.now();
	   }
	  
   // TODO: Add code to help you to create object/instance for this class in different way
   public Prescription(String _prescriptionID, String _customerID, String _doctorName, ArrayList<Medication> _medication, LocalDate _date, JSONArray _prescriptionList)
   {
	   prescriptionID = _prescriptionID;
	   customerID = _customerID;
	   doctorName = _doctorName;
	   medications = _medication;
	   date = LocalDate.now();
	   prescriptionList = _prescriptionList;
   }



   // TODO: Add code to help you to access or modify data members for this class
		public String getPrescriptionID(){
			return this.prescriptionID;
		}
	    public void setPrescriptionID(String prescriptionID){
		prescriptionID=this.prescriptionID;
	    }
		public String getCustomerID(){
		   return this.customerID;
		}
		public void setCustomerID(String customerID){
		   customerID=this.customerID;
		}
		public String getDoctorName(){
		   return this.doctorName;
		}
		public void setDoctorName(String doctorName){
		   doctorName=this.doctorName;
		}
		public ArrayList<Medication> getMedications(){
		   return this.medications;
		}
		public void setMedications(ArrayList medications){
		   medications=this.medications;
		}
		public LocalDate getDate(){
		   return this.date;
		}
		public void setDate(LocalDate date){
		   date=this.date;
		}
		public JSONArray getPrescriptionList(){
		   return this.prescriptionList;
		}

	   



		// TODO: Add code needed to be able to add prescription in the file
		// While adding the prescription in the file, please follow the format shown below
		// Format for the prescription: {"DoctorName":"Yves","PrescriptionID":"TA3","Medications":[{"quantity":2,"processedStatus":false,"name":"IBUPROFEN","id":"IB7"}],"CustomerID":"GR","Date":"2023-08-07"}

	    public void addPrescription() throws IOException, ParseException {
			FileHandler fileHandler = new FileHandler();
			JSONArray existingPrescriptions = fileHandler.readJSONArrayFromFile();

			JSONObject prescriptionObject = new JSONObject();
			prescriptionObject.put("DoctorName", doctorName);
			prescriptionObject.put("PrescriptionID", prescriptionID);
			prescriptionObject.put("CustomerID", customerID);
			prescriptionObject.put("Date", date.toString());

			JSONArray medicationsArray = new JSONArray();
			for (Medication medication : medications) {
				JSONObject medicationObject = new JSONObject();
				medicationObject.put("quantity", medication.getQuantity());
				medicationObject.put("processedStatus", medication.getProcessedStatus());
				medicationObject.put("name", medication.getName());
				medicationObject.put("id", medication.getID());
				medicationsArray.add(medicationObject);
			}
			prescriptionObject.put("Medications", medicationsArray);

			// TODO: Add code to add prescription in the file
			System.out.println(this);
			existingPrescriptions.add(this.toJson());
			fileHandler.writeJSONArrayToFile(existingPrescriptions);
	    }
	        private JSONObject toJson(){
		         JSONObject jsonObject = new JSONObject();
				 Map<String, Object> prescriptionMap = new HashMap();
				 prescriptionMap.put("DoctorName", this.doctorName);
				 prescriptionMap.put("PrescriptionID", this.prescriptionID);
				 JSONArray medicationsArray = new JSONArray();
				 this.medications.forEach((medication) -> {
					medicationsArray.add(medication.toJson());
				   });
				 prescriptionMap.put("Medications", medicationsArray);
				 jsonObject.putAll(prescriptionMap);
				 return jsonObject;
			}
	   
	   
		// TODO: Add code needed to be able to get all medications on the prescription

		// TODO: You must return an array of medications!

		private JSONArray  getMedicationsOnPrescription(Prescription prescription) {
			JSONArray jsonArray = new JSONArray();
			

			// TODO: Add code to get medications on the prescription
			ArrayList<Medication> medications = prescription.getMedications();

			for (Medication medication : medications){
				JSONObject medicationObject = new JSONObject();
				medicationObject.put("quantity", medication.getQuantity());
				medicationObject.put("processedStatus", medication.getProcessedStatus());
				medicationObject.put("name", medication.getName());
				medicationObject.put("id", medication.getID());
				jsonArray.add(medicationObject);
			}


			return jsonArray;
		}
	    
	   
		// TODO: Add code to help you viewing all prescriptions in the file
		// You must return an array of prescriptions


	   	public ArrayList<Prescription> viewPrescription() throws IOException, ParseException {
			// TODO: Add code to help you reading from the prescriptions.json file
			FileHandler fileHandler = new FileHandler();
			JSONArray jsonArray = fileHandler.readJSONArrayFromFile();

			ArrayList<Prescription> prescriptions = new ArrayList<>();

	        // TODO: Add code to help you creating an array of prescriptions



	        for (Object obj : jsonArray) {
	            JSONObject jsonObject = (JSONObject) obj;
                
                String doctorName = (String) jsonObject.get("DoctorName");
                String prescriptionID = (String) jsonObject.get("PrescriptionID");
                String customerID = (String) jsonObject.get("CustomerID");
                String date = (String) jsonObject.get("Date");
                LocalDate dateToPrint = LocalDate.parse(date);
                
                ArrayList<Medication> medications = new ArrayList<>();
                
                JSONArray medicationsArray = (JSONArray) jsonObject.get("Medications");

                for (Object medObj : medicationsArray) {
                    JSONObject medication = (JSONObject) medObj;

					// TODO: Add code to get medication ID, name and quantity
					// medication quantity should be casted to int
                    // also medication ID and name should be casted to String
					int quantity = ((Long) medication.get("quantity")).intValue();
					boolean processedStatus = (boolean) medication.get("processedStatus");
					String medicationName = (String) medication.get("name");
					String medicationID = (String) medication.get("id");

                    medications.add(new Medication(medicationID, medicationName, quantity, processedStatus));
                }

                prescriptions.add(new Prescription(prescriptionID,customerID, doctorName, dateToPrint, medications, dateToPrint, jsonArray));
                
            }
			return prescriptions;

	    }
	   
	   


		// TODO: Add code to help you deleting a specific prescription
		public void deletePrescription(String prescriptionIDToDelete) throws IOException, ParseException {
		   FileHandler fileHandler = new FileHandler();
		   JSONArray existingPrescriptions = fileHandler.readJSONArrayFromFile();
			// TODO: Add code to help you reading from the prescriptions.json file


			int indexToDelete = -1;
			for (int i = 0; i < existingPrescriptions.size(); i++) {
				JSONObject jsonObject = (JSONObject) existingPrescriptions.get(i);
				String existingPrescriptionID = (String) jsonObject.get("PrescriptionID");

				// TODO: Add code to check if the prescription you want to delete is similar to one exists
				if (existingPrescriptionID.equals(prescriptionIDToDelete)) {
					indexToDelete = i;
					break;
				}
			}

			if (indexToDelete != -1) {
				existingPrescriptions.remove(indexToDelete);
				fileHandler.writeJSONArrayToFile(existingPrescriptions);
				fileHandler.writeJSONArrayToFile(existingPrescriptions);
				System.out.println("Prescription with ID" + prescriptionIDToDelete + "deleted successfully!");
			}
			else {
				System.out.println("Prescription with ID" + prescriptionIDToDelete + "not found!");
			}
			}
		}