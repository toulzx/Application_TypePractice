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
 * ��ʼ��ͼ��
 * ���ƿ�ʼ����
 *
 * @author lzx
 */
public class StartView {
	
	JFrame jf = new JFrame("������Ϸ");
	private GameModel user=null;
	
	final int WIDTH = 740;
	final int HEIGHT = 550;
	
	public StartView(GameModel nowUser){
		user=nowUser;
	}

	// ��װ��ͼ
	public void init() throws Exception {
		// ���ô����������
		jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2,
				(ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
//		jf.setLayout(new FlowLayout());
		jf.setResizable(true);
		jf.setIconImage(ImageIO.read(new File("images\\logo.png"))); //����logo
		
		// ���ô�������
		// ����ͼƬ
		BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images\\1.jpg")));
		bgPanel.setBounds(0,0,WIDTH,HEIGHT);
		
		
		// ѡ��ؿ�
		Box cBox1 = Box.createHorizontalBox();
		Box cBox2 = Box.createHorizontalBox();
//		Dimension preferredSize=new Dimension(160,40);
		String choiceStr []= {"��һ��", "�ڶ���", "������", "����ģʽ"};
		JButton choiceBut []=new JButton[choiceStr.length];
		HandleChoice handleChoice = new HandleChoice(user);
		// ���ÿ���ѡ��Ĺؿ�
		for(int i=0; i<choiceStr.length; i++) {
			choiceBut[i]=new JButton(choiceStr[i]);
			if(user.getPassNum()>=i) // �����һ����ͨ�������ù�����Ϊ���Խ���
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
		
		
		// �ַ�ģʽ
		Box sBox = Box.createHorizontalBox();
		JButton startBut = new JButton("�� �� ģ ʽ");
		startBut.setBackground(Color.red);
//		preferredSize=new Dimension(150,70);
//		startBut.setPreferredSize(preferredSize);
		startBut.addActionListener(new ActionListener() { // Ϊ����ʼ��Ϸ����ťע�������
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ת����Ϸ����
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

		// �ı�ģʽ
		Box aBox = Box.createHorizontalBox();
		JButton articleCompareBut = new JButton("�� �� ģ ʽ");
		articleCompareBut.setBackground(Color.green);
		articleCompareBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				// ��ת�ı��ȽϽ���
				try{
					//��ʼ������
					Windowm windowm = new Windowm(user);
					//��������
					SimpleAttributeSet attrset = new SimpleAttributeSet();
					//�����С
					StyleConstants.setFontSize(attrset,16);
					//��ȡJTextPane����
					Document docs1=windowm.text1.getDocument();
					//���ó�����ʾ�ı�
					docs1.insertString(docs1.getLength(), "", attrset);
					Document docs2=windowm.text2.getDocument();
					docs2.insertString(docs2.getLength(), "�ֶ������������ѡ���ļ��\n����˶�����\n��ɫ��ʾ�����ַ�\n��ɫ��ʾ�����ȱʧ�ַ�", attrset);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
		aBox.add(articleCompareBut);
		
		// ���а�
		Box kBox=Box.createHorizontalBox();
		JButton rankListBut = new JButton("��  ��  ��");
		rankListBut.addActionListener(new ActionListener() { // Ϊ���а�ťע�������
			@Override
			public void actionPerformed(ActionEvent e) {
				List<GameModel> rankList = HandleDataList.getUserList(); // ��ȡ�����û��������б�
				try {
					 // ��ʾ���а���ͼ
					new RankListView(rankList, user).init();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		kBox.add(rankListBut);
		
		// �˳���½
		Box rBox=Box.createHorizontalBox();
		JButton reLoginBut = new JButton("�˳���½");
		reLoginBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					HandleDataList.saveCurrentUserData(user); // ������Ϸǰ�ȱ����û�����
					
					new LoginView().init(); // ��½����
					jf.dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		rBox.add(reLoginBut);
		
		// �˳���Ϸ
		Box eBox=Box.createHorizontalBox();
		JButton exitBut = new JButton("�˳���Ϸ");
		exitBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HandleDataList.saveCurrentUserData(user); // ������Ϸǰ�ȱ����û�����
				System.exit(0); // �˳�
			}
		});
		eBox.add(exitBut);

		Box tBox = Box.createHorizontalBox();
		tBox.add(sBox);
		tBox.add(Box.createHorizontalStrut(10));
		tBox.add(aBox);
		
		
		// ��ӭ��
		JLabel label = new JLabel(user.getNickname()+"���,��ӭ����");
		label.setFont(new Font("����", Font.BOLD, 40));
		
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
