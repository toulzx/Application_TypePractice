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
 * 注册视图：
 * 绘制注册界面
 * @author 段志超
 *
 */
public class RegisterView {
    JFrame jf = new JFrame("注册");

    final int WIDTH = 500;
    final int HEIGHT = 400;


    //组装视图
    public void init() throws Exception {
        //设置窗口的属性
        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setResizable(false);
        jf.setIconImage(ImageIO.read(new File("images\\logo.png")));

        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images\\regist.jpg")));
        bgPanel.setBounds(0,0,WIDTH,HEIGHT);


        Box vBox = Box.createVerticalBox();
        
        //组装昵称
        Box nBox = Box.createHorizontalBox();
        JLabel nLabel = new JLabel("昵    称：");
        JTextField nField = new JTextField(15);

        nBox.add(nLabel);
        nBox.add(Box.createHorizontalStrut(20));
        nBox.add(nField);

        //组装用户名
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("用户名：");
        JTextField uField = new JTextField(15);

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        //组装密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("密    码：");
        JPasswordField pField = new JPasswordField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        //组装性别
        Box gBox = Box.createHorizontalBox();
        JLabel gLabel = new JLabel("性    别：");
        JRadioButton maleBtn = new JRadioButton("男",true);
        JRadioButton femaleBtn = new JRadioButton("女",false);

        //实现单选的效果
        ButtonGroup bg = new ButtonGroup();
        bg.add(maleBtn);
        bg.add(femaleBtn);

        gBox.add(gLabel);
        gBox.add(Box.createHorizontalStrut(20));
        gBox.add(maleBtn);
        gBox.add(femaleBtn);
        gBox.add(Box.createHorizontalStrut(120));

        //组装验证码
//        Box cBox = Box.createHorizontalBox();
//        JLabel cLabel = new JLabel("验证码：");
//        JTextField cField = new JTextField(4);
//        JLabel cImg = new JLabel(new ImageIcon(ImageRequestUtils.getImage("http://localhost:8080/code/getCheckCode")));
//        //给某个组件设置提示信息
//        cImg.setToolTipText("点击刷新");
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

        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton registBtn = new JButton("注册");
        JButton backBtn = new JButton("返回登录页面");

        registBtn.addActionListener(new ActionListener() { //使用匿名类，注册为“注册”按钮的监视器
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户录入的信息
                String username = uField.getText().trim();
				String password = new String(pField.getPassword());
                String nickname = nField.getText().trim();
                String gender = bg.isSelected(maleBtn.getModel())? maleBtn.getText():femaleBtn.getText();
//                String checkCode = cField.getText().trim();

                Register newUser=new Register(nickname, username, password, gender);
                HandleRegister.writeRegisterModel(newUser);
                
                if(newUser.getRegisterSuccess()) { 
                	// 注册成功
                	JOptionPane.showMessageDialog(jf,"注册成功，即将返回登录页面");
                    try {
                        new LoginView().init();
                        jf.dispose();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else{
                    //注册失败
                    JOptionPane.showMessageDialog(jf,"注册失败，请重新输入");
                }
            }
        });

        backBtn.addActionListener(new ActionListener() { // 为“返回登陆页面”按钮注册监视器
            @Override
            public void actionPerformed(ActionEvent e) {
                //返回到登录页面即可
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
