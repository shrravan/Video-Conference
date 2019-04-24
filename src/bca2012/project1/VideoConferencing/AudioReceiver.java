package bca2012.project1.VideoConferencing;
import java.net.DatagramPacket;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

//This class plays the received bytes by opening Audio Format for each sender (only once)
public class AudioReceiver implements Runnable 
{
	private byte[] receiveBuffer;   // Buffer to temporarly store incoming bytes. 
	byte[] inSound;
	SourceDataLine Speaker = null;  //it provides a method for writing audio data
	AudioFormat af= null;
	private DatagramPacket inPacket;
	
    //Opens the Audio Format for each sender once
	public void open() throws LineUnavailableException
	{
		af = new AudioFormat(8000.0f,8,1,true,false);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);  /*Data line is used for output of audio by means of the SourceDataLine  which allow 
		                                                                     program to write data.*/
		Speaker = (SourceDataLine)AudioSystem.getLine(info);              // AudioSystem class acts as the entry point to the sampled-audio system resources.
		Speaker.open(af);                                                 //getLine- Obtains a line that matches the description in the specified Line.Info object.
		Speaker.start();
	}
	public AudioReceiver(DatagramPacket in) throws LineUnavailableException
	{
		inPacket= in;
		open();
	} 
	public DatagramPacket getInPacket()
	{
		return inPacket;
	}
	public void setInPacket(DatagramPacket inPacket)
	{
		this.inPacket = inPacket;
	}
	
	//plays the incoming audio bytes in speaker
	public void run() 
	{
		try
		{
			receiveBuffer = inPacket.getData();                    
			Speaker.write(receiveBuffer, 0,receiveBuffer.length);	/*Writes audio data to the Speaker. The requested number of bytes of data are read 
			                                                         from receiveBuffer starting at the given offset(0) .*/
		}
		catch( Exception e )
		{
			e.printStackTrace();
			System.out.println( "AudioReceiver: Exception in receiving packet: "+ e.getMessage() );
		}
	}
}