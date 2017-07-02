package GUI;

import game.Chessboard;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ChessboardUI extends JPanel implements MouseListener, MouseMotionListener{
	
	private static final long serialVersionUID = 1L;

	private Interface parent;
	private Chessboard chessboard;
	private char who;
	private boolean lock;
	
	public ChessboardUI(Interface parent, int width, int height){
		this.parent = parent;
		
		chessboard = new Chessboard();
		chessboard.setSize(width, height);
		initChessboardUI();
		addMouseListener(this);
	}
	
	public void initChessboardUI(){
		chessboard.initChess();
		who = 'B';
		lock = false;
		System.out.println("init Chessboard:" + who);
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {  //重写，绘制棋子棋盘
		// TODO Auto-generated method stub
		super.paintComponent(g);
	
		for(int x=0;x<chessboard.getWidth();x++){
			for(int y=0;y<chessboard.getHeight();y++){
				paintChess(g,x,y,chessboard.getChess(x,y));
			}
		}
	}
	
	public void paintChess(Graphics g, int i, int j, char color){
		int x = 20*i + 20;
		int y = 20*j + 20;
		
		if(i!=chessboard.getWidth()-1 && j!=chessboard.getHeight()-1){
			g.setColor(Color.black);
			g.drawRect(x, y, 20, 20);
		}
		if(color == 'N') return;

		g.setColor(color=='B'?Color.black:Color.white);
		g.drawOval(x-8, y-8, 16, 16);
		g.fillOval(x-8, y-8, 16, 16);
	}
	
	public void mousePressed(MouseEvent event){  //监听鼠标单击事件
		if(lock) return;
		
		int x = (event.getX()-10)/20;
		int y = (event.getY()-10)/20;
		System.out.println("x="+x+", y="+y);
		
		if(event.getModifiers() == MouseEvent.BUTTON1_MASK){
			
			if(chessboard.canBePut(x, y)){
				
				chessboard.putChess(x, y, who);
				this.repaint();
				
				String winner = chessboard.isWin();
				if(!winner.equals("No one")) {
					new resultDialog(parent, "比赛结果", winner);  //dialog 结束后会继续主进程
					return;
				}
				who = who=='B' ? 'W':'B';
				
				if(chessboard.isWithComputer()){
					AIPutChess();
					winner = chessboard.isWin();
					if(!winner.equals("No one")) {
						new resultDialog(parent, "比赛结果", winner);  //dialog 结束后会继续主进程
						return;
					}
				}
				
			}
			else System.out.println("can't be put!");
		} else if(event.getModifiers() == MouseEvent.BUTTON3_MASK){
			System.out.println("Button3 Click");
			RightMouseMenu().show(this, event.getX(), event.getY());
		}
	}
	
	public void AIPutChess(){  //AI放置电脑
		
		System.out.println("Computer's Turn");
		chessboard.getNext(who);
		this.repaint();
		who = who=='B' ? 'W':'B';
	}

	
	public JPopupMenu RightMouseMenu(){
		JPopupMenu popMenu = new JPopupMenu();
		JMenuItem backItem = new JMenuItem("悔棋");
		backItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				who = who == 'B' ? 'W' : 'B';
				chessboard.popPoint();
				repaint();
			}
		});
		JMenuItem exitItem = new JMenuItem("退出");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		popMenu.add(backItem);
		popMenu.add(exitItem);
		return popMenu;
		
	}
	public void setLock(boolean lock) {  
		this.lock = lock;
	}

	public Chessboard getChessboard() {
		return chessboard;
	}


	@Override
	public void mouseClicked(MouseEvent e) {  //接口，这些类必须要重写
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
