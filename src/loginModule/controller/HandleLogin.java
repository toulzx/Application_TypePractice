package loginModule.controller;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper; // �����ⲿ��

import gameModule.model.GameModel;
import loginModule.model.Login;

/**
 * ��½����
 * ��ѯ�洢�û����ݵ��ļ�������û��Ƿ��Ѿ�ע�᣻
 * ����û���ע���������Ƿ���ȷ��
 * @author Administrator
 *
 */
public class HandleLogin {

	/**
	 * ��½ģ�ʹ�������
	 * ���ڲ�������½ģ�͡������ļ���������ȡ�û�json���ݲ�ת��ΪGameModel���󣻺˶Զ�ȡ�û����½�û��Ƿ���ͬ
	 * 	���� $����ͬ���˶������Ƿ���ȷ
	 * 		���� ��������ȷ������LoginSuccessΪtrue; ��ǰ�û���Ϊ���û���
	 * 		���� �������������LoginSuccessΪfalse; ���õ�ǰ�û�Ϊnull��
	 * 	���� $����ȡ���ļ���β����Ȼδ���Һ˶Գɹ�������LoginSuccessΪfalse; ���õ�ǰ�û�Ϊnull��
	 * @param login ��½ģ��
	 * @see com.fasterxml.jackson.databind.ObjectMapper#readValue(byte[], Class)
	 */
	public static void queryVerify(Login login) {
		try {
			File file=new File("UserInfo.json");
			Reader in=new FileReader(file); // �ײ���
			BufferedReader userRead=new BufferedReader(in); // �ϲ���
			String json=null;
			GameModel nowUser=null;
			ObjectMapper mapper = new ObjectMapper();
			while((json=userRead.readLine()) != null) { // ���϶�ȡ�û�����
				nowUser=mapper.readValue(json, GameModel.class); // ��ȡ�����û���Ϊ��ǰ�û�
				if(nowUser.getUsername().equals(login.getUsername())) {
					if(nowUser.getPasswrod().equals(login.getPasswrod())) { 
						// �ҵ��û�������������ȷ����½�ɹ�
						login.setLoginSuccess(true); 
						login.setNowUser(nowUser);
						break;
					}
					else {
						login.setLoginSuccess(false); // ������󣬵�½ʧ��
						login.setNowUser(null);
					}
				}
			}
			userRead.close(); //�ر��ϲ���
			if(login.getLoginSuccess() != true) { // �����ڸ��û�����½ʧ��
				login.setNowUser(null);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
