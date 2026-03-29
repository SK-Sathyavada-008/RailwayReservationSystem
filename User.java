package RailwayReservationSystem;

public class User {
	private int id = 0;
	private String name;
	private int age;
	private boolean disabled;
	private String dis;
	private int train;
	private int compartment;
	private int seat;

	// constructor
	User(int id, String name, int age, boolean disabled, int train, int compartment, int seat){
		this.setId(id);
		this.name = name;
		this.age = age;
		this.disabled = disabled;
		dis = "no";
		if(disabled) {
			dis = "yes";
		}
		this.train = train;
		this.compartment = compartment;
		this.seat = seat;
	}

	public void display() {
		System.out.println(getId() + " | " + name + " | " + age + " | " + dis + " | " + train + " | " + compartment + " | " + seat);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public boolean getDisabled() {
		return disabled;
	}

	public int getTrain() {
		return train;
	}

	public int getCompartment() {
		return compartment;
	}

	public int getSeat() {
		return seat;
	}

}