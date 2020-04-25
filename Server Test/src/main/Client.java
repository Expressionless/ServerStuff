package main;

import java.io.*;
import java.net.Socket;

import static main.NetworkConstants.*;

public class Client {

	public static final int TIMEOUT = 100;
	
	private int client_id;
	private Socket socket;
	
	private String ip;
	private int port;
	
	private BufferedReader in;
	private PrintWriter out;
		
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
		
		if(connect(ip, port))
			System.out.println("Connected to: " + ip + ":" + port);
		else System.out.println("Failed to connect to: " + ip + ":" + port);
		
		if(socket != null) {
			//Ask for a client id
			client_id = Integer.parseInt(request(CLIENT_ID));
		}
	}
	
	public boolean connect(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			
			// Convert bytes to chars
			InputStreamReader input = new InputStreamReader(socket.getInputStream());
			
			// Convert chars to strings
			in = new BufferedReader(input);
			
			//Initialize the output
			out = new PrintWriter(socket.getOutputStream());
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public String request(int packet_id) {
		out.print(packet_id);
		
		int timer = 0;
		
		String receive = null;
		
		try {
			while(timer < TIMEOUT) {
				if(in.ready())
					return in.readLine();
				timer++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		return receive;
	}
	
	
	public int getID() {
		return client_id;
	}
	
	public Socket getSocket() {
		return socket;
	}
}
