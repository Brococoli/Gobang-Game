package GUI;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class aboutDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	private Interface parent;
	private int windowWidth = 330, windowHeight = 95;
	private JLabel msgLabel;
	public aboutDialog(Interface parent, String msg) {
		super(parent, msg, true);
		this.parent = parent;
		setDefaultCloseOperation(resultDialog.DISPOSE_ON_CLOSE);
		
		JPanel pMsg = new JPanel();
		msgLabel = new JLabel("writen by Wonder, Thank you!");
		msgLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		pMsg.add(msgLabel);
		
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(pMsg);
		this.add(mainPanel);
		
		setPosition();
		validate();   //��������
		setVisible(true);
	}
	
	private void setPosition(){   //�������ĶԻ�����õ�����������
		int parentX = parent.getX();
		int parentY = parent.getY();
		int parentWidth = parent.getWidth();
		int parentHeight = parent.getHeight();
		int dialogX = parentX + (parentWidth - windowWidth)/2;
		int dialogY = parentY + (parentHeight - windowHeight)/2;
		this.setBounds(dialogX, dialogY, windowWidth, windowHeight);
	}
}
