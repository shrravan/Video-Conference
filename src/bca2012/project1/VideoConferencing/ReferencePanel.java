package bca2012.project1.VideoConferencing;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

//panel for Reference manual!!!!!
public class ReferencePanel extends JPanel implements WindowListener
{
	private static final long serialVersionUID = 1L;
	public JButton but= new JButton("NEXT");
	public JButton but1= new JButton("PREVIOUS");
	ImageIcon im,im1;
	String s,s1= "",d= ".jpg";
	int l= 0;
	Image i,i1;
	ReferenceFrame t;
	public ReferencePanel(ReferenceFrame t)
	{

		this.t= t;

		but.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				change();
				repaint();
			}
		});

		add(but1);
		but1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				change1();
				repaint();
			}
		});
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Shape(g);
	}
	public void Shape(Graphics g)
	{
		Graphics2D g1= (Graphics2D) g;
		im= new ImageIcon("/home/bca3/workspace/VideoConferencing/resources/"+ s);
		im1= new ImageIcon("/home/bca3/workspace/VideoConferencing/resources/book.gif");
		i= im.getImage();
		i1= im1.getImage();
		if(l == 0)
		{
			g1.drawImage(i1,500,300,null);
			Font f= new Font(Font.SANS_SERIF, Font.ITALIC, 100);
			g1.setFont(f);
			g1.drawString("lets start !!!!",200,200);
		}
		g1.drawImage(i,0,0,this);//getWidth(),getHeight(),this);
		add(but);
		but.setBounds(getWidth() - 150 ,getHeight() - 40,100,30);
		setBackground(Color.WHITE);
		but1.setBounds(getWidth() - getWidth() + 50 ,getHeight() - 40, 150,30);
	}
	public void change()
	{
		if(l != 11)
		{
			l++;
			s= Integer.toString(l).concat(d);
		}	
	}
	public void change1()
	{
		if(l != 0)
		{
			l--;
			s= Integer.toString(l).concat(d);
		}
	}
	public void windowClosed(WindowEvent e) 
	{
		LoggedIn.getJmiReference().setEnabled(true);
	}

	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e){} 
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}
