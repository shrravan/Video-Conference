package bca2012.project1.VideoConferencing;
//This class is used to initialize the socket, Multicast Address for sending or receiving video data. 
//WebcamImage class is to capture images from webcam

import java.net.InetAddress;
import java.net.MulticastSocket;

public class VideoConnection
{
	private static int first= 0;  //To call status & webcamImage classes only once ,till user logsout!!!
	private static InetAddress address= null;
	private static int cport= 9060;	//common port 
	private static MulticastSocket socket = null;

	public static InetAddress getAddress()
	{
		return(address);
	}

	public static int getPort()
	{
		return(cport);
	}

	public static MulticastSocket getSocket()
	{
		return socket;
	}

	public VideoConnection() throws InterruptedException
	{
		try
		{
			System.out.println("In constructor");
			socket = new MulticastSocket(cport);
			address = InetAddress.getByName("224.2.2.5");
			socket.joinGroup(address);
		}
		catch(Exception e)
		{
			System.out.println("constructor Error: " + e.getMessage());
			e.printStackTrace();
		}
		if(first == 0)
		{
			first= 1;
			new VideoStatus();
			new ImageSender();
		}
	}
}
