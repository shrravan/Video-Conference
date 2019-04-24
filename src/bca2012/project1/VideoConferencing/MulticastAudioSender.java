package bca2012.project1.VideoConferencing;

import java.net.DatagramPacket;

import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class MulticastAudioSender implements Runnable
{
	private static DatagramPacket sendPack;
	private static final int BUFFER_SIZE = 4096;
	private static int bytesRead = 0;
	private static TargetDataLine microphone= null;       //A target data line is used to read audio data (audio capture device)
	private static byte[] soundData= new byte[BUFFER_SIZE];
	private static AudioFormat af = null;
	Timer t= new Timer();                                // To schedule tasks for future execution(sending audio data)
	//Sets the Audio Format for capturing audio
	public MulticastAudioSender() throws LineUnavailableException 
	{
		af = new AudioFormat(8000.0f,8,1,true,false);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, af);  /*Data line is used for input of audio by means of the TargetDataLine  which allow an 
                                                                            application program to read data.*/
		microphone = (TargetDataLine)AudioSystem.getLine(info);            // AudioSystem class acts as the entry point to the sampled-audio system resources.
		microphone.open(af);                                               //getLine- Obtains a line that matches the description in the specified Line.Info object.
		microphone.start();
	}
	public void run()
	{
		TimerTask1 tt= new TimerTask1();	
		t.scheduleAtFixedRate(tt, 10, 1);                      //Schedules the specified task for repeated fixed-rate execution(Ex- Ringing bell for every one hour)
	}

	class TimerTask1 extends TimerTask 
	{
		TimerTask1()
		{
		}
		//Captures voice from microphone and sends to network
		public void run()
		{
			bytesRead = microphone.read(soundData,0,soundData.length);  //Reads audio data from the microphone and inserts that data into soundData[]
			if(bytesRead >= 0)
			{
				try
				{
					sendPack = new DatagramPacket(soundData,soundData.length,AudioConnection.getAddress(),AudioConnection.getPort()); //sets datagrampacket with the audio data
					AudioConnection.getSocket().send(sendPack);     //sends the datagrampacket with audio data     
					if(MyPreferences.getAudio() == 1)              //Enters if user press Mute
						this.cancel();                            //Cancels the task that have assigned
				}
				catch(Exception e)
				{
					System.out.println("Audio Sender Error: "+e.getMessage());
				}
			}
		}
	}
}