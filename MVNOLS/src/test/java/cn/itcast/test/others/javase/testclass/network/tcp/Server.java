package cn.itcast.test.others.javase.testclass.network.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;

/**
 * 	This is a class to serve as client: can be both transmitter & receiver
 */
public class Server {

	public static void main (String[] args) throws Exception {
		
		/*	Set up a server through TCP	 */
		ServerSocket serverSocket = new ServerSocket(9090);
	
		/*	Once server accepts data, output it to the console	 */
		BufferedReader socketReader = new BufferedReader(new InputStreamReader(serverSocket.accept().getInputStream()));	
		String line = null;
		while ((line = socketReader.readLine()) != null) {
			System.out.println("Server accepted: " + line);
		}
		
		/*	Close the server socket	 */
		serverSocket.close();
	}
}
