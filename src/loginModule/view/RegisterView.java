package loginModule.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import loginModule.controller.HandleRegister;
import loginModule.model.Register;
import loginModule.utils.ScreenUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * ע����ͼ��
 * ����ע�����
 * @author ��־��
 *
 */
public class RegisterView {
    JFrame jf = new JFrame("ע��");

    final int WIDTH = 500;
    final int HEIGHT = 400;


    //��װ��ͼ
    public void init() throws Exception {
        //���ô��ڵ�����
        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setResizable(false);
        jf.setIconImage(ImageIO.read(new File("images\\logo.png")));

        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images\\regist.jpg")));
        bgPanel.setBounds(0,0,WIDTH,HEIGHT);


        Box vBox = Box.createVerticalBox();
        
        //��װ�ǳ�
        Box nBox = Box.createHorizontalBox();
        JLabel nLabel = new JLabel("��    �ƣ�");
        JTextField nField = new JTextField(15);

        nBox.add(nLabel);
        nBox.add(Box.createHorizontalStrut(20));
        nBox.add(nField);

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

        //��װ�Ա�
        Box gBox = Box.createHorizontalBox();
        JLabel gLabel = new JLabel("��    ��");
        JRadioButton maleBtn = new JRadioButton("��",true);
        JRadioButton femaleBtn = new JRadioButton("Ů",false);

        //ʵ�ֵ�ѡ��Ч��
        ButtonGroup bg = new ButtonGroup();
        bg.add(maleBtn);
        bg.add(femaleBtn);

        gBox.add(gLabel);
        gBox.add(Box.createHorizontalStrut(20));
        gBox.add(maleBtn);
        gBox.add(femaleBtn);
        gBox.add(Box.createHorizontalStrut(120));

        //��װ��֤��
//        Box cBox = Box.createHorizontalBox();
//        JLabel cLabel = new JLabel("��֤�룺");
//        JTextField cField = new JTextField(4);
//        JLabel cImg = new JLabel(new ImageIcon(ImageRequestUtils.getImage("http://localhost:8080/code/getCheckCode")));
//        //��ĳ�����������ʾ��Ϣ
//        cImg.setToolTipText("���ˢ��");
//        cImg.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                cImg.setIcon(new ImageIcon(ImageRequestUtils.getImage("http://localhost:8080/code/getCheckCode")));
//                cImg.updateUI();
//            }
//        });
//
//        cBox.add(cLabel);
//        cBox.add(Box.createHorizontalStrut(20));
//        cBox.add(cField);
//        cBox.add(cImg);

        //��װ��ť
        Box btnBox = Box.createHorizontalBox();
        JButton registBtn = new JButton("ע��");
        JButton backBtn = new JButton("���ص�¼ҳ��");

        registBtn.addActionListener(new ActionListener() { //ʹ�������࣬ע��Ϊ��ע�ᡱ��ť�ļ�����
            @Override
            public void actionPerformed(ActionEvent e) {
                //��ȡ�û�¼�����Ϣ
                String username = uField.getText().trim();
				String password = new String(pField.getPassword());
                String nickname = nField.getText().trim();
                String gender = bg.isSelected(maleBtn.getModel())? maleBtn.getText():femaleBtn.getText();
//                String checkCode = cField.getText().trim();

                Register newUser=new Register(nickname, username, password, gender);
                HandleRegister.writeRegisterModel(newUser);
                
                if(newUser.getRegisterSuccess()) { 
                	// ע��ɹ�
                	JOptionPane.showMessageDialog(jf,"ע��ɹ����������ص�¼ҳ��");
                    try {
                        new LoginView().init();
                        jf.dispose();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else{
                    //ע��ʧ��
                    JOptionPane.showMessageDialog(jf,"ע��ʧ�ܣ�����������");
                }
            }
        });

        backBtn.addActionListener(new ActionListener() { // Ϊ�����ص�½ҳ�桱��ťע�������
            @Override
            public void actionPerformed(ActionEvent e) {
                //���ص���¼ҳ�漴��
                try {
                    new LoginView().init();
                    jf.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        btnBox.add(registBtn);
        btnBox.add(Box.createHorizontalStrut(80));
        btnBox.add(backBtn);

        vBox.add(Box.createVerticalStrut(50));
        vBox.add(nBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(gBox);
        vBox.add(Box.createVerticalStrut(20));
//        vBox.add(cBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(btnBox);

        bgPanel.add(vBox);
        
        jf.add(bgPanel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
