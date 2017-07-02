package GUI;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import GUI.ChessboardUI;
import GUI.ChessboardDialog;

import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;  
import org.jvnet.substance.skin.SubstanceAutumnLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel;

public class Interface extends JFrame{  //主框体
	
	private ChessboardUI chessboardUI;
	
	private static final long serialVersionUID = 1L;
	public Interface(String title){
		super(title);
		setChessboardSize(20, 20);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createMenuBar();
		
		
	}

	public void setChessboardSize(int width,int height){
		this.setSize(width*20+40, height*20+90);
		chessboardUI = new ChessboardUI(this, width, height);
		this.setContentPane(chessboardUI);
		this.setResizable(false);
	}
	private void createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu gameMenu = new JMenu("游戏");
		JMenu viewMenu = new JMenu("视图");
		JMenu helpMenu = new JMenu("帮助");
		menuBar.add(gameMenu);
		menuBar.add(viewMenu);
		menuBar.add(helpMenu);
		
		JMenuItem beginGame = new JMenuItem("开局", 'N');
		beginGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chessboardUI.initChessboardUI();
			}
		});
		JMenuItem chessboardGame = new JMenuItem("棋盘");
		chessboardGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChessboardDialog cd = new ChessboardDialog(Interface.this, "棋盘设置");
				cd.createChessboardDialog();
			}
		});
		JMenu modeGame = new JMenu("模式");
		ButtonGroup modeGroup = new ButtonGroup();
		JRadioButtonMenuItem P2PGame = new JRadioButtonMenuItem("双人对战");
		P2PGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getChessboardUI().getChessboard().setWithComputer(false);
				getChessboardUI().initChessboardUI();
			}
		});
		JRadioButtonMenuItem P2CGame = new JRadioButtonMenuItem("人机对战");
		P2CGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getChessboardUI().getChessboard().setWithComputer(true);
				getChessboardUI().initChessboardUI();
			}
		});
		modeGroup.add(P2PGame);
		modeGroup.add(P2CGame);
		P2CGame.setSelected(true);
//		modeGame.add(modeGroup);
		modeGame.add(P2PGame);
		modeGame.add(P2CGame);
		
		JMenuItem exitGame = new JMenuItem("退出", 'W');
		exitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK)); // 快捷键ctrl+W
		gameMenu.add(beginGame);
		gameMenu.add(chessboardGame);
		gameMenu.add(modeGame);
		gameMenu.addSeparator();
		gameMenu.add(exitGame);
		
		JMenuItem metalView = new JMenuItem("Black");
		metalView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
		        } catch (Exception er)
		        {
		            er.printStackTrace();
		        }
			}
		});
		JMenuItem motifView = new JMenuItem("Pink");
		motifView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		            UIManager.setLookAndFeel(new SubstanceAutumnLookAndFeel());
		        } catch (Exception er)
		        {
		            er.printStackTrace();
		        }
				
			}
		});
		JMenuItem windowsView = new JMenuItem("Oriange");
		windowsView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		            UIManager.setLookAndFeel(new SubstanceNebulaBrickWallLookAndFeel());
		        } catch (Exception er)
		        {
		            er.printStackTrace();
		        }
				
			}
		});
		viewMenu.add(metalView);
		viewMenu.add(motifView);
		viewMenu.add(windowsView);

		JMenuItem aboutHelp = new JMenuItem("关于");
		aboutHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new aboutDialog(Interface.this, "关于作者");				
			}
		});
		helpMenu.add(aboutHelp);
		
		this.setJMenuBar(menuBar);
	}
	
	public ChessboardUI getChessboardUI() {
		return chessboardUI;
	}

}
