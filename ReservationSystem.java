package RailwayReservationSystem;
import java.util.Scanner;
import java.io.*;

public class ReservationSystem {
	DSA_Applications ds = new DSA_Applications();
	// total available trains
	int totalTrains = 3;
	String[] train = {"Vande Bharat", "Godavari Express", "Venkatadri Express"};
	// matrix for compartment and seats - 3 compartments, each with 6 seats
	String[][] compartment_seat1 = new String[3][6];
	String[][] compartment_seat2 = new String[3][6];
	String[][] compartment_seat3 = new String[3][6];
	User[] hashTable = new User[68];
	String filePath = "passengers.txt";

	ReservationSystem() {
		for(int i = 0; i<68; i++) {
			hashTable[i] = null;
		}
		// load existing passenger data from file on startup
		loadFromFile();
	}

	// total 54 users
	// each train 18 users
	User[] user = new User[54];
	int userCount = 0;
	String[] user_name = new String[54];
	int id = -1;

	// book ticket - create a user and add to linked list 
	public void bookTicket(Scanner sc) {
		id++;
		System.out.print("Enter name : ");
		String name = sc.next();
		System.out.print("Enter age : ");
		user_name[id] = name;
		int age = sc.nextInt();
		System.out.print("Physically Disabled (yes/no) : ");
		String dis = sc.next();
		boolean disabled = false;;
		if(dis.equalsIgnoreCase("yes")) {
			disabled = true;
		}

		for(int i = 0; i<3; i++) {
			System.out.println((i+1) + ". " + train[i]);
		}
		boolean valid = false;
		int train_num;
		boolean success = true;
		
		do {
			System.out.print("Enter train number : ");
			train_num = sc.nextInt();
			if(train_num > train.length || train_num <= 0) {
				System.out.print("Invalid train number");
			} else {
				valid = true;
			}
		} while(valid == false);
		boolean valid_comp = false;
		int comp_num = 0, seat = 0;
		boolean valid_seat = false;
		if(age > 60 || disabled == true) {
			displayLowerBerth(train_num);
		}
		displayAvailableSeats(train_num);
		switch(train_num) {
			case 1:
				do {
					System.out.print("Enter compartment number : ");
					comp_num = sc.nextInt();
					if(comp_num > compartment_seat1.length || comp_num <= 0) {
						System.out.print("Invalid compartment number");
					} else {
						valid_comp = true;
					}
				} while(valid_comp == false);	
				do {
					System.out.print("Enter seat number : ");
					seat = sc.nextInt();
					if((seat > compartment_seat1[0].length || seat <= 0)) {
						System.out.print("Invalid seat number");
					} else {
						valid_seat = true;
					}
				} while(valid_seat == false);
				if(compartment_seat1[comp_num - 1][seat - 1] == null) {
					compartment_seat1[comp_num - 1][seat - 1] = name;
				} else {
					System.out.println("Seat already occupied");
					success = false;
				}		
				break;
			case 2:
				do {
					System.out.print("Enter compartment number: ");
					comp_num = sc.nextInt();
					if(comp_num > compartment_seat2.length || comp_num <= 0) {
						System.out.print("Invalid compartment number");
					} else {
						valid_comp = true;
					}
				} while(valid_comp == false);
				do {
					System.out.print("Enter seat number : ");
					seat = sc.nextInt();
					if(seat > compartment_seat2[0].length || seat <= 0) {
						System.out.print("Invalid seat number");
					} else {
						valid_seat = true;
					}
				} while(valid_seat == false);
				if(compartment_seat2[comp_num - 1][seat - 1] == null) {
					compartment_seat2[comp_num - 1][seat - 1] = name;
				} else {
					System.out.println("Seat already occupied");
					success = false;
				}
				break;
			case 3: 
				do {
					System.out.print("Enter compartment number : ");
					comp_num = sc.nextInt();
					if(comp_num > compartment_seat3.length || comp_num <= 0) {
						System.out.print("Invalid compartment number");
					} else {
						valid_comp = true;
					}
				} while(valid_comp == false);
				do {
					System.out.print("Enter seat number : ");
					seat = sc.nextInt();
					if(seat > compartment_seat3[0].length || seat <= 0) {
						System.out.print("Invalid seat number");
					} else {
						valid_seat = true;
					}
				} while(valid_seat == false);
				if(compartment_seat3[comp_num - 1][seat - 1] == null) {
					compartment_seat3[comp_num - 1][seat - 1] = name;
				} else {
					success = false;
					System.out.println("Seat already occupied");
				}
				break;
			default : 
				System.out.println("Invalid Case");
		}

		if(success) {
			User u = new User(id, name, age, disabled, train_num, comp_num, seat);
			addHashTable(id, u);
			ds.insertNode(u);
			user[id] = u;
			System.out.println("Seat booked successfully");
			u.display();
			// append new passenger to file
			appendToFile(u);
		}
	}


