package gameModule.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import gameModule.controller.HandleGame;
import gameModule.controller.HandleDataList;
import gameModule.model.GameModel;
import loginModule.utils.ScreenUtils;

public class GameView {

	JFrame jf=new JFrame("字符模式");
	
	private GameModel user=null;
	Timer timeThread=null;
	HandleGame gamePanel;
	
	final int WIDTH = 740;
	final int HEIGHT = 550;
	
	public GameView(GameModel user) {
		this.user=user;
	}
	
	
	public void init() throws Exception {
		
		// 设置窗口相关属性
		jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2,
				(ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
		jf.setResizable(true);
		jf.setIconImage(ImageIO.read(new File("images\\logo.png"))); // 设置logo
		jf.setBackground(Color.BLACK);
		jf.setVisible(true);
		
		// 设置窗口内容
		
		// 游戏控制
		Box cBox=Box.createHorizontalBox();
		JButton stopBut=new JButton("暂停");
		JButton continueBut=new JButton("继续");
		JButton exitBut = new JButton("返回");
		JButton rankBut = new JButton("排行榜");
		
		stopBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeThread.stop();
			}
		});
		
		continueBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 请求游戏窗口获得焦点
				if (!gamePanel.isFocusable()){
					gamePanel.setFocusable(true);
				}
				if (!gamePanel.isFocusOwner()){
					gamePanel.requestFocusInWindow();
				}
				timeThread.restart();
			}
		});
		
		exitBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HandleDataList.saveCurrentUserData(user); // 结束游戏前先保存用户数据
				jf.dispose(); // 退出
				try {
					new StartView(user).init();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		rankBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<GameModel> rankList = HandleDataList.getUserList(); // 获取所有用户排序后的列表
				try {
					 // 显示排行榜视图
					new RankListView(rankList, user).init();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		cBox.add(exitBut);
		cBox.add(Box.createHorizontalStrut(120));
		cBox.add(stopBut);
		cBox.add(Box.createHorizontalStrut(20));
		cBox.add(continueBut);
		cBox.add(Box.createHorizontalStrut(120));
		cBox.add(rankBut);
		
		// 游戏界面
		JPanel panel = new JPanel();
		panel.add(cBox);
		gamePanel = new HandleGame(user);
		gamePanel.setBackground(Color.BLACK);

		jf.add(gamePanel);
		jf.add(panel, BorderLayout.SOUTH);
		

		// 请求游戏窗口获得焦点
		if (!gamePanel.isFocusable()){
			gamePanel.setFocusable(true);
        }
        if (!gamePanel.isFocusOwner()){
        	gamePanel.requestFocusInWindow();
        }
		
		// 启动游戏
		timeThread=new Timer(30, gamePanel);
		timeThread.start();
		gamePanel.setThread(timeThread);
		jf.addKeyListener(gamePanel);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
