package main;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	private Client client;
	
	public static void main(String[] args) {
		
		Server server = null;
		
		Client client = null;
		
		System.out.print("Type: ");
		try(Scanner sc = new Scanner(System.in)) {
			String type = sc.nextLine();
			type = type.toLowerCase();
			System.out.println(type);
			if(type == "server") {
				System.out.println("Starting a server");
				server = new Server(40, 4999);
			} else if(type == "client") {
				client = new Client("localhost", 4999);
			}
						
		}
		
		if(server != null) {
			try {
				server.getServerSocket().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(client != null) {
			try {
				client.getSocket().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
