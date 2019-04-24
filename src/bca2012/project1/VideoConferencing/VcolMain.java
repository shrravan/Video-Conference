package bca2012.project1.VideoConferencing;

import java.io.IOException;
import javax.swing.SwingUtilities;

//This call has main() method!!!!!!
public class VcolMain 
{
	public static void main(String[] args)
	{
		//Create the thread on the event dispatching thread.
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					new MyFrontEndFrame();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
