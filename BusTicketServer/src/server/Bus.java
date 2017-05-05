package server;

public class Bus {
	private String[] seats;
	private int bookedTickets;
	private final int CAPACITY;
	private boolean isFull;

	public Bus() {
		bookedTickets = 0;
		CAPACITY = 1;
		seats = new String[CAPACITY];
		isFull = false;
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
		return isFull;
	}
}
