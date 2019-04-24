package bca2012.project1.VideoConferencing;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;

//Gives various features for user. 
public class MyPreferences 
{
	private static int Video= 0;    //if Video= 0,sending & receiving!!!! Video= 1,only receiving!!
	private static int Audio= 0;   //if Audio= 0,listening & Speaking!!! Audio= 1,only listening!!
	
	public static int getVideo()
	{
		return(Video);
	}
	public static int getAudio()
	{
		return(Audio);
	}
	
	public MyPreferences(String string) throws InterruptedException, LineUnavailableException, IOException
	{
		switch (string)
		{
			case "Home":
			{
				LoggedIn.getJfrm2().dispose();
				new MyFrontEndFrame();
				break;
			}
	
			case "Mute":
			{
				Audio= 1;
				new AudioConnection();
				//LoggedIn.getJfrm2().setTitle(string);
				JOptionPane.showMessageDialog(null,"You opted for Listening",
						"Message", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("In Listner Mode");
				break;
			}
	
			case "UnMute":
			{
				Audio= 0;
				new AudioConnection();
				//LoggedIn.getJfrm2().setTitle(string);
				JOptionPane.showMessageDialog(null,"You opted for Speaking and Listening",
						"Message", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("In Speaker Mode");
				break;
			}
			
			case "Send":
			{
				MyPreferences.Video= 0;
				ImageSender.setSec(2);
				new VideoConnection();
				//LoggedIn.getJfrm2().setTitle(string);
				JOptionPane.showMessageDialog(null,"You opted to send and receive video ",
						"Message", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("In Send Video");
				break;
			}
	
			case "UnSend":
			{
				MyPreferences.Video= 1;
				new VideoConnection();
				//LoggedIn.getJfrm2().setTitle(string);
				JOptionPane.showMessageDialog(null,"You opted to receive video ",
						"Message", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("In UnSend Video");
				break;
			}
	
			case "Log Out":
			{
				System.exit(0);
			}
	
			case "Reference Manual":
			{
				LoggedIn.getJmiReference().setEnabled(false);
				new ReferenceFrame();
				System.out.println("In Help");
				break;
			}
		}
	}
}
