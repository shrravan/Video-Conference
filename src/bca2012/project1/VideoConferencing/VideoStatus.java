package bca2012.project1.VideoConferencing;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

//This calss gives the status for video conferencing!!!!!
public class VideoStatus implements Runnable
{
	private static String ipAddressString;
	private static ArrayList<String> address= new ArrayList<String>(6);
	private static HashMap<String, ImageReceiver> list;
	private byte[] inBuf;
	private static DatagramPacket inPacket;
	private BufferedImage img;
	private ByteArrayInputStream bis;
	private static int i= 0;

	public VideoStatus()
	{
		try
		{
			//zeroth position is for Bigpanel!!!! so,added null
			address.add(null);
			address.add(InetAddress.getLocalHost().getHostAddress());  //fixed positon for local host!!!
			address.add(null);
			address.add(null);
			address.add(null);
			address.add(null);
			address.add(null);
		}
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}

		System.out.println("Videostatus");
		list= new HashMap<String, ImageReceiver>();
		inBuf = new byte[65535];
		inPacket = new DatagramPacket(inBuf, inBuf.length);
		Thread t= new Thread(this);
		t.start();
	}


	public static DatagramPacket getdatagrampacket()
	{
		return(inPacket);
	}
	public static ArrayList<String> getaddress()
	{
		return(address);
	}
	public BufferedImage getImg()
	{
		return img;
	}

	//swap function relative with zero!!!
	public static void swapZeroWith(int ind) 
	{
		String add= address.get(0);
		address.set(0,address.get(ind));
		address.set(ind, add);
		//swap image
		Image im0= MyCamFrame.getPanel(0).im.getImage();
		Image imInd= MyCamFrame.getPanel(ind).im.getImage();

		//swap stopped image!!!1
		MyCamFrame.getPanel(0).im.setImage(imInd);
		MyCamFrame.getPanel(ind).im.setImage(im0);

		//call repaint() !! to draw the stopped image!!!
		MyCamFrame.getPanel(0).repaint();
		MyCamFrame.getPanel(ind).repaint();
	}

	public void setImg(BufferedImage img) 
	{
		this.img = img;
	}


	public String receive() throws IOException
	{
		VideoConnection.getSocket().receive(inPacket);
		bis= new ByteArrayInputStream(inBuf,0,inPacket.getLength());
		this.setImg(ImageIO.read(bis));
		return inPacket.getAddress().getHostAddress();
	}

	//This run() method receives image packets and sends this image data to ImageReceiver(to set image to respective panel).
	public void run() 
	{
		try
		{
			while(true)
			{
				setIpAddressString(this.receive());
				if(list.get(ipAddressString) == null)
				{
					list.put(ipAddressString, new ImageReceiver());
					if(!InetAddress.getLocalHost().getHostAddress().equals(ipAddressString))
					{
						System.out.println("Adding " + ipAddressString);
						address.set(i,ipAddressString);//Add new client address to arraylist
						i++;
						if(i == 1) 
							i++; //index 1 is for local host
					}
				}
				try
				{
					list.get(ipAddressString).setImg(this.getImg());
				}
				catch(Exception e)
				{
					address.set(i,null);
					i--; 
					if (i== 1) 
						i--; //index 1 is for local host
					System.out.println("In status while true method:"+e.getMessage());
					list.remove(inPacket.getAddress().toString());
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Status Error: "+e.getMessage());
			e.printStackTrace();
		}
	}

	public static String getIpAddressString() 
	{
		return ipAddressString;
	}


	public static void setIpAddressString(String ipAddressString) 
	{
		VideoStatus.ipAddressString = ipAddressString;
	}
}
