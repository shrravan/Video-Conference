package bca2012.project1.VideoConferencing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class MyCamPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	ImageIcon im;
	GridBagLayout layout;

	public MyCamPanel() 
	{
		super();
		setBackground(Color.PINK);
		//For Buttons...Look and Feel Used!!!
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		layout= new GridBagLayout();
		setLayout(layout);

		GridBagConstraints gbcBig= new GridBagConstraints();
		gbcBig.anchor= GridBagConstraints.CENTER;
		gbcBig.fill= GridBagConstraints.BOTH;
		gbcBig.gridheight= 2;
		gbcBig.gridwidth= 2;
		gbcBig.gridx= 0;
		gbcBig.gridy= 0;
		gbcBig.insets= new Insets(2,2,2,2);
		gbcBig.weightx= 66;
		gbcBig.weighty= 66;
		
		MyCamPanel p= new MyCamPanel(0);
		MyCamFrame.addPanel(0,p);
		Border b= BorderFactory.createBevelBorder(BevelBorder.RAISED);
		p.setBorder(b);

		layout.setConstraints(p,gbcBig);
		add(p, gbcBig);

		GridBagConstraints gbcSmall= (GridBagConstraints) gbcBig.clone();
		gbcSmall.gridheight= 1;
		gbcSmall.gridwidth= 1;
		gbcSmall.insets= new Insets(2,2,2,2);
		gbcSmall.weightx= 35;
		gbcSmall.weighty= 35;
		gbcSmall.gridx= 2;
		gbcSmall.gridy= 0;

		//Mouse listeners for panels!!!!!!!!!
		MouseAdapter ad= new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				MyCamPanel p= (MyCamPanel) e.getSource();

				//check the size and swap
				int active= VideoStatus.getaddress().size();
				int ind= MyCamFrame.getIndex(p);

				if(active > 2) 
				{
					//swap funtion!!!
					VideoStatus.swapZeroWith(ind);
				}
			}
		};
		
		for(int i= 1; i<= 5; i++) 
		{
			MyCamFrame.addPanel(i, p= new MyCamPanel(i));
			p.setBorder(b);
			if (i != 1)
				p.addMouseListener(ad);
		}

		p= MyCamFrame.getPanel(1);
		layout.setConstraints(p,gbcSmall);
		add(p, gbcSmall);

		gbcSmall= (GridBagConstraints) gbcSmall.clone();
		gbcSmall.gridx= 2;
		gbcSmall.gridy= 1;
		p= MyCamFrame.getPanel(2);
		layout.setConstraints(p,gbcSmall);
		add(p, gbcSmall);

		gbcSmall= (GridBagConstraints) gbcSmall.clone();
		gbcSmall.gridx= 0;
		gbcSmall.gridy= 2;
		p= MyCamFrame.getPanel(3);
		layout.setConstraints(p,gbcSmall);
		add(p, gbcSmall);

		gbcSmall= (GridBagConstraints) gbcSmall.clone();
		gbcSmall.gridx= 1;
		gbcSmall.gridy= 2;
		p= MyCamFrame.getPanel(4);
		layout.setConstraints(p,gbcSmall);
		add(p, gbcSmall);

		gbcSmall= (GridBagConstraints) gbcSmall.clone();
		gbcSmall.gridx= 2;
		gbcSmall.gridy= 2;
		p= MyCamFrame.getPanel(5);
		layout.setConstraints(p,gbcSmall);
		add(p, gbcSmall);
	}

	public MyCamPanel(int i)
	{
		switch (i) 
		{
		case 0:
			im= new ImageIcon("/home/bca3/workspace/VideoConferencing/resources/swami2.jpg");
			//im= new ImageIcon("resources/p1.jpg");
			break;

		case 1:
			im= new ImageIcon("/home/bca3/workspace/VideoConferencing/resources/p2.jpg");
			break;

		case 2:
			im= new ImageIcon("/home/bca3/workspace/VideoConferencing/resources/p3.jpg");
			break;

		case 3:
			im= new ImageIcon("/home/bca3/workspace/VideoConferencing/resources/p4.jpg");
			break;

		case 4:
			im= new ImageIcon("/home/bca3/workspace/VideoConferencing/resources/p5.jpg");
			break;

		case 5:
			im= new ImageIcon("/home/bca3/workspace/VideoConferencing/resources/p6.jpg");
			break;
		}
	}
	public  void setImage(BufferedImage im)
	{
		this.im= new ImageIcon(im);
		repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(im != null)
			g.drawImage(im.getImage(),0,0,getWidth(),getHeight(),null);
	}
}

