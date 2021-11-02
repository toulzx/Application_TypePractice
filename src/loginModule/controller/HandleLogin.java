package loginModule.controller;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper; // 导人外部包

import gameModule.model.GameModel;
import loginModule.model.Login;

/**
 * 登陆处理：
 * 查询存储用户数据的文件，检查用户是否已经注册；
 * 如果用户已注册检查密码是否正确。
 */
public class HandleLogin {

	/**
	 * 登陆模型处理方法：
	 * 对于参数 “登陆模型”，从文件中连续读取用户 json 数据并转换为 GameModel 对象；核对读取用户与登陆用户是否相同
	 * 	—— $ 若相同，核对密码是否正确
	 * 		—— 若密码正确，设置 LoginSuccess 为 true; 当前用户即为此用户；
	 * 		—— 若密码错误，设置 LoginSuccess 为 false; 设置当前用户为 null。
	 * 	—— $ 若读取到文件结尾，仍然未查找核对成功，设置 LoginSuccess 为 false; 设置当前用户为 null。
	 * @param login 登陆模型
	 * @see com.fasterxml.jackson.databind.ObjectMapper#readValue(byte[], Class)
	 */
	public static void queryVerify(Login login) {
		try {
			File file=new File("UserInfo.json");
			Reader in=new FileReader(file); // 底层流
			BufferedReader userRead=new BufferedReader(in); // 上层流
			String json=null;
			GameModel nowUser=null;
			ObjectMapper mapper = new ObjectMapper();
			while((json=userRead.readLine()) != null) { // 不断读取用户数据
				nowUser=mapper.readValue(json, GameModel.class); // 读取到的用户作为当前用户
				if(nowUser.getUsername().equals(login.getUsername())) {
					if(nowUser.getPasswrod().equals(login.getPasswrod())) {
						// 找到用户名，且密码正确，登陆成功
						login.setLoginSuccess(true);
						login.setNowUser(nowUser);
						break;
					}
					else {
						login.setLoginSuccess(false); // 密码错误，登陆失败
						login.setNowUser(null);
					}
				}
			}
			userRead.close(); // 关闭上层流
			if(login.getLoginSuccess() != true) { // 不存在该用户，登陆失败
				login.setNowUser(null);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}