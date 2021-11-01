package loginModule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ע��ģ��
 * @author ��־��
 *
 */
public class Register {

	// ��������Ĭ��ֵ
	protected String nickname=null; // �ǳ�
	protected String username=null, passwrod=null;// �û�����������Ա�
	protected String sex=null;// �Ա�
	protected int passNum=0;// ͨ���ؿ���
	protected long totalType=0;// �����ܸ���
	protected long totalRight=0;// ��ȷ����
	protected long newType =0;// ���������ܸ���
	protected long newRight =0;// ������ȷ����

	// �ų����ԣ�����ע��ģ��ʱ���ó�Ա��������ӵ�json

	@JsonIgnore
	private boolean registerSuccess=false; // ע���Ƿ�ɹ�
	
	/**
	 * Ĭ�ϵĹ��췽����ʲôҲ�������������Ծ�ΪĬ��ֵ
	 */
	public Register() {}
	
	/**
	 * ���صĹ��췽���ṩ���������������ԣ���������ΪĬ��ֵ
	 * @param nickname �ǳ�
	 * @param username �û���
	 * @param passwrod ����
	 * @param sex �Ա�
	 */
	public Register(String nickname, String username, String passwrod, String sex){
		this.nickname=nickname;
		this.username=username;
		this.passwrod=passwrod;
		this.sex=sex;
	}
	
	// ��������������������

	public void setNickname(String s) {
		nickname=s;
	}
	public String getNickname() {
		return nickname;
	}
	
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
	
	public void setSex(String s) {
		sex=s;
	}
	public String getSex() {
		return sex;
	}
	
	public void setPassNum(int n) {
		passNum=n;
	}
	public int getPassNum() {
		return passNum;
	}
	
	public void setTotalType(long n) {
		totalType=n;
	}
	public long getTotalType() {
		return totalType;
	}
	
	public void setTotalRight(long n) {
		totalRight=n;
	}
	public long getTotalRight() {
		return totalRight;
	}
	
	public void setRegisterSuccess(boolean b) {
		registerSuccess=b;
	}
	public boolean getRegisterSuccess() {
		return registerSuccess;
	}

	public long getNewType() {
		return newType;
	}
	public void setNewType(long newType) {
		this.newType = newType;
	}

	public long getNewRight() {
		return newRight;
	}
	public void setNewRight(long newRight) {
		this.newRight = newRight;
	}

}