	// display train seats 
	public void displayTrain(Scanner sc) {
		System.out.print("Enter train number : ");
		int t = sc.nextInt();
		if(t <=0 || t > 3) {
			System.out.println("Invalid train number");
		}

		if(t == 1) {
			for(int i = 0; i<3; i++) {
				System.out.println("Compartment " + (i+1) + " : ");
				for(int j = 0; j<6; j++) {
					System.out.println("Seat " + (j+1) + " - " + compartment_seat1[i][j]);
				}
				System.out.println();
			}
		}

		if(t == 2) {
			for(int i = 0; i<3; i++) {
				System.out.println("Compartment " + (i+1) + " : ");
				for(int j = 0; j<6; j++) {
					System.out.println("Seat " + (j+1) + " - " + compartment_seat2[i][j]);
				}
				System.out.println();
			}
		}

		if(t == 3) {
			for(int i = 0; i<3; i++) {
				System.out.println("Compartment " + (i+1) + " : ");
				for(int j = 0; j<6; j++) {
					System.out.println("Seat " + (j+1) + " - " + compartment_seat3[i][j]);
				}
				System.out.println();
			}
		}
	}

	

	// create id array
	public int[] idarray(int id) {
		int[] idarray = new int[id+1];
		for(int i = 0; i<=id; i++) {  
			idarray[i] = i; 
		}
		return idarray;
	}

	// binary search by id
	public void binarySearchId(Scanner sc) {
		int[] idarr = idarray(id);
		System.out.print("Enter passenger id : ");
		int searchId = sc.nextInt();    
		int index = ds.binarySearch(idarr, searchId);
		if(index == -1) {
			System.out.println("User does not exist");
		} else {
			User currentUser = user[index];
			currentUser.display();
		}
	}

	public void linearSearchName(Scanner sc) {
		System.out.print("Enter name : ");
		String name = sc.next();
		int index = ds.linearSearch(user_name, name);
		if(index == -1) {
			System.out.println("User does not exist");
		} else {
			User currentUser = user[index];
			currentUser.display();
		}
	}

	public void displayAvailableSeats(int t) {
		if(t <=0 || t > 3) {
			System.out.println("Invalid train number");
		}
		if(t == 1) {
			for(int i = 0; i<3; i++) {
				System.out.println("Compartment " + (i+1) + " : ");
				for(int j = 0; j<6; j++) {
					if(compartment_seat1[i][j] == null)
						System.out.println("Seat " + (j+1) + " - Available");    
					else 
						continue;
				}
				System.out.println();
			}
		}

		if(t == 2) {
			for(int i = 0; i<3; i++) {
				System.out.println("Compartment " + (i+1) + " : ");
				for(int j = 0; j<6; j++) {
					if(compartment_seat2[i][j] == null)
						System.out.println("Seat " + (j+1) + " - Available"); 
					else 
						continue;
				}
				System.out.println();
			}
		}

		if(t == 3) {
			for(int i = 0; i<3; i++) {
				System.out.println("Compartment " + (i+1) + " : ");
				for(int j = 0; j<6; j++) {
					if(compartment_seat3[i][j] == null)
						System.out.println("Seat " + (j+1) + " - Available");  
					else 
						continue;
				}
				System.out.println();
			}
		}
	}

	// display lower berth priority 
	public void displayLowerBerth(int t) {
		if(t <=0 || t > 3) {
			System.out.println("Invalid train number");
		} else {
			System.out.println("Available Lower Berth Seats");
			if(t == 1) {
				for(int i = 0; i<3; i++) {
					System.out.println("Compartment " + (i+1) + " : ");
					if(compartment_seat1[i][0] == null)
						System.out.println("Seat 1" + " - Available");
					if(compartment_seat1[i][4] == null)
						System.out.println("Seat 5" + " - Available");
				}
				System.out.println();
			}
		}
	}

	
	// sort based on passenger name
	public void sort(Scanner sc) {
		if(id < 0) {
			System.out.println("No passengers to sort."); 
			return;
		}
		String[] temparr = new String[id + 1];  
		for(int i = 0; i<=id; i++) {     
			temparr[i] = user_name[i];
		}
		System.out.println("1. Bubble Sort\n2. Insertion Sort\n3. Quick Sort");
		System.out.print("Enter choice : ");
		int ch = sc.nextInt();
		if(ch == 1) {
			temparr = ds.bubbleSort(temparr);
			for(int i = 0; i<temparr.length; i++) {
				System.out.println(temparr[i]);  
			}
		} else if(ch == 2) {
			temparr = ds.insertionSort(temparr);
			for(int i = 0; i<temparr.length; i++) {
				System.out.println(temparr[i]); 
			}
		} else if(ch == 3) {
			temparr = ds.quicksort(temparr, 0, id);  
			for(int i = 0; i<temparr.length; i++) {
				System.out.println(temparr[i]);
			}
		} else {
			System.out.println("Invalid Choice");
		}
	}

	// pop last added user - stack
	public void popLastUser() {
		if(id < 0) {
			System.out.println("No passengers to pop.");
			return;
		}
		int top = id;
		user[top] = null;
		user_name[top] = null;
		System.out.println("Passenger " + (id) + " popped out");
		if(id == 0) {
			id = -1;   
		} else {
			id--;
		}
		// rewrite file without the popped passenger
		rewriteFile();
	}

