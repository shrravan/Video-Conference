package bca2012.project1.VideoConferencing;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AboutVcol extends JPanel implements WindowListener
{
	private static final long serialVersionUID = 1L;
	public AboutVcol()
	{
	}
	
	public void paintComponent(Graphics g)
	{
		ImageIcon im= new ImageIcon("/home/bca3/workspace/VideoConferencing/resources/Aboutus.jpg");
		Image m= im.getImage();
		super.paintComponent(g);
		//g.drawImage(m, 0, 0, 500, 500, null);
		g.drawImage(m, 0, 0, getWidth(), getHeight(), null);
	}
	
	public void windowClosed(WindowEvent e) 
	{
		MyFrontEndPanel.getjbtnAboutVcol().setEnabled(true);
	}

	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e){} 
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}

