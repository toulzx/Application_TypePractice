package gameModule.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gameModule.model.GameModel;

/**
 * 难度选择处理：
 * 根据用户选择的关卡，设置游戏难度
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
		// 设置不同关卡难度
			switch(e.getActionCommand()) {
			case "第一关" :
				user.setDifficulty(5);
				break;
			case "第二关" :
				user.setDifficulty(10);
				break;
			case "第三关" :
				user.setDifficulty(15);
				break;
			case "自由模式" :
				String difficultyCustom=JOptionPane.showInputDialog(null,"请输入你想要的难度 (只对字符模式生效)",
						"难度自定义",JOptionPane.PLAIN_MESSAGE);
				if(difficultyCustom != null) {
					user.setDifficulty(Integer.parseInt(difficultyCustom));
				}
				break;
			}
	
	}

}
