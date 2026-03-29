package RailwayReservationSystem;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ReservationSystem rs = new ReservationSystem();

		// menu driven program
		int choice;
		do {
			System.out.println("\nRailway Reservation System");
			System.out.println("1. Book Ticket");
			System.out.println("2. Display Trains");
			System.out.println("3. Search by Passenger ID");
			System.out.println("4. Search by Passenger Name");
			System.out.println("5. Pop Last added passenger");
			System.out.println("6. Sort");
			System.out.println("7. Hash Table");
			System.out.println("8. Cancel Ticket");
			System.out.println("0. Exit");
			System.out.print("\nEnter Choice : ");
			choice = sc.nextInt();

			switch(choice) { 
				case 1:
					rs.bookTicket(sc);
					break;
				case 2:
					rs.displayTrain(sc);
					break;
				case 3:
					rs.binarySearchId(sc);
					break;
				case 4:
					rs.linearSearchName(sc);
					break;
				case 5:
					rs.popLastUser();
					break;  
				case 6:
					rs.sort(sc);
					break;
				case 7:
					rs.displayHashTable();
					break;
				case 8:
					rs.cancelTicket(sc);
					break;
				case 0:
					System.out.println("End");
					break;
				default:
					System.out.println("Invalid Choice");
			}
		
		} while(choice != 0);
	}

}