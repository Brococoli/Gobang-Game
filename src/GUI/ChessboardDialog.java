package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ChessboardDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private int windowWidth = 400;
	private int windowHeight = 140;
	private JLabel widthLabel= new JLabel("宽度："); 
	private JTextField widthText = new JTextField(10);
	private JLabel heightLabel= new JLabel("宽度："); 
	private JTextField heightText = new JTextField(10);
	private JButton saveButton = new JButton("确定");
	private JButton closeButton = new JButton("取消");
	
	private Interface parent;
	public ChessboardDialog(Interface parent, String msg){
		super(parent, msg, true);
		this.parent = parent; 
	}
	private void initGrid(){
		saveButton.addActionListener(this);
		closeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ChessboardDialog.this.dispose();
			}
		});
		
		JPanel pSize = new JPanel();
		pSize.add(widthLabel);
		pSize.add(widthText);
		pSize.add(heightLabel);
		pSize.add(heightText);
		

		
		JPanel pButton = new JPanel();
		pButton.add(saveButton);
		pButton.add(closeButton);
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(pSize);
		mainPanel.add(pButton);
		
		this.add(mainPanel);
	}
	public void createChessboardDialog(){
		initGrid();
		setPosition();
		validate();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
	public void actionPerformed(ActionEvent e){
		String widthStr = widthText.getText();
		String heightStr = heightText.getText();
		
		
		for (int i = widthStr.length();--i>=0;){    //检验输入值是否合法
			if (!Character.isDigit(widthStr.charAt(i)))	dispose();
		}  
		for (int i = heightStr.length();--i>=0;){    
			if (!Character.isDigit(heightStr.charAt(i))) dispose();  
		} 
			
		int width = Integer.parseInt(widthStr);
		int height = Integer.parseInt(heightStr);
		
		if(width<10||height<10) dispose();
		parent.setChessboardSize(width, height);
		dispose();
	}
	
}
