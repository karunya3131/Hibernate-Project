package com.domain.com.domain.javaproject;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/* To differentiate java file and database related java file we use @Entity
* through the database know to which class the object created belongs.
* @DynamicUpdate, avoids checking for changes in values in all the columns,
* by checking and updating only the specific column value that is said to update.*/
@Entity
@DynamicUpdate
public class AirlineReservationSystem {

	private static  Scanner constant = new Scanner(System.in);
	/* @Id must be given to the entity classes, in which makes
	* the attributes to act as primary key.In @GeneratedValue, what
	* type of primary key to be generated is given in it. Here, primary key
	* type IDENTITY is given which starts by 1 and increments by 1.*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer airline_Id;

	/* @Column helps to change the column name as we need and 
	* avoids attribute name to be taken as column name.*/
	@Column(name = "airline_name")
	private String airline_Name;

	@Column(name = "airline_origin", unique = true)
	private String origin;

	@Column(name = "airline_destiny", unique = true)
	private String destination;

	private Integer seating_availability;

	private Integer fare;

	/* loginDetails is a non static method and also a parameterized method 
	 * Inside this method we have a if condition which checks whether the entered
	* admin_name and password matches the given values.*/
	public  void loginDetails(String admin_name,String admin_password) 
	{
		if(admin_name.equals("ROYAL") && admin_password.equals("karunya@31"))  
		{
			System.out.println("\nYOU HAVE SUCCESSFULLY LOGGED IN..");
			System.out.println("\n..........| WELCOME TO ROYAL AIRLINES |..........\n");
			//These statement are used to get the input from the user to perform the followings.
			System.out.println(" * TO ADD AN AIRLINE      --> PRESS 1");
			System.out.println(" * TO SEARCH AN AIRLINE   --> PRESS 2");
			System.out.println(" * TO DELETE AN AIRLINE   --> PRESS 3");
			System.out.println(" * TO UPDATE AN AIRLINE   --> PRESS 4");
			System.out.println(" * TO LOGOUT              --> PRESS 5");

		}
		else
		{
			System.out.println("\nPLEASE CHECK YOUR CREDENTIALS");
			checkingcredentials();
		}
	}
	
	public void checkingcredentials() {

		System.out.print("\nENTER THE ADMIN NAME: ");
		String admin_name = constant.next();

		System.out.print("\nENTER THE ADMIN PASSWORD: ");
		String admin_password = constant.next();

		loginDetails(admin_name,admin_password);
	}
	public void toLogOut() {

		System.out.println("Logged out successfully.");

	}

	
   /* "createAirline" method is used to insert airlines details to database.
	* "beginTransaction()" is used to start and record the process. The details
	* enetered is passed through "getAirlineReservationSystem()". "commit()"
	* is used to make the permanent changes in the database".
	*/
	public void createAirline(Session session) {

		session.beginTransaction();
		@SuppressWarnings("deprecation")
		Integer id = (Integer) session.save(getAirlineReservationSystem());//internally it is airline(object)
		System.out.println("Airline is created with id :"+id);
		session.getTransaction().commit();

	}
	
	
	/*" getAirlineReservationSystem()" performs operation of getting
	* input from the user and returning it through object of class type.*/
	public AirlineReservationSystem getAirlineReservationSystem() {

		AirlineReservationSystem airline = new AirlineReservationSystem();

		System.out.println("ENTER THE AIRLINE_NAME:"); 
		constant.nextLine();
		String airline_Name = constant.nextLine();
		airline.setAirline_Name(airline_Name);
		System.out.println("ENTER THE AIRLINE_ORIGIN:");
		String origin = constant.nextLine();
		airline.setOrigin(origin);
		System.out.println("ENTER THE AIRLINE_DESTINATION:");
		String destination = constant.nextLine();
		airline.setDestination(destination);
		System.out.println("ENTER THE AIRLINE_SEATING_CAPACITY:");
		Integer availability = constant.nextInt();
		airline.setSeating_availability(availability);
		System.out.println("ENTER THE AIRLINE_FARE:");
		Integer fare = constant.nextInt();
		airline.setFare(fare);
		
		return airline;

	}
	
