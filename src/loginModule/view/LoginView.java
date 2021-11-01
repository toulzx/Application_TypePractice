package loginModule.view;

import gameModule.view.StartView;
import loginModule.controller.HandleLogin;
import loginModule.model.*;
import loginModule.utils.ScreenUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * ��½��ͼ��
 * ���Ƶ�½����
 *
 */
public class LoginView {

	JFrame jf = new JFrame("��¼");

    final int WIDTH = 500;
    final int HEIGHT = 300;

    //��װ��ͼ
    public void init() throws Exception {
        //���ô�����ص�����
        jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        jf.setResizable(false);
        jf.setIconImage(ImageIO.read(new File("images\\logo.jpg")));//����logoͼƬ

        //���ô��ڵ�����
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images\\library.jpg")));
        bgPanel.setBounds(0,0,WIDTH,HEIGHT);
        //��װ��¼��ص�Ԫ��
        Box vBox = Box.createVerticalBox();

        //��װ�û���
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("�û�����");
        JTextField uField = new JTextField(15);

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        //��װ����
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("��    �룺");
        JPasswordField pField = new JPasswordField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        //��װ��ť
        Box btnBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton("��¼");
        JButton registBtn = new JButton("ע��");

        loginBtn.addActionListener(new ActionListener() {  //ʹ�������࣬ע��Ϊ����¼����ť�ļ�����
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//��ȡ�û����������
        		String username = uField.getText().trim();
        		String password = new String(pField.getPassword());
              
        		Login loginUser=new Login(username, password);
        		HandleLogin.queryVerify(loginUser);
              
        		if (loginUser.getLoginSuccess()){
        			//��¼�ɹ�,��ת����ҳ��
        			try {
        				new StartView(loginUser.getNowUser()).init();
        				jf.dispose();
        			} 
        			catch (Exception ex) {
        				ex.printStackTrace();
        			}
        		}
        		else{
        			// ��¼ʧ��
        			JOptionPane.showMessageDialog(jf,"������û������������������ע��");
        		}
        	}
        });
        
        registBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //��ת��ע��ҳ��
                try {
                    new RegisterView().init();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //��ǰ������ʧ
                jf.dispose();
            }
        });
        
        btnBox.add(loginBtn);
        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(registBtn);

        vBox.add(Box.createVerticalStrut(50));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(btnBox);
        
        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
