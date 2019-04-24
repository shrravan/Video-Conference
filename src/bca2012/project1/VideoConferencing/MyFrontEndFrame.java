package bca2012.project1.VideoConferencing;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MyFrontEndFrame  
{
	private static JFrame jfrm;
	public static JFrame getFrame()
	{
		return jfrm;
	}
	public MyFrontEndFrame() throws IOException
	{
		//For Buttons...Look and Feel Used!!!
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}

		jfrm= new JFrame("		Video Conferencing On Lan (Vcol)");
		jfrm.setSize(1366,768);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.setResizable(true);
		jfrm.setLocationRelativeTo(null);
		jfrm.setVisible(true);
		jfrm.add(new MyFrontEndPanel());
	}
}
