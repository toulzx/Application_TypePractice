package gameModule.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import gameModule.controller.HandleDataList;
import gameModule.model.GameModel;
import loginModule.view.BackGroundPanel;
import loginModule.utils.ScreenUtils;

/**
 * 排行榜视图
 * 绘制排行榜界面
 *
 * @author lzx
 */
public class RankListView {

	JFrame jf=new JFrame("排行榜");


	final int WIDTH = 800;
	final int HEIGHT = 400;

	List<GameModel> mRankList;
	private GameModel mCurrentUser;
	
	public RankListView(List<GameModel> mRankList, GameModel mCurrentUser){
		this.mRankList = mRankList;
		this.mCurrentUser = mCurrentUser;
	}
	
	public void init() {
		// 设置窗口属性
		jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, 
				(ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT); // 设置窗口居中
		jf.setLayout(new FlowLayout());
		jf.setResizable(true);
		try {
			jf.setIconImage(ImageIO.read(new File("images\\logo.png"))); //设置logo
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 设置窗口内容
		
		// 背景图片
		BackGroundPanel bgPanel = null;
		try {
			bgPanel = new BackGroundPanel(ImageIO.read(new File("images\\1.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert bgPanel != null;
		bgPanel.setBounds(0,0,WIDTH,HEIGHT);
		
		// 标题
		JLabel title = new JLabel("排行榜");
		title.setFont(new Font("楷体", Font.BOLD, 30));


		// 条目
		JLabel nameJl = new JLabel("姓名");
		JLabel trueCountJl = new JLabel("打字总个数");
		JLabel scoreJl = new JLabel("历史正确率");
		JLabel progressJl = new JLabel("上次正确率");
		JLabel deleteJl = new JLabel("管理");

		Font f1 = new Font("楷体", Font.BOLD, 22);
		Font f2 = new Font("楷体", Font.BOLD, 20);

		nameJl.setFont(f1);
		trueCountJl.setFont(f1);
		scoreJl.setFont(f1);
		progressJl.setFont(f1);
		deleteJl.setFont(f1);

		// 标题
		Box box = Box.createVerticalBox();
		box.add(title);
		box.add(Box.createVerticalStrut(15));

		// 条目-姓名
		Box boxHone = Box.createVerticalBox();
		boxHone.add(nameJl);

		// 条目-打字总个数
		Box boxHtwo = Box.createVerticalBox();
		boxHtwo.add(trueCountJl);

		// 条目-正确率
		Box boxHthree = Box.createVerticalBox();
        boxHthree.add(scoreJl);

        // 上一次的正确率
		Box boxHfour = Box.createVerticalBox();
		boxHfour.add(progressJl);

		// 条目-按钮
		Box boxHfive = Box.createVerticalBox();
		boxHfive.add(deleteJl);


		// 条目迭代器-输入每个用户信息
		for (GameModel user : mRankList) {
			JLabel name = new JLabel(user.getNickname());
			name.setFont(f2);
			boxHone.add(name);

			JLabel truenum = new JLabel("" + user.getTotalType());
			truenum.setFont(f2);
			boxHtwo.add(truenum);

			JLabel score = new JLabel("" + (int) (user.getAccuracy() * 100) + "%");
			score.setFont(f2);
			boxHthree.add(score);

			JLabel progress = new JLabel("" + (int)(user.getNewAccuracy() * 100) + "%");
			progress.setFont(f2);
			boxHfour.add(progress);

			JButton deleteBtn = new JButton("delete");
			deleteBtn.setBackground(Color.yellow);
			deleteBtn.setPreferredSize(new Dimension(70,20));
			GameModel finalCurrentUser = user;
			deleteBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					// 如果是管理员，删除键才能起作用
					if (HandleDataList.deleteUser(mCurrentUser, finalCurrentUser)) {
						JOptionPane.showMessageDialog (jf, "删除成功", "提示消息",JOptionPane.WARNING_MESSAGE);
						try {
							jf.dispose();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog (jf, "删除失败，你没有此权限", "提示消息",JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			boxHfive.add(deleteBtn);
		}

        // 条目容器
		Box boxH = Box.createHorizontalBox();
        boxH.add(boxHone);
        boxH.add(Box.createHorizontalStrut(30));
        boxH.add(boxHtwo);
        boxH.add(Box.createHorizontalStrut(30));
        boxH.add(boxHthree);
		boxH.add(Box.createHorizontalStrut(30));
        boxH.add(boxHfour);
        boxH.add(Box.createHorizontalStrut(30));
        boxH.add(boxHfive);

        box.add(boxH);
		
        jf.add(box);
        jf.setVisible(true);
	}

}
