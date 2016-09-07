package localhost.javaSailRestDemo.employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

/**
 * Class that makes request to API to update employee record
 * 
 * @author paul
 * @since 2016-07-09
 *
 */

public class Create {

	/* 
	 * URL of the API we want to connect to
	 */
	protected static String employee = "http://localhost:1337/employee";
	
	/*
	 * CHaracter set to use when encoding URL parameters
	 */
	protected static String charset = "UTF-8";
			
	
	//scanner for user input via console 
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//selection menu, loops after running selected commands to allow user to select another option
		boolean menuValidation;
		
		do{
			menuValidation = false;
			System.out.println(" ");
			System.out.println("Please make a menu selection\n"		
				+ "1. Create a new record\n"
				+ "2. Update a record\n"
				+ "3. Delete a record\n"
				+ "4. View all records\n"
				+ "5. Search for record by ID\n"
				+ "6. Exit");
			
			String menuSelection = sc.nextLine();
		
			if(menuSelection.equals("1")){
				createRecord();
				
			}else if(menuSelection.equals("2")){
				updateRecord();
			
			}else if(menuSelection.equals("3")){
				delete();
			
			}else if(menuSelection.equals("4")){
				viewAll();
				
			}else if(menuSelection.equals("5")) {
				viewID();
				
			}else if(menuSelection.equals("6")){
				System.out.println("Thank you, good-bye!");
				System.exit(0);
			
			}else 
			System.out.println("Please make a valid menu selection 1, 2, 3, 4, 5 or 6");
			menuValidation = true;
		
		}while (menuValidation);
		
	}//main
		
	private static void delete() {
		
		System.out.println("Enter Employee ID to delete");
		String scID = sc.nextLine();
		
	try {
		
		URL employeeRecord = new URL(employee + "/" + scID);
		HttpURLConnection connection = (HttpURLConnection) employeeRecord.openConnection();
		connection.setRequestMethod("DELETE");
		
		//if do not get 201/create (success) throw exception
		if (connection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseMessage());
		}
		
		//read response into buffer
		BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));			
		
		//loop of buffer line by line until it return null meaning there are no more lines
		while (br.readLine() != null) {
			//print out each line to the screen
			System.out.println(br.readLine());
		}
		
		//close connection to API
		connection.disconnect();
		
	} catch (MalformedURLException e) {
		
		e.printStackTrace();
		
	} catch (IOException e) {
		
		e.printStackTrace();
		
	}
		
}//delete
	
	private static void viewAll() {
		
	try {
		//creates a new URL out of the api URL
		URL employeeRecord = new URL(employee);
		HttpURLConnection connection = (HttpURLConnection) employeeRecord.openConnection();
		connection.setRequestMethod("GET");
		
		//if do not get 200/create (success) throw exception
		if (connection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseMessage());
		}
		
		//read response into buffer
		BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));			
		
		//loop of buffer line by line until it return null meaning there are no more lines
		while (br.readLine() != null) {
			//print out each line to the screen
			System.out.println(br.readLine());
		}
		
		//close connection to API
		connection.disconnect();
		
	} catch (MalformedURLException e) {
		
		e.printStackTrace();
		
	} catch (IOException e) {
		
		e.printStackTrace();
		
	}
		
}//viewall	
		
	private static void viewID() {

		System.out.println("Enter Employee ID to view");
		String scID = sc.nextLine();
		
	try {
		
		//creates a new URL out of the api link, employee ID
		URL employeeRecord = new URL(employee + "/" + scID);
		HttpURLConnection connection = (HttpURLConnection) employeeRecord.openConnection();
		connection.setRequestMethod("GET");
		
		//if do not get 200 (success) throw exception
		if (connection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseMessage());
		}
		
		//read response into buffer
		BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));			
		
		//loop of buffer line by line until it return null meaning there are no more lines
		while (br.readLine() != null) {
			//print out each line to the screen
			System.out.println(br.readLine());
		}
		
		//close connection to API
		connection.disconnect();
		
	} catch (MalformedURLException e) {
		
		e.printStackTrace();
		
	} catch (IOException e) {
		
		e.printStackTrace();
		
	}		
		
	} //viewID

	private static void updateRecord() {
		
		System.out.println("Enter Employee ID to update");
		String scID = sc.nextLine();
		
		System.out.println("Enter the first name:");
		String scFirstName = sc.nextLine();
		
		System.out.println("Enter the last name:");
		String scLastName = sc.nextLine();
		
		System.out.println("Enter the email:");
		String scEmail = sc.nextLine();
		
		System.out.println("Enter home phone:");
		String scHomePhone = sc.nextLine();
		
		System.out.println("Enter cell phone:");
		String scCellPhone = sc.nextLine();
		
		try {
		
			//
			String employeeID = scID;
			
			//
			String firstName = scFirstName;
			
			//
			String lastName = scLastName;
			
			//
			String email = scEmail;
			
			//
			String homePhone = scHomePhone;
			
			//
			String cellPhone = scCellPhone;
			
			//
			String password = "Passw0rd";
		
			//
			String active = "1";
			
			//creates the url parameters as a string encoding them the defined charset
			String queryString = String.format("employeeID=%s&firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s",
					URLEncoder.encode(employeeID, charset),
					URLEncoder.encode(firstName, charset),
					URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset),
					URLEncoder.encode(homePhone, charset),
					URLEncoder.encode(cellPhone, charset),
					URLEncoder.encode(password, charset),
					URLEncoder.encode(active, charset)
			);
			
			//creates a new URL out of the api link, employee ID and querystring
			
			URL employeeRecord = new URL(employee + "/" + scID + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) employeeRecord.openConnection();
			connection.setRequestMethod("PUT");
			
			//if do not get 200 (success) throw exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseMessage());
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));			
			
			//loop of buffer line by line until it return null meaning there are no more lines
			while (br.readLine() != null) {
				//print out each line to the screen
				System.out.println(br.readLine());
			}
			
			//close connection to API
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
			
}//update			
	
	private static void createRecord() {
		
		System.out.println("Enter the first name:");
		String scFirstName = sc.nextLine();
		
		System.out.println("Enter the last name:");
		String scLastName = sc.nextLine();
		
		System.out.println("Enter the email:");
		String scEmail = sc.nextLine();
		
		System.out.println("Enter home phone:");
		String scHomePhone = sc.nextLine();
		
		System.out.println("Enter cell phone:");
		String scCellPhone = sc.nextLine();
		
		try {
		
			//first name in record		
			String firstName = scFirstName;
			
			//last name in record
			String lastName = scLastName;
			
			//e-mail in record
			String email = scEmail;
			
			//home phone number in record
			String homePhone = scHomePhone;
			
			//cell phone number in record
			String cellPhone = scCellPhone;
			
			//password - hardcoded and identical for all record unless changed here
			String password = "Passw0rd";
		
			//1 for active 2 for inactive - hardcoded
			String active = "1";
			
			//creates the url parameters as a string encoding them the defined charset
			String queryString = String.format("firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s",
					URLEncoder.encode(firstName, charset),
					URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset),
					URLEncoder.encode(homePhone, charset),
					URLEncoder.encode(cellPhone, charset),
					URLEncoder.encode(password, charset),
					URLEncoder.encode(active, charset)
			);
			
			//creates a new URL out of the endpoint, returnType and querystring
			
			URL employeeRecord = new URL(employee + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) employeeRecord.openConnection();
			connection.setRequestMethod("POST");
			
			//if do not get 201/create (success) throw exception
			if (connection.getResponseCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseMessage());
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));			
			
			//loop of buffer line by line until it return null meaning there are no more lines
			while (br.readLine() != null) {
				//print out each line to the screen
				System.out.println(br.readLine());
			}
			
			//close connection to API
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}//createRecord
		
}//class
		
		
		
		
	
		
		
		
		
		
		
		
		