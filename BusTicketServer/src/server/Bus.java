package server;

public class Bus {
	private String[] seats;
	private int bookedTickets;
	private final int CAPACITY;
	private boolean isFull;

	public Bus() {
		bookedTickets = 0;
		CAPACITY = 50;
		seats = new String[CAPACITY];
		isFull = false;
	}

	synchronized public void bookTicket(String name) {
		seats[bookedTickets++] = name;
		if(bookedTickets == CAPACITY) {
			isFull = true;
		}
	}

	public int bookedTicketsCount() {
		return bookedTickets;
	}

	public boolean isFull() {
		return isFull;
	}
}
