package loginModule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 注册模型
 * @author 段志超
 *
 */
public class Register {

	// 设置属性默认值
	protected String nickname=null; // 昵称
	protected String username=null, passwrod=null;// 用户名、密码和性别
	protected String sex=null;// 性别
	protected int passNum=0;// 通过关卡数
	protected long totalType=0;// 打字总个数
	protected long totalRight=0;// 正确个数
	protected long newType =0;// 新增打字总个数
	protected long newRight =0;// 新增正确个数

	// 排除属性，处理注册模型时，该成员变量不添加到json

	@JsonIgnore
	private boolean registerSuccess=false; // 注册是否成功
	
	/**
	 * 默认的构造方法，什么也不做，所有属性均为默认值
	 */
	public Register() {}
	
	/**
	 * 重载的构造方法提供参数设置以下属性，其他属性为默认值
	 * @param nickname 昵称
	 * @param username 用户名
	 * @param passwrod 密码
	 * @param sex 性别
	 */
	public Register(String nickname, String username, String passwrod, String sex){
		this.nickname=nickname;
		this.username=username;
		this.passwrod=passwrod;
		this.sex=sex;
	}
	
	// 设置域更改器和域访问器

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
