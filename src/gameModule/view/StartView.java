package gameModule.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import gameModule.controller.HandleChoice;
import gameModule.controller.HandleDataList;
import gameModule.model.GameModel;
import loginModule.view.BackGroundPanel;
import loginModule.view.LoginView;
import loginModule.utils.ScreenUtils;

/**
 * 开始视图：
 * 绘制开始界面
 *
 * @author lzx
 */
public class StartView {
	
	JFrame jf = new JFrame("打字游戏");
	private GameModel user=null;
	
	final int WIDTH = 740;
	final int HEIGHT = 550;
	
	public StartView(GameModel nowUser){
		user=nowUser;
	}

	// 组装视图
	public void init() throws Exception {
		// 设置窗口相关属性
		jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2,
				(ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
//		jf.setLayout(new FlowLayout());
		jf.setResizable(true);
		jf.setIconImage(ImageIO.read(new File("images\\logo.png"))); //设置logo
		
		// 设置窗口内容
		// 背景图片
		BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images\\1.jpg")));
		bgPanel.setBounds(0,0,WIDTH,HEIGHT);
		
		
		// 选择关卡
		Box cBox1 = Box.createHorizontalBox();
		Box cBox2 = Box.createHorizontalBox();
//		Dimension preferredSize=new Dimension(160,40);
		String choiceStr []= {"第一关", "第二关", "第三关", "自由模式"};
		JButton choiceBut []=new JButton[choiceStr.length];
		HandleChoice handleChoice = new HandleChoice(user);
		// 设置可以选择的关卡
		for(int i=0; i<choiceStr.length; i++) {
			choiceBut[i]=new JButton(choiceStr[i]);
			if(user.getPassNum()>=i) // 如果上一关已通过，将该关设置为可以进入
			{
				choiceBut[i].setEnabled(true);
			} else {
				choiceBut[i].setEnabled(false);
			}
			choiceBut[i].addActionListener(handleChoice);
//			choiceBut[i].setPreferredSize(preferredSize);
		}
		
		cBox1.add(choiceBut[0]);
		cBox1.add(Box.createHorizontalStrut(20));
		cBox1.add(choiceBut[1]);
		cBox2.add(choiceBut[2]);
		cBox2.add(Box.createHorizontalStrut(20));
		cBox2.add(choiceBut[3]);
		
		
		// 字符模式
		Box sBox = Box.createHorizontalBox();
		JButton startBut = new JButton("字 符 模 式");
		startBut.setBackground(Color.red);
//		preferredSize=new Dimension(150,70);
//		startBut.setPreferredSize(preferredSize);
		startBut.addActionListener(new ActionListener() { // 为“开始游戏”按钮注册监视器
			@Override
			public void actionPerformed(ActionEvent e) {
				// 跳转到游戏界面
				try {
//					new CharJFrame(user.getDifficulty());
					new GameView(user).init();
					jf.dispose();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		sBox.add(startBut);

		// 文本模式
		Box aBox = Box.createHorizontalBox();
		JButton articleCompareBut = new JButton("文 本 模 式");
		articleCompareBut.setBackground(Color.green);
		articleCompareBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				// 跳转文本比较界面
				try{
					//初始化界面
					Windowm windowm = new Windowm(user);
					//属性设置
					SimpleAttributeSet attrset = new SimpleAttributeSet();
					//字体大小
					StyleConstants.setFontSize(attrset,16);
					//获取JTextPane对象
					Document docs1=windowm.text1.getDocument();
					//设置初次显示文本
					docs1.insertString(docs1.getLength(), "", attrset);
					Document docs2=windowm.text2.getDocument();
					docs2.insertString(docs2.getLength(), "手动输入输入或者选择文间打开\n点击核对试试\n红色表示错误字符\n蓝色表示多余或缺失字符", attrset);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
		aBox.add(articleCompareBut);
		
		// 排行榜
		Box kBox=Box.createHorizontalBox();
		JButton rankListBut = new JButton("排  行  榜");
		rankListBut.addActionListener(new ActionListener() { // 为排行榜按钮注册监视器
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
		kBox.add(rankListBut);
		
		// 退出登陆
		Box rBox=Box.createHorizontalBox();
		JButton reLoginBut = new JButton("退出登陆");
		reLoginBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					HandleDataList.saveCurrentUserData(user); // 结束游戏前先保存用户数据
					
					new LoginView().init(); // 登陆界面
					jf.dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		rBox.add(reLoginBut);
		
		// 退出游戏
		Box eBox=Box.createHorizontalBox();
		JButton exitBut = new JButton("退出游戏");
		exitBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HandleDataList.saveCurrentUserData(user); // 结束游戏前先保存用户数据
				System.exit(0); // 退出
			}
		});
		eBox.add(exitBut);

		Box tBox = Box.createHorizontalBox();
		tBox.add(sBox);
		tBox.add(Box.createHorizontalStrut(10));
		tBox.add(aBox);
		
		
		// 欢迎语
		JLabel label = new JLabel(user.getNickname()+"你好,欢迎您！");
		label.setFont(new Font("楷体", Font.BOLD, 40));
		
		Box box=Box.createVerticalBox();
		box.add(Box.createVerticalStrut(120));
		box.add(cBox1);
		box.add(Box.createVerticalStrut(5));
		box.add(cBox2);
		box.add(Box.createVerticalStrut(20));
		box.add(tBox);
		box.add(Box.createVerticalStrut(15));
		box.add(kBox);
		box.add(Box.createVerticalStrut(15));
		box.add(rBox);
		box.add(Box.createVerticalStrut(15));
		box.add(eBox);
		
		bgPanel.add(label);
		bgPanel.add(box);
		
		jf.add(bgPanel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
