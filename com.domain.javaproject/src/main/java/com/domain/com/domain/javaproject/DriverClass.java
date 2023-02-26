/*Author: S.Karunya Ganashree
 * Project: Airline Reservation System
 * The Project performs functions such as adding,displaying,
 * searching,updating and deleting airline details to SQL database,
 * using Hibernate*/

package com.domain.com.domain.javaproject;


import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DriverClass {

	private static Scanner scan = new Scanner(System.in);	

	/* main method contains session details inorder to open the session and
	 *  perform reading , writing operations from/to database. It is surrounded by try catch
	 * to handle the exception."do while" loop has switch case in which each case contains 
	 * respective method calls to perform deletion, insertion,search and display operations.*/
	public static void main( String[] args )
	{
		/*"Configuration is a class which implements "session" interface inorder to
		 * use methods in it. "configure" is a method in hibernate, which is used to read
		 * configuration file. configuration file contains username, password, url to connect
		 * with database.The given entity class must be conveyed to database using "addAnnotatedClass()",
		 * which is a predefined method in hibernate and class name for which the table to be created in
		 * database is passed as parameter."sessionFact" contains only username and password,that is given
		 * by "SessionFactory" and using the credentials stored in "sessionFact", the session is opened using
		 * "openSession()" that helps to open the session for database.*/
		try 
		{
			Configuration config = new Configuration().configure().addAnnotatedClass(AirlineReservationSystem.class);
			SessionFactory sessionFact = config.buildSessionFactory(); 
			Session session = sessionFact.openSession();
			AirlineReservationSystem airlineobject = new AirlineReservationSystem();
			airlineobject.checkingcredentials();

			/*"adminChoice" is a variable which is declared to hold
			 * the inputed value to be passed to switch. */
			byte adminChoice ;
			do 
			{
				adminChoice = scan.nextByte();

				switch(adminChoice) 
				{

				case 1:

					airlineobject.createAirline(session);
					break;

				case 2:

					airlineobject.displayById(session);
					break;

				case 3:

					airlineobject.deleteById(session);
					break;

				case 4:

					airlineobject.updateById(session);
					break;

				case 5:

					airlineobject.toLogOut();
					break;

				}
			}
			while (adminChoice != 5) ;
			scan.close();
		}
		catch(HibernateException obj) 
		{
			//System.out.println(obj);
		}
		catch(Exception obj) 
		{
			//System.out.println(obj);
		}
	}	
}