	// hashing
	public int hashfunction(int id) {
		return id % 68;
	}

	public void addHashTable(int id, User u) {
		int index = hashfunction(id);
		if(hashTable[index] == null) {
			hashTable[index] = u;
		} else {
			int si = index;
			int temp = (si + 1) % 68;
			while(temp != si) {
				if(hashTable[temp] == null) {
					hashTable[temp] = u;
					return;
				}
				temp = (temp + 1) % 68;
			}
			System.out.println("Hash table is full");
		}
	}

	// cancel ticket - search by name, confirm, then delete
	public void cancelTicket(Scanner sc) {
		System.out.print("Enter passenger name to cancel : ");
		String name = sc.next();
		int index = ds.linearSearch(user_name, name);
		if(index == -1) {
			System.out.println("Passenger not found");
			return;
		}
		User u = user[index];
		System.out.println("Passenger found : ");
		u.display();
		System.out.print("Confirm cancel? (yes/no) : ");
		String confirm = sc.next();
		if(!confirm.equalsIgnoreCase("yes")) {
			System.out.println("Cancellation aborted");
			return;
		}

		// free the seat in the compartment matrix
		int t = u.getTrain();
		int comp = u.getCompartment();
		int seat = u.getSeat();
		if(t == 1) compartment_seat1[comp - 1][seat - 1] = null;
		else if(t == 2) compartment_seat2[comp - 1][seat - 1] = null;
		else if(t == 3) compartment_seat3[comp - 1][seat - 1] = null;
		// remove from user arrays
		user[index] = null;
		user_name[index] = null;
		// remove from linked list
		if(ds.head != null) {
			if(ds.head.data.getId() == u.getId()) {
				ds.head = ds.head.link;
			} else {
				Node prev = ds.head;
				Node curr = ds.head.link;
				while(curr != null) {
					if(curr.data.getId() == u.getId()) {
						prev.link = curr.link;
						break;
					}
					prev = curr;
					curr = curr.link;
				}
			}
		}

		// remove from hash table
		int hIndex = hashfunction(u.getId());
		for(int i = 0; i < 68; i++) {
			int probe = (hIndex + i) % 68;
			if(hashTable[probe] != null && hashTable[probe].getId() == u.getId()) {
				hashTable[probe] = null;
				break;
			}
		}
		System.out.println("Ticket cancelled successfully");
		// rewrite file without the cancelled passenger
		rewriteFile();
	}

	// append a single passenger to the file (called after booking)
	public void appendToFile(User u) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
			bw.write(u.getId() + "," + u.getName() + "," + u.getAge() + "," + u.getDisabled() + "," + u.getTrain() + "," + u.getCompartment() + "," + u.getSeat());
			bw.newLine();
		} catch(IOException e) {
			System.out.println("Error writing to file : " + e.getMessage());
		}
	}

	// rewrite the entire file from current user array (called after cancellation)
	public void rewriteFile() {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
			for(int i = 0; i <= id; i++) {
				if(user[i] != null) {
					User u = user[i];
					bw.write(u.getId() + "," + u.getName() + "," + u.getAge() + "," + u.getDisabled() + "," + u.getTrain() + "," + u.getCompartment() + "," + u.getSeat());
					bw.newLine();
				}
			}
		} catch(IOException e) {
			System.out.println("Error rewriting file : " + e.getMessage());
		}
	}

	// load all passengers from file on startup
	public void loadFromFile() {
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while((line = br.readLine()) != null) {
				String[] data = line.split(",");
				int uid      = Integer.parseInt(data[0]);
				String uname = data[1];
				int uage     = Integer.parseInt(data[2]);
				boolean udis = Boolean.parseBoolean(data[3]);
				int utrain   = Integer.parseInt(data[4]);
				int ucomp    = Integer.parseInt(data[5]);
				int useat    = Integer.parseInt(data[6]);
				User u = new User(uid, uname, uage, udis, utrain, ucomp, useat);

				// restore into all data structures
				id = uid;
				user[id] = u;
				user_name[id] = uname;
				addHashTable(id, u);
				ds.insertNode(u);
				// restore seat in compartment matrix
				if(utrain == 1) compartment_seat1[ucomp - 1][useat - 1] = uname;
				else if(utrain == 2) compartment_seat2[ucomp - 1][useat - 1] = uname;
				else if(utrain == 3) compartment_seat3[ucomp - 1][useat - 1] = uname;
			}
		} catch(FileNotFoundException e) {
		} catch(IOException e) {
			System.out.println("Error reading file : " + e.getMessage());
		}
	}

	// display hash table
	public void displayHashTable() {
		System.out.println("\nHash Table:");
		System.out.println("Index | ID | Name | Age | Disabled | Train | Compartment | Seat");
		System.out.println("------------------------------------------------------------------");
		for(int i = 0; i < 68; i++) {
			if(hashTable[i] != null) {
				System.out.print(i + "     | ");
				hashTable[i].display();
			}
		}
	}

}