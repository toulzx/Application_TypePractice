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
 * ���а���ͼ
 * �������а����
 *
 * @author lzx
 */
public class RankListView {

	JFrame jf=new JFrame("���а�");


	final int WIDTH = 800;
	final int HEIGHT = 400;

	List<GameModel> mRankList;
	private GameModel mCurrentUser;
	
	public RankListView(List<GameModel> mRankList, GameModel mCurrentUser){
		this.mRankList = mRankList;
		this.mCurrentUser = mCurrentUser;
	}
	
	public void init() {
		// ���ô�������
		jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, 
				(ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT); // ���ô��ھ���
		jf.setLayout(new FlowLayout());
		jf.setResizable(true);
		try {
			jf.setIconImage(ImageIO.read(new File("images\\logo.png"))); //����logo
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ���ô�������
		
		// ����ͼƬ
		BackGroundPanel bgPanel = null;
		try {
			bgPanel = new BackGroundPanel(ImageIO.read(new File("images\\1.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert bgPanel != null;
		bgPanel.setBounds(0,0,WIDTH,HEIGHT);
		
		// ����
		JLabel title = new JLabel("���а�");
		title.setFont(new Font("����", Font.BOLD, 30));


		// ��Ŀ
		JLabel nameJl = new JLabel("����");
		JLabel trueCountJl = new JLabel("�����ܸ���");
		JLabel scoreJl = new JLabel("��ʷ��ȷ��");
		JLabel progressJl = new JLabel("�ϴ���ȷ��");
		JLabel deleteJl = new JLabel("����");

		Font f1 = new Font("����", Font.BOLD, 22);
		Font f2 = new Font("����", Font.BOLD, 20);

		nameJl.setFont(f1);
		trueCountJl.setFont(f1);
		scoreJl.setFont(f1);
		progressJl.setFont(f1);
		deleteJl.setFont(f1);

		// ����
		Box box = Box.createVerticalBox();
		box.add(title);
		box.add(Box.createVerticalStrut(15));

		// ��Ŀ-����
		Box boxHone = Box.createVerticalBox();
		boxHone.add(nameJl);

		// ��Ŀ-�����ܸ���
		Box boxHtwo = Box.createVerticalBox();
		boxHtwo.add(trueCountJl);

		// ��Ŀ-��ȷ��
		Box boxHthree = Box.createVerticalBox();
        boxHthree.add(scoreJl);

        // ��һ�ε���ȷ��
		Box boxHfour = Box.createVerticalBox();
		boxHfour.add(progressJl);

		// ��Ŀ-��ť
		Box boxHfive = Box.createVerticalBox();
		boxHfive.add(deleteJl);


		// ��Ŀ������-����ÿ���û���Ϣ
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
					// ����ǹ���Ա��ɾ��������������
					if (HandleDataList.deleteUser(mCurrentUser, finalCurrentUser)) {
						JOptionPane.showMessageDialog (jf, "ɾ���ɹ�", "��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
						try {
							jf.dispose();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog (jf, "ɾ��ʧ�ܣ���û�д�Ȩ��", "��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			boxHfive.add(deleteBtn);
		}

        // ��Ŀ����
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
