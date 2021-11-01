package loginModule.view;

import javax.swing.*;
import java.awt.*;
/**
 * 这个类用来设置背景图片
 * @author 段志超
 *
 */
public class BackGroundPanel extends JPanel {
    /**
	 * 序列版本
	 */
	private static final long serialVersionUID = 1L;
	//声明图片
    private Image backIcon;
    public BackGroundPanel(Image backIcon){
        this.backIcon = backIcon;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //绘制背景
        g.drawImage(backIcon,0,0,this.getWidth(),this.getHeight(),null);

    }
}
