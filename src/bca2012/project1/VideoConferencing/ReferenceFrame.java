package bca2012.project1.VideoConferencing;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

//Frame for reference manual!!!!
public class ReferenceFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	public ReferenceFrame()
	{
		add(new ReferencePanel(this));
	    Toolkit kit= Toolkit.getDefaultToolkit();
		Dimension screenSize= kit.getScreenSize();
		setTitle("Reference Manual");
		setVisible(true);
		addWindowListener(new ReferencePanel(this));
		setSize(screenSize.width,screenSize.height);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
