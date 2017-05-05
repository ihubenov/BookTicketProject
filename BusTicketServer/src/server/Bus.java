package server;

public class Bus {
	private String[] seats;
	private int bookedTickets;
	private final int CAPACITY;

	public Bus() {
		bookedTickets = 0;
		CAPACITY = 50;
		seats = new String[CAPACITY];
	}

	synchronized public boolean bookTicket(String name) {
		if(bookedTickets < CAPACITY) {
			seats[bookedTickets++] = name;
			return true;
		}
		return false;
	}

	public int bookedTicketsCount() {
		return bookedTickets;
	}
	
	public boolean isFull() {
		return bookedTickets == CAPACITY;
	}
	
	public String[] getBookedSeats() {
		return seats;
	}

}
