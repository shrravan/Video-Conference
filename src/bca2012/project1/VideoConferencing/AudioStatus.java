package bca2012.project1.VideoConferencing;
import java.net.DatagramPacket;

import java.net.InetAddress;
import java.util.HashMap;

import javax.sound.sampled.LineUnavailableException;

//This class is used to find who is sending audio data and 
//create referece for each sender by creating Object(new AudioReceiver).

public class AudioStatus implements Runnable
{
	private static HashMap<String, AudioReceiver > list;
	byte[] Buf;

	public AudioStatus() throws LineUnavailableException
	{
		list= new HashMap<String, AudioReceiver >();
	}
	//This run() receives audio packets and sends this audio data to AudioReceiver to play the incoming audio bytes
	public void run() 
	{
		try
		{
			while(true)
			{
				Buf= new byte[4096];
				DatagramPacket datagramPacket= new DatagramPacket(Buf, Buf.length);  //Constructs a DatagramPacket for receiving packets 
				AudioConnection.getSocket().receive(datagramPacket);                      //To receive packet 
				String ipAddressString= datagramPacket.getAddress().toString();
				if(datagramPacket.getAddress().equals(InetAddress.getLocalHost()))  //Discards if the packet is coming from the same system(Local Host)
					continue;
				if(list.size() < 10)//only for 9 Clients
				{
					if(list.get(ipAddressString) == null)                    //if sender's IPaddress is not present in HashMap enter inside
					{
						AudioReceiver rcv= new AudioReceiver(datagramPacket);
						list.put(ipAddressString, rcv);                      //Inserts the IPaddress of the sender if it doesn't exist in hashmap and creates reference for this Ipaddress
						new Thread(rcv).start();                              
					}
					else
					{
						list.get(ipAddressString).setInPacket(datagramPacket);  // Gets the present sender IpAddress and sets the packet that he sends
						new Thread(list.get(ipAddressString)).start();
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("AudioStatus Error: "+e.getMessage());
			e.printStackTrace();
		}
	}
}
