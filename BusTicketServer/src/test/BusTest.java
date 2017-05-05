package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import server.Bus;

public class BusTest {
	@Test
	public void shouldCorrectlyBookTicketsConcurrently() {
		Bus b = new Bus();
		for(int i = 0 ; i < 10 ; i++) {
			new Thread(() -> {
				b.bookTicket("RandomName");
			}).start();
		}
		int count = 0;
		for(String name : b.getBookedSeats()) {
			if(name != null) {
				count++;
			}
		}
		assertEquals(count, b.bookedTicketsCount());
	}
	
	@Test
	public void shouldCorrectlyCountTickets() {
		Bus b = new Bus();
		for(int i = 0 ; i < 10 ; i++) {
			b.bookTicket("Name " + i);
		}
		assertEquals(b.bookedTicketsCount(), 10);
	}
	
	@Test
	public void shouldCorrectlyCheckIfFullWhenBusFull() {
		Bus b = new Bus();
		for(int i = 0 ; i < 50 ; i++) {
			b.bookTicket("Name " + i);
		}
		assertTrue(b.isFull());
	}
	
	@Test
	public void shouldCorrectlyCheckIfFullWhenBusNotFull() {
		Bus b = new Bus();
		assertFalse(b.isFull());
	}
	
	@Test
	public void shouldCorrectlyReturnBookedTickets() {
		Bus b = new Bus();
		boolean check = true;
		
		for(int i = 0 ; i < 10 ; i++) {
			b.bookTicket("Name " + i);
		}
		
		String[] names = b.getBookedSeats();
		
		for(int i = 0 ; i < 10 ; i++) {
			if(!names[i].equals("Name " + i)) {
				check = false;
				break;
			}
		}
		
		assertTrue(check);
	}
	
}
