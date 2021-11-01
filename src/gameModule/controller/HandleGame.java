package gameModule.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import gameModule.model.GameModel;

/**
 * ������Ϸ��
 * �������û������Լ�ѡ��Ĺؿ����ɶ�Ӧ��Ϸ����
 * @author ��־��
 *
 */
public class HandleGame extends JPanel implements ActionListener ,KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GameModel user=null;
	private Timer thread=null;
	private int charNumb=0;
	private int []x=null;
	private int []y=null;
	private char []ch=null;
	
	long typeCount=0;
	long rightCount=0;
	double accuracyCount=0;
	
	int speed=1;
	int count=1;
	int time=50;
	int timecount=0;
	
	public HandleGame(GameModel user){
		this.user=user;
		charNumb=user.getDifficulty();
		x = new int[charNumb];
		y = new int[charNumb];
		ch = new char[charNumb];
		for(int i = 0; i < charNumb; i++){
        	x[i] = (int)(100 + Math.random() * 520); 
        	y[i] = (int)(Math.random() * 300);
            ch[i] = (char)(Math.random() * 26 + 97);
        }
		addKeyListener(this);
	}
	
	public Timer getThread() {
		return thread;
	}
	public void setThread(Timer thread) {
		this.thread = thread;
	}
	
	
	@Override
	public void paint(Graphics g){
        super.paint(g);
        ImageIcon icon1 = new ImageIcon("images\\2.jpg");
        ImageIcon icon2 = new ImageIcon("images\\3.jpg");
        Font f1 = new Font("����",Font.BOLD,30);
        Font f2 = new Font("����",Font.BOLD,28);
        g.setColor(Color.RED);
        g.setFont(f2);
        if(time <= 0){
        	thread.stop();
        	HandleDataList.saveCurrentUserData(user); // ������Ϸǰ�ȱ����û�����
        	g.drawImage(icon2.getImage(), 0, 0, getSize().width, getSize().height, this);
        	if(accuracyCount > 0.6) {
        		// ���׼ȷ�ʴ��� 60%���ù�ͨ��
        		int passNum=user.getPassNum();
        		if(charNumb/5 > passNum && passNum<4) {
					passNum++; // �����ǰ�ؿ�δͨ�أ�ͨ������һ
				}
        		user.setPassNum(passNum);
            	JOptionPane.showConfirmDialog(this, user.getNickname()+"����ϲ��˳��ͨ������",
            			"��Ϸ����",JOptionPane.YES_OPTION);
        	}
        	else {
        		JOptionPane.showConfirmDialog(this, "���ٲ��У��ٽ�����Ŷ��",
            			"��Ϸ����",JOptionPane.YES_OPTION);
        	}
        }
        
        g.drawImage(icon1.getImage(), 0, 0, getSize().width, getSize().height, this);
        g.drawString("��ȷ��:" + rightCount, 10, 30);
        g.drawString("��ȷ�ʣ�" + (int)(accuracyCount*100) + "%", 200, 30);
        g.drawString("ʱ�䣺" + time, 510, 30);
        g.setFont(f1);
        g.setColor(Color.BLUE);
        for(int i = 0; i < charNumb; i++){
            g.drawString("" + (char)ch[i], x[i], y[i]);
        }
    }
	

	@Override
	public void actionPerformed(ActionEvent e) {
		int num;
		if(charNumb<=5) {
			num = 250;
		}
		else if(charNumb<=10 && charNumb>5) {
			num=500;
		}
		else {
			num=750;
		}
		for(int i=0; i<charNumb; i++) {
			count++;
			timecount++;
			y[i]+=speed;
			if(y[i]>500) {
				// �����ĸ���䵽�����⻹δ��ȷ���룬������һ
				y[i]=0;
				typeCount++;
			}
			if(count>5000) {
				speed+=1;
				count=1;
			}
			if(timecount >num) {
				time--;
				timecount=0;
			}
		}
		// ��������
		user.setTypeCount(typeCount);
		accuracyCount=(double)rightCount/typeCount;
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		typeCount++; // �û��û����̣��������һ
		
		int yy=-1;
		int index=-1;
		for(int i=0; i<charNumb; i++) {
			if(e.getKeyChar() == ch[i]) { 
				// ������ȷ����ȡ��ȷ�������ĸλ�ã�������һ
				if(yy < y[i]) {
					rightCount++;
					yy=y[i];
					index=i;
					break;
				}
			}
		}
		if(index > -1) {
			y[index] = 0;
			x[index] = (int)(Math.random() * 500);
			ch[index] = (char)(Math.random() * 26 + 97);
		}
		// ��������
		user.setTypeCount(typeCount);
		user.setRightCount(rightCount);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
