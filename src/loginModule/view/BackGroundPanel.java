package loginModule.view;

import javax.swing.*;
import java.awt.*;
/**
 * ������������ñ���ͼƬ
 * @author ��־��
 *
 */
public class BackGroundPanel extends JPanel {
    /**
	 * ���а汾
	 */
	private static final long serialVersionUID = 1L;
	//����ͼƬ
    private Image backIcon;
    public BackGroundPanel(Image backIcon){
        this.backIcon = backIcon;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //���Ʊ���
        g.drawImage(backIcon,0,0,this.getWidth(),this.getHeight(),null);

    }
}
