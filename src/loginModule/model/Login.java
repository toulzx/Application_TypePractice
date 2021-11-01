package loginModule.model;

import gameModule.model.GameModel;

/**
 * ��½ģ��
 * @author ��־��
 *
 */
public class Login {

	// ��������Ĭ��ֵ
	private String username=null, passwrod=null;// �û���������
	private boolean loginSuccess=false; // ��½�Ƿ�ɹ�
	private GameModel nowUser=null;
	
	/**
	 * ͨ�����췽���ṩ�Ĳ������õ�ǰҪ��½���û���������
	 * @param username �û���
	 * @param passwrod ����
	 */
	public Login(String username, String passwrod){
		this.username=username;
		this.passwrod=passwrod;
	}
	
	// ��������������������
	public void setUsername(String s) {
		username=s;
	}
	public String getUsername() {
		return username;
	}
	
	public void setPasswrod(String s) {
		passwrod=s;
	}
	public String getPasswrod() {
		return passwrod;
	}
	
	public void setLoginSuccess(boolean b) {
		loginSuccess=b;
	}
	public boolean getLoginSuccess() {
		return loginSuccess;
	}
	
	public void setNowUser(GameModel g) {
		nowUser=g;
	}
	public GameModel getNowUser() {
		return nowUser;
	}
	
	
}
