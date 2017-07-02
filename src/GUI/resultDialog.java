package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class resultDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	private Interface parent;
	private int windowWidth = 225, windowHeight = 136;
	private JLabel resLabel;
	private JButton againButton = new JButton("再来一局");
	private JButton closeButton = new JButton("关闭");
	public resultDialog(Interface parent, String msg, String winner) {
		super(parent, msg, true);
		this.parent = parent;
		setDefaultCloseOperation(resultDialog.DISPOSE_ON_CLOSE);
		
		JPanel pRes = new JPanel();
		resLabel = new JLabel(winner + " 获胜！！");
		resLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		pRes.add(resLabel);
		
		JPanel pButton = new JPanel();
		againButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultDialog.this.parent.getChessboardUI().initChessboardUI();
				resultDialog.this.dispose();
			}
		});
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultDialog.this.parent.getChessboardUI().setLock(true);
				resultDialog.this.dispose();
			}
		});
		pButton.add(againButton);
		pButton.add(closeButton);
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(pRes);
		mainPanel.add(pButton);
		this.add(mainPanel);
		
		setPosition();
		validate();
		setVisible(true);
	}
	
	private void setPosition(){
		int parentX = parent.getX();
		int parentY = parent.getY();
		int parentWidth = parent.getWidth();
		int parentHeight = parent.getHeight();
		int dialogX = parentX + (parentWidth - windowWidth)/2;
		int dialogY = parentY + (parentHeight - windowHeight)/2;
		this.setBounds(dialogX, dialogY, windowWidth, windowHeight);
	}
	
}
