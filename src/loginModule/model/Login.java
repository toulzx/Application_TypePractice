package loginModule.model;

import gameModule.model.GameModel;

/**
 * 登陆模型
 */
public class Login {

	// 设置属性默认值
	private String username=null, password =null;// 用户名和密码
	private boolean isLoginSuccess =false; // 登陆是否成功
	private GameModel currentUser =null;

	/**
	 * 通过构造方法提供的参数设置当前要登陆的用户名和密码
	 * @param username 用户名
	 * @param password 密码
	 */
	public Login(String username, String password){
		this.username=username;
		this.password = password;
	}

	// 设置域更改器和域访问器
	public void setUsername(String s) {
		username=s;
	}
	public String getUsername() {
		return username;
	}

	public void setPassword(String s) {
		password =s;
	}
	public String getPassword() {
		return password;
	}

	public void setLoginSuccess(boolean b) {
		isLoginSuccess =b;
	}
	public boolean getLoginSuccess() {
		return isLoginSuccess;
	}

	public void setCurrentUser(GameModel g) {
		currentUser =g;
	}
	public GameModel getCurrentUser() {
		return currentUser;
	}


}