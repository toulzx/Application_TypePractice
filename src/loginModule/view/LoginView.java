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
 * 登陆视图：
 * 绘制登陆界面
 *
 */
public class LoginView {

    JFrame jf = new JFrame(" 登录 ");

    final int WIDTH = 500;
    final int HEIGHT = 300;

    // 组装视图
    public void init() throws Exception {
        // 设置窗口相关的属性
        jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        jf.setResizable(false);
        jf.setIconImage(ImageIO.read(new File("src\\images\\logo.jpg")));// 设置 logo 图片

        // 设置窗口的内容
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("src\\images\\library.jpg")));
        bgPanel.setBounds(0,0,WIDTH,HEIGHT);
        // 组装登录相关的元素
        Box vBox = Box.createVerticalBox();

        // 组装用户名
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel(" 用户名：");
        JTextField uField = new JTextField(15);

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        // 组装密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel(" 密    码：");
        JPasswordField pField = new JPasswordField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        // 组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton(" 登录 ");
        JButton registBtn = new JButton(" 注册 ");

        loginBtn.addActionListener(new ActionListener() {  // 使用匿名类，注册为 “登录” 按钮的监视器
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户输入的数据
                String username = uField.getText().trim();
                String password = new String(pField.getPassword());

                Login loginUser=new Login(username, password);
                HandleLogin.queryVerify(loginUser);

                if (loginUser.getLoginSuccess()){
                    // 登录成功，跳转到主页面
                    try {
                        new StartView(loginUser.getNowUser()).init();
                        jf.dispose();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else{
                    // 登录失败
                    JOptionPane.showMessageDialog(jf," 密码或用户名错误，请重新输入或注册 ");
                }
            }
        });

        registBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 跳转到注册页面
                try {
                    new RegisterView().init();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                // 当前界面消失
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
