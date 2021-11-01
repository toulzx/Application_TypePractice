package gameModule.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gameModule.model.GameModel;

/**
 * �Ѷ�ѡ����
 * �����û�ѡ��Ĺؿ���������Ϸ�Ѷ�
 * @author Administrator
 *
 */
public class HandleChoice implements ActionListener {

	GameModel user=null;
	
	public HandleChoice(GameModel user) {
		this.user=user;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// ���ò�ͬ�ؿ��Ѷ�
			switch(e.getActionCommand()) {
			case "��һ��" :
				user.setDifficulty(5);
				break;
			case "�ڶ���" :
				user.setDifficulty(10);
				break;
			case "������" :
				user.setDifficulty(15);
				break;
			case "����ģʽ" :
				String difficultyCustom=JOptionPane.showInputDialog(null,"����������Ҫ���Ѷ� (ֻ���ַ�ģʽ��Ч)",
						"�Ѷ��Զ���",JOptionPane.PLAIN_MESSAGE);
				if(difficultyCustom != null) {
					user.setDifficulty(Integer.parseInt(difficultyCustom));
				}
				break;
			}
	
	}

}
