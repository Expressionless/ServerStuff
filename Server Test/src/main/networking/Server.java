package main.networking;

import static main.networking.Constants.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

	private ArrayList<Socket> connect = new ArrayList<Socket>();

	private ServerSocket serverSocket;

	public Server(int slots, int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}

		connect.add(accept(5000));
		int id = connect.size() - 1;
		System.out.println(send(id, Integer.toString(id)));
	}

	public Socket accept(int port) {
		Socket s = null;

		try {
			s = serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	public void parseRequest(int clientID) {
		InputStreamReader in;

		int request = -1;

		try {
			in = new InputStreamReader(connect.get(clientID).getInputStream());
			BufferedReader bf = new BufferedReader(in);

			while (bf.ready()) {
				String str = bf.readLine();
				System.out.println(connect.get(clientID).getPort() + ": " + str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			PrintWriter pr = new PrintWriter(connect.get(clientID).getOutputStream());

			// Parse request
			switch (request) {
			case CLIENT_ID:
				pr.println(clientID);
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean send(int clientID, String packet) {
		try {
			PrintWriter pr = new PrintWriter(connect.get(clientID).getOutputStream());
			pr.print(packet);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}
}
