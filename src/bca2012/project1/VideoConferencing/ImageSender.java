package bca2012.project1.VideoConferencing;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamLock;
import com.github.sarxos.webcam.WebcamResolution;

//capture images of webcam and send to MVS!!!
public class ImageSender implements Runnable
{
	private static BufferedImage img;
	private static int sec= 2;
	private static int send= 0;  //send variable calls multicastsender if it is 0 
	private static Webcam wc= null;
	JFrame frame;
	
	public static void setsend(int send)
	{
		ImageSender.send= send;
	}
	public static int getsend()
	{
		return(send);
	}
	public static BufferedImage getImage()
	{
		return(img);
	}
	public ImageSender() throws InterruptedException
	{
		Webcam.setAutoOpenMode(true);            //it opens webcam if it is closed
		System.out.println("webcamImage");
		if(Webcam.getDefault() == null)
		{
			JOptionPane.showMessageDialog(frame,"No webcam connected.",
					"Error", JOptionPane.ERROR_MESSAGE);
			new MyWebcamDiscoveryListener();  //call to detect webcam events!!!
		}
		else
		{
			wc= Webcam.getDefault();           // it discovers and return webcam available in the system.
			wc.setViewSize(WebcamResolution.valueOf("VGA").getSize());  //sets the resolution while capturing image from webcam
			wc.open(false);
			img= wc.getImage();
			WebcamLock lock= wc.getLock();
			lock.unlock();
			if(MyWebcamDiscoveryListener.wdl == 0 && getSec() == 2)
			{
				setSec(1);
				Thread thread1= new Thread(this);
				thread1.start();
			}
			
		}
	}

	public void run() 
	{
		new MyWebcamDiscoveryListener();
		DoJob();
	}
	
	//This method continously captures images from webcam and sends the captured images to Multicatvideosender class
	public static void DoJob()            
	{									
		try						       
		{							  
			while(true)
			{
				img= wc.getImage();                 //intialize img as the image that have captured from webcam
				if(MyPreferences.getVideo() == 1)  //Enters when user press unsend(do not send to network)
				{
					MyCamFrame.getPanel(1).setImage(img);
					img= null;
				}
				if(MyPreferences.getVideo() == 0)  //Enters when user press send(send to network)
				{
					if(send == 0)
					{
						System.out.println("In send= "+send);
						new MulticastVideoSender();
						send++;

					}
				}
				//check cam!!!
				if(Webcam.getDefault() == null)
				{
					break;
				}
			}
		}
		catch(Exception e)
		{
			Webcam.setAutoOpenMode(false);
			System.out.println("Error in WebcamImage:"+e.getMessage());
		    img= null;
			return;
		}
	}
	public static int getSec()
	{
		return sec;
	}
	public static void setSec(int sec) 
	{
		ImageSender.sec = sec;
	}
}
