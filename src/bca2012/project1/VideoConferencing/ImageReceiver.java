package bca2012.project1.VideoConferencing;
import java.awt.image.BufferedImage;
import java.net.UnknownHostException;

//This is used to set incoming images to different panels in MyCamFrame class
public class ImageReceiver
{
	public MyCamFrame myFrame= new MyCamFrame(); 
	public BufferedImage img;
	
	ImageReceiver()
	{
		System.out.println("ImageReceiver");
	}

	public BufferedImage getImg() 
	{
		return img;
	}

	public void setImg(BufferedImage img) throws UnknownHostException 
	{
		this.img = img;
		myFrame.setImage(this.getImg());
	}
}
