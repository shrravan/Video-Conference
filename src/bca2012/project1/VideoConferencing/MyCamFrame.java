package bca2012.project1.VideoConferencing;

import java.awt.image.BufferedImage;
import java.net.UnknownHostException;
import javax.swing.JFrame;


public class MyCamFrame extends JFrame 
{
	//{bigPanel,smallPanel1,smallPanel2,smallPanel3,smallPanel4,smallPanel5}
	private static MyCamPanel[] panel=  new MyCamPanel[6];
	private static final long serialVersionUID = 1L;

	public MyCamFrame() 
	{
	}

	public void setImage(BufferedImage bim) throws UnknownHostException
	{
		try
		{
			int ind= VideoStatus.getaddress().indexOf(VideoStatus.getdatagrampacket().getAddress().getHostAddress());
			//find out the index of address in the arraylist say it is i
			if (ind != -1)
				panel[ind].setImage(bim);  //set respective image to respective panel!!!
			else 
				System.out.println("Address not found in list");
		}
		catch(Exception e)
		{
			System.out.println("Err in MyCamFrame:"+e.getMessage());
		}
	}
	
	public static void addPanel(int ind, MyCamPanel p) 
	{
		panel[ind]= p;
	}
	
	public static MyCamPanel getPanel(int ind) 
	{
		return panel[ind];
	}

	public static int getIndex(MyCamPanel p) 
	{
		for (int i= 0; i < 6; i++) 
		{
			if(p.equals(panel[i])) return i;
		}
		return -1;
	}
}