	/*"deleteById" method is used to delete the airline to database by
	* entering specific id. If the id entered is available, it performs the
	* specified operation or it will display the id not found prompt.
	* "beginTransaction()" is used to start and record the process.
	* "commit()"is used to make the permanent changes in the database.*/
	public void deleteById(Session session) {
		System.out.println("Enter the Airline_id:");
		int airlineId = constant.nextInt();
		AirlineReservationSystem airline = session.get(AirlineReservationSystem.class,airlineId);

		if(airline != null) {
			session.beginTransaction();
			session.remove(airline);
			session.getTransaction().commit();
			System.out.println("Airline Detail is deleted successfully. ");
		}
		else {
			System.out.println("Airline id does not exist");

		}	
	}
	
	/*"updateById" method is used to update the airline to database by
	* entering specific id. If the id entered is available, it performs the
	* specified operation or it will display the id not found prompt.
	* "beginTransaction()" is used to start and record the process.
 	* "commit()" is used to make the permanent changes in the database.*/
	public void updateById(Session session) {
		System.out.println("Enter the Airline_id:");
		int airlineId = constant.nextInt();

		AirlineReservationSystem airline = session.get(AirlineReservationSystem.class,airlineId);
		if(airline != null) {
			session.beginTransaction();
			System.out.println("ENTER THE AIRLINE_ORIGIN TO UPDATE :");
			String origin = constant.next();
			airline.setOrigin(origin);
			System.out.println("ENTER THE AIRLINE_DESTINATION TO UPDATE:");
			String destination = constant.next();
			airline.setDestination(destination);
			System.out.println("ENTER THE AIRLINE_SEATING_CAPACITY TO UPDATE:");
			Integer availability = constant.nextInt();
			airline.setSeating_availability(availability);
			System.out.println("ENTER THE AIRLINE_FARE:");
			Integer Fare = constant.nextInt();
			airline.setFare(Fare);
			session.persist(airline);
			session.getTransaction().commit();
			System.out.println("AIRLINE DETAILS ARE UPDATED ");
		}
		else {
			System.out.println("Airline id does not exist");

		}	
	}
	
	 /*"displayById" method is used to display the airline to database by
		* entering specific id. If the id entered is available, it performs the
		* specified operation or it will display the id not found prompt.
		* "beginTransaction()" is used to start and record the process.
	 	* "commit()" is used to make the permanent changes in the database.*/
	public void displayById(Session session){
		System.out.println("Enter the Airline_id:");
		int airlineId = constant.nextInt();
		
		AirlineReservationSystem airline = session.get(AirlineReservationSystem.class,airlineId);
		if(airline != null) {
			System.out.println(airline);
		}
		else {
			System.out.println("Airline id does not exist");

		}
	}

	/*Here we used setter method for all the attributes  to set the value
	 *This keyword is refers to the current class object and also 
	 * as the names are similar we used this keyword.
	 *Here we used getter method for  all the attributes to return it's value.*/
	public Integer getAirline_Id() {
		return airline_Id;
	}

	public void setAirline_Id(Integer airline_Id) {
		this.airline_Id = airline_Id;
	}

	public String getAirline_Name(){
		return airline_Name;
	}

	public void setAirline_Name(String airline_Name) {
		this.airline_Name = airline_Name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getSeating_availability() {
		return seating_availability;
	}

	public void setSeating_availability(Integer seating_availability) {
		this.seating_availability = seating_availability;
	}

	public Integer getFare() {
		return fare;
	}

	public void setFare(Integer fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "AirlineReservationSystem [airline_Id=" + airline_Id + ", airline_Name=" + airline_Name + ", origin="
				+ origin + ", destination=" + destination + ", seating_availability=" + seating_availability + ", fare="
				+ fare + "]";
	}
}
