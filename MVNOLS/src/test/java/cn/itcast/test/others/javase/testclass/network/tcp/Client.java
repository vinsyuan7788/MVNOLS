package cn.itcast.test.others.javase.testclass.network.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 	This is a class to serve as client: can be both transmitter & receiver
 * 	1. "\r\n" is necessary for the line recognition of server
 * 	2. Flush stream to make sure the data written to buffer is sent to server
 */
public class Client {

	public static void main (String[] args) throws Exception {
		
		/*	Set up a client through TCP	 */
		Socket socket = new Socket(InetAddress.getLocalHost(), 9090);

		/*	Write the data that accepted from keyboard to server using I/O stream	*/
		OutputStreamWriter socketWriter = new OutputStreamWriter(socket.getOutputStream());
		BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = keyboardReader.readLine()) != null) {
			socketWriter.write(line + "\r\n");
			socketWriter.flush();
		}
		
		/*	Close the socket   */
		socket.close();
	}
}
