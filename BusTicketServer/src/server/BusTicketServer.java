package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BusTicketServer {

	final private int port;
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	final private Bus bus;

	public BusTicketServer(int port) {
		this.port = port;
		bus = new Bus();
	}

	public static void main(String[] args) {
		BusTicketServer s = new BusTicketServer(8080);
		s.run();
	}

	public void run() {
		openServSocket();

		while (true) {
			try {
				clientSocket = this.serverSocket.accept();
			} catch (IOException e) {
				throw new RuntimeException("Client cannot be connected - Error", e);
			}
			new Thread(() -> {
				try (Scanner in = new Scanner(clientSocket.getInputStream());
						PrintWriter out = new PrintWriter(clientSocket.getOutputStream());) {

					while (true) {
						if (in.hasNext()) {
							String name = in.nextLine();
							if (bus.bookTicket(name)) {
								System.out.println(name + " has booked seat number " + bus.bookedTicketsCount());
								out.println("A bus ticket has been booked for you!" + " Seat number: "
										+ bus.bookedTicketsCount() + ".");
								out.flush();
							} else {
								System.out.println(name + " tried to book a seat but the bus is full.");
								out.println("The bus is full.");
								out.flush();
							}
						}
					}
				} catch (IOException e) {
				}
			}).start();
		}
	}

	private void openServSocket() {
		try {
			this.serverSocket = new ServerSocket(this.port);
			System.out.println("Server status: ON");
		} catch (IOException e) {
			throw new RuntimeException("Not able to open this port", e);
		}
	}
}