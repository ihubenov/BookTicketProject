package server;

//import java.util.Vector;

public class Bus {
	private String[] seats;
	//private Vector<String> seats;
	private int bookedTicketsCount;
	private final int CAPACITY;

	public Bus() {
		bookedTicketsCount = 0;
		CAPACITY = 50;
		//seats = new Vector<>(CAPACITY);
		seats = new String[CAPACITY];
		//To redesign the application to be thread-safe without locking mechanisms
		//I would use vector, instead of an array (since it is a thread-safe data structure)
	}

	synchronized public boolean bookTicket(String name) {
		if(bookedTicketsCount < CAPACITY) {
			seats[bookedTicketsCount++] = name;
			//seats.add(name);
			return true;
		}
		return false;
	}

	public int bookedTicketsCount() {
		return bookedTicketsCount;
	}
	
	public boolean isFull() {
		return bookedTicketsCount == CAPACITY;
	}
	
	public String[] getBookedSeats() {
	//public Vector<String> getBookedSeats() {
		return seats;
	}

}
