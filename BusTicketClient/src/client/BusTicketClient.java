package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class BusTicketClient {

	private final static int PORT = 8080;
	private final static String HOST = "localhost";

	public static void main(String[] args) throws IOException {
		try {
			@SuppressWarnings("resource")
			Socket socket = new Socket(HOST, PORT);

			try (Scanner chat = new Scanner(System.in);
					Scanner in = new Scanner(socket.getInputStream());
					PrintStream out = new PrintStream(socket.getOutputStream());) {

				System.out.println("Enter name: ");
				String name = chat.nextLine();
				out.println(name);
				out.flush();
				if (in.hasNext()) {
					System.out.println(in.nextLine());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException noServer) {
			System.out.println("The server might not be up at this time.");
			System.out.println("Please try again later.");
		}
	}
}