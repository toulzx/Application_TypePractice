package loginModule.model;

import gameModule.model.GameModel;

/**
 * 登陆模型
 * @author 段志超
 *
 */
public class Login {

	// 设置属性默认值
	private String username=null, passwrod=null;// 用户名和密码
	private boolean loginSuccess=false; // 登陆是否成功
	private GameModel nowUser=null;
	
	/**
	 * 通过构造方法提供的参数设置当前要登陆的用户名和密码
	 * @param username 用户名
	 * @param passwrod 密码
	 */
	public Login(String username, String passwrod){
		this.username=username;
		this.passwrod=passwrod;
	}
	
	// 设置域更改器和域访问器
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
