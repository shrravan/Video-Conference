package bca2012.project1.VideoConferencing;
import javax.swing.JFrame;

//panel for the TWO buttons!!!!
public class MyVcolLog 
{
    private static JFrame jfrm1;
	public MyVcolLog(String string)
	{
		switch (string)
		{
			case "About Us":
			{
				MyFrontEndPanel.getjbtnAboutVcol().setEnabled(false);
				jfrm1= new JFrame("		About Us");
				jfrm1.setSize(800,800);
				jfrm1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				jfrm1.setLocationRelativeTo(null);
				jfrm1.setVisible(true);
				jfrm1.add(new AboutVcol());
				jfrm1.addWindowListener(new AboutVcol());
				jfrm1.setResizable(true);
				jfrm1.getTitle();
                break;				
			}

			case "Log In":
			{
				MyFrontEndFrame.getFrame().dispose();
				new LoggedIn();
				break;
			}
		}
	}
}