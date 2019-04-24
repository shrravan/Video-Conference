package bca2012.project1.VideoConferencing;
import java.net.InetAddress;
import java.net.MulticastSocket;

//This class Intializes socket and InetAddress for sending or receiving Audio data
//MulticastAudioSender to transmit audio data
public class AudioConnection
{
	private static InetAddress address= null;
	private static int cport= 7778;	//common port 
	private static MulticastSocket socket = null;
	private static int once= 0;     //To ensure,audio_status thread is created only once till user log out's!!!

	public AudioConnection()
	{
		try
		{
			socket = new MulticastSocket(cport);
			address = InetAddress.getByName("235.1.1.1");
			socket.joinGroup(address);
			if(MyPreferences.getAudio() == 0)  //Enters when user press unmute
			{
				MulticastAudioSender ms= new MulticastAudioSender();
				new Thread(ms).start();
			}
			if(once == 0)
			{
				AudioStatus s= new AudioStatus();
				new Thread(s).start();
				once++;
			}
		}
		catch(Exception e)
		{
			System.out.println("constructor Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

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
}