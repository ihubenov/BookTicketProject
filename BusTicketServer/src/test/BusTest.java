package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//import java.util.Vector;

import static org.junit.Assert.assertFalse;
import org.junit.Test;
import server.Bus;

public class BusTest {
	@Test
	public void shouldCorrectlyBookTicketsConcurrently() {
		Bus b = new Bus();
		Thread[] t = new Thread[50];
		int count = 0;
		
		for(int k = 0 ; k < 50 ; k++) {
			t[k] = new Thread(() -> {
				b.bookTicket("RandomName");
			});
			t[k].start();
		}
		for(int i = 0 ; i < 50 ; i++) {
			try {
				t[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(String name : b.getBookedSeats()) {
			if(name != null) {
				count++;
			}
		}
		
		assertEquals(count, 50);
	}
	
	@Test
	public void shouldCorrectlyBookTicketsWhenExceedCapacity() {
		Bus b = new Bus();
		
		for(int i = 0 ; i < 60 ; i++) {
			b.bookTicket("Name " + i);
		}
		
		assertEquals(b.bookedTicketsCount(), 50);
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
		//Vector<String> names = b.getBookedSeats();
		
		for(int i = 0 ; i < 10 ; i++) {
			if(!names[i].equals("Name " + i)) {
			//if(!names.get(i).equals("Name " + i)) {
				check = false;
				break;
			}
		}
		
		assertTrue(check);
	}
	
}
