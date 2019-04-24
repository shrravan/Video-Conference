package bca2012.project1.VideoConferencing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

//This class uses JMenubar; Gives various features for user. 
public class LoggedIn extends JPanel implements ActionListener   
{
	private static final long serialVersionUID = 1L;
	private static JFrame jfrm2;
	private static JRadioButtonMenuItem jmiSend;
	private static JRadioButtonMenuItem jmiUnSend;
	private static JRadioButtonMenuItem jmiMute;
	private static JRadioButtonMenuItem jmiUnMute;
	private static JMenuItem jmiReference; 
	public LoggedIn()
	{
		setJfrm2(new JFrame("		You are Logged In"));
		getJfrm2().setSize(1366,768);

		//To close window without any thread remaining!!!
		getJfrm2().dispatchEvent(new WindowEvent(getJfrm2(), WindowEvent.WINDOW_CLOSED));
		getJfrm2().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getJfrm2().setResizable(true);
		getJfrm2().setLocationRelativeTo(null);
		getJfrm2().setVisible(true);

		//Create the menu bar and a label that will display menu selection.
		JMenuBar jmb= new JMenuBar();
		JMenu jmBack= new JMenu("Back  ");
		JMenuItem jmiHome= new JMenuItem("Home");
		jmBack.add(jmiHome);
		jmb.add(jmBack);

		JMenu jmPreferences= new JMenu("Preferences  ");

		JMenu jmAudio= new JMenu("Audio Mode");
		jmiMute= new JRadioButtonMenuItem("Mute");
		jmiUnMute= new JRadioButtonMenuItem("UnMute");
		jmAudio.add(jmiMute);
		jmAudio.add(jmiUnMute);
		jmPreferences.add(jmAudio);

		JMenu jmVideo= new JMenu("Video Mode");
		jmiSend= new JRadioButtonMenuItem("Send");
		jmiUnSend= new JRadioButtonMenuItem("UnSend");
		jmVideo.add(jmiSend);
		jmVideo.add(jmiUnSend);
		jmPreferences.add(jmVideo);


		JMenuItem jmiLogOut= new JMenuItem("Log Out");
		jmPreferences.add(jmiLogOut);
		jmb.add(jmPreferences);

		JMenu jmHelp= new JMenu("Help");
		setJmiReference(new JRadioButtonMenuItem("Reference Manual"));
		jmHelp.add(getJmiReference());
		jmb.add(jmHelp);
		getJfrm2().setJMenuBar(jmb); 

		//Add action listeners for the menu items.
		jmiHome.addActionListener(this);
		jmiMute.addActionListener(this);
		jmiUnMute.addActionListener(this);
		jmiSend.addActionListener(this);
		jmiUnSend.addActionListener(this);
		jmiLogOut.addActionListener(this);
		getJmiReference().addActionListener(this);
		getJfrm2().add(new MyCamPanel());
	}

	//Handle menu items action events.
	public void actionPerformed(ActionEvent e)
	{
		try 
		{
			new MyPreferences(e.getActionCommand());
			if(e.getActionCommand().equals("Send"))
			{
				LoggedIn.jmiSend.setEnabled(false);
				LoggedIn.jmiUnSend.setEnabled(true);
			}
			if(e.getActionCommand().equals("UnSend"))
			{
				LoggedIn.jmiSend.setEnabled(true);
				LoggedIn.jmiUnSend.setEnabled(false);
			}

			if(e.getActionCommand().equals("Mute"))
			{
				LoggedIn.jmiMute.setEnabled(false);
				LoggedIn.jmiUnMute.setEnabled(true);
			}
			if(e.getActionCommand().equals("UnMute"))
			{
				LoggedIn.jmiUnMute.setEnabled(false);
				LoggedIn.jmiMute.setEnabled(true);
			}
		}
		catch (InterruptedException | LineUnavailableException | IOException e1) 
		{
			e1.printStackTrace();
		}
	}

	public static JFrame getJfrm2() 
	{
		return jfrm2;
	}

	public static void setJfrm2(JFrame jfrm2) 
	{
		LoggedIn.jfrm2 = jfrm2;
	}

	public static JMenuItem getJmiReference() 
	{
		return jmiReference;
	}

	public static void setJmiReference(JMenuItem jmiReference) 
	{
		LoggedIn.jmiReference = jmiReference;
	}
}
