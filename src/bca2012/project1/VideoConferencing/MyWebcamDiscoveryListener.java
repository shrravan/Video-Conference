package bca2012.project1.VideoConferencing;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;

//Gets the events of webcam!!!!!!!!
public class MyWebcamDiscoveryListener implements WebcamDiscoveryListener ,Runnable
{
	private static int c= 0,first= 0,second= 0,found= 0;
	public static int wdl= 0;
	JFrame frame;
	public MyWebcamDiscoveryListener() 
	{
		Webcam.addDiscoveryListener(this);
		if(c == 0)
		{
			check();
			c++;
		}
	}

	public void webcamFound(WebcamDiscoveryEvent event) 
	{
		if(found == 0)
		{
			first= 1;
			found= 1;
			JOptionPane.showMessageDialog(frame,"webcam has been connected.",
					"Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void webcamGone(WebcamDiscoveryEvent event) 
	{
		if(second == 0)
		{
			//For Message...Look and Feel Used!!!
			try
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
			{
				e.printStackTrace();
			}
			second= 1;
			found= 0;
			//first= 1;
			JOptionPane.showMessageDialog(frame,"webcam has been disconnected.",
					"Message", JOptionPane.INFORMATION_MESSAGE);
			ImageSender.DoJob();
		}
	}

	public void run() 
	{
		while(true)
		{
			try
			{
				if(first == 1)
				{
					first= 2;
					second= c= 0;
					wdl++;
					Thread t1= new Thread(new ImageSender());
					t1.start();
					break;
				}
				new MyWebcamDiscoveryListener();
				Thread.sleep(2000);
			}
			catch(InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

	private void check() 
	{
		Thread t= new Thread(this);
		t.start();
	}
}
