package bca2012.project1.VideoConferencing;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;

//This class sends the images to group(who ever has joined)
//it gets images from WebcamImage class by calling WebcamImage.getImage() method
public class MulticastVideoSender implements Runnable
{
	private static DatagramPacket outPacket= null;
	byte[] outBuf;
	private static byte[] bytes;
	public MulticastVideoSender()
	{
		Thread thread= new Thread(this);
		thread.start();
	} 

	public void run() 
	{
		InvokeSender();
		return;
	}
	
    // This method sends image bytes to network
	public static void InvokeSender()
	{
		while(true)
		{
			ByteArrayOutputStream baos= new ByteArrayOutputStream();
			try
			{
				ImageIO.write(ImageSender.getImage(),"JPEG", baos); //Writes an image in JPEG format to an ByteArrayOutputStream
				baos.flush();
				bytes = baos.toByteArray();              //Converts ByteArrayStream to bytes
				outPacket = new DatagramPacket(bytes, bytes.length, VideoConnection.getAddress(), VideoConnection.getPort());
				VideoConnection.getSocket().send(outPacket);
				Thread.sleep(100);     
			} 
			catch (Exception e)                
			{
				byte[] outBuf;
				String msg;
				msg =  "bye";
				outBuf = msg.getBytes();
				ImageSender.setsend(0);
				outPacket = new DatagramPacket(outBuf, outBuf.length, VideoConnection.getAddress(), VideoConnection.getPort());
				try
				{
					VideoConnection.getSocket().send(outPacket);
				}
				catch(IOException e1) 
				{
					e1.printStackTrace();
				}

				System.out.println("Error In MulticastSender: "+e.getMessage());
				return;
			}
		}
	}
}
