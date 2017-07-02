package GUI;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class StartGame {  //ÓÎÏ·Èë¿Ú

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
		Interface win = new Interface("Gobang game!");
		win.setVisible(true);
		

	}

}
