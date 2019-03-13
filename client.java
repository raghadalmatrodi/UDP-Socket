package socket;
import java.net.DatagramPacket;
import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.lang.*;
/** 
 * This is a Client class that uses UDP for solving HW2 Part 2 (CEN 303)
 * @author ----------------
 * Please apply the programming skills that you have learned. 
 */
public class client {

	 public static void main(String args[]) throws Exception
	   {
	      
	      DatagramSocket clientSocket = new DatagramSocket();
	      InetAddress IPAddress = InetAddress.getByName("localhost");
	      byte[] sendData = new byte[1024];
	      byte[] receiveData = new byte[1024];
	      String message = "Hey";
	     for(int i=0;i<10;i++)
	     { 
	    	 int counter=0;
	    	 sendData = message.getBytes();
		      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9999);
		      clientSocket.send(sendPacket);
		      double initialTime=System.nanoTime();
	    	
	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	      clientSocket.setSoTimeout(1000);
	     
	      try{
	      clientSocket.receive(receivePacket);
	      double recivedTime=System.nanoTime();
	      String receivedSentence = new String(receivePacket.getData());
	      System.out.println("FROM SERVER:" + receivedSentence);
	      System.out.println("Round trip= " + rtt( initialTime,recivedTime));
	      }
	      
	     catch(SocketTimeoutException e) {
	    	 double recivedTime=System.nanoTime();
	    	 counter++;
	    	 System.out.println("failed packet");
	    	 System.out.println("Round trip= " + rtt( initialTime,recivedTime));
	     }
	      i=i-counter;
	     
	      
	   }
	      clientSocket.close();
	   }
	 public static double rtt(double time1,double time2) {
		 return (time2-time1)/1000000000;
	 }
	  
	 
}



