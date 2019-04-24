package bca2012.project1.VideoConferencing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

//The first page user encounters!!!
public class MyFrontEndPanel extends JPanel  implements ActionListener
{ 
	private static final long serialVersionUID = 1L;
	GridBagLayout layout;
	private static JButton jbtnAboutVcol;
	private ImageIcon im= new ImageIcon("/home/bca3/workspace/VideoConferencing/resources/saipanel.jpg");
	
	public static JButton getjbtnAboutVcol()
	{
		return(jbtnAboutVcol);
	}

	public MyFrontEndPanel() throws IOException
	{
		super();
		layout= new GridBagLayout();
		setLayout(layout);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
		
		GridBagConstraints gbcButton= new GridBagConstraints();
		gbcButton.anchor= GridBagConstraints.NORTHWEST;
		gbcButton.fill= GridBagConstraints.NONE;
		gbcButton.gridx = 0;
		gbcButton.gridy = 0;
		gbcButton.ipadx = 80;
		gbcButton.ipady = 0;
		gbcButton.gridwidth = 0;
		gbcButton.gridheight = 0;
		gbcButton.weightx= 1;
		gbcButton.weighty= 1;
		gbcButton.insets= new Insets(450,700,0,250);
		
		jbtnAboutVcol= new JButton("About Us");
		jbtnAboutVcol.addActionListener(this);
		layout.setConstraints(jbtnAboutVcol, gbcButton);
		add(jbtnAboutVcol, gbcButton);
		
		GridBagConstraints gbcButton1= new GridBagConstraints();
		gbcButton1.anchor= GridBagConstraints.NORTHWEST;
		gbcButton1.fill= GridBagConstraints.NONE;
		gbcButton1.gridx = 0;
		gbcButton1.gridy = 0;
		gbcButton1.ipadx = 100;
		gbcButton1.ipady = 0;
		gbcButton1.gridwidth = 0;
		gbcButton1.gridheight = 0;
		gbcButton1.weightx= 1;
		gbcButton1.weighty= 1;
		gbcButton1.insets= new Insets(515,700,0,250);
		
		JButton jbtnLogIn= new JButton("Log In");
		jbtnLogIn.addActionListener(this);
		layout.setConstraints(jbtnLogIn, gbcButton1);
		add(jbtnLogIn, gbcButton1);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(im != null)
			g.drawImage(im.getImage(),0,0,getWidth(),getHeight(),null);
	}
	
	//Handle button event.
	public void actionPerformed(ActionEvent e) 
	{
		new MyVcolLog(e.getActionCommand());
		System.out.println("Entered: "+e.getActionCommand());
	}
}