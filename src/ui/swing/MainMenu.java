package ui.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Desktop;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainMenu extends JFrame
{
	public MainMenu() {
		setMinimumSize(new Dimension(300, 400));
		setMaximumSize(new Dimension(300, 400));
		setTitle("Divine Right Version 0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblDivineRight = new JLabel("DIVINE RIGHT");
		lblDivineRight.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		lblDivineRight.setBounds(10, 11, 360, 37);
		getContentPane().add(lblDivineRight);
		
		JButton btnGenerateWorld = new JButton("Create a New World");
		btnGenerateWorld.setBounds(10, 59, 264, 23);
		getContentPane().add(btnGenerateWorld);
		
		JButton btnAdvancedWorldCreation = new JButton("Advanced World Creation");
		btnAdvancedWorldCreation.setBounds(10, 93, 264, 23);
		getContentPane().add(btnAdvancedWorldCreation);
		
		JButton btnContinueAnExisting = new JButton("Continue an Existing World");
		btnContinueAnExisting.setBounds(10, 127, 264, 23);
		getContentPane().add(btnContinueAnExisting);
		
		JLabel lblNewLabel = new JLabel("https://github.com/Haedrian/Divine-Right");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				//Check whether we can open a browser
				if ( java.awt.Desktop.isDesktopSupported())
				{
					try
					{
						Desktop.getDesktop().browse(new URI("https://github.com/Haedrian/Divine-Right"));
					} 
					catch (IOException | URISyntaxException e)
					{
					//don't really care
					}
				}
			}
		});
		lblNewLabel.setBounds(10, 337, 264, 14);
		getContentPane().add(lblNewLabel);
	}
}
