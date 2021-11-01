package loginModule.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.fasterxml.jackson.databind.ObjectMapper; // 导入外部包

import loginModule.model.Register;

/**
 * 注册处理：
 * 将模型中的数据转换为json字符串格式并写入到文件中；
 * 使用json存储，便于文件交互和阅读修改。
 * @author 段志超
 *
 */
public class HandleRegister {

	/**
	 * 注册模型处理方法：
	 * 对于参数“注册模型”，将其各属性及数据写入json文件；
	 * 使用了外部包jackson中的ObjectMapper类。利用该类的writeValue方法，将对象转换为json字符串并写入json文件。
	 * @see com.fasterxml.jackson.databind.ObjectMapper
	 * @see com.fasterxml.jackson.databind.ObjectMapper#writeValue(java.io.Writer, Object)
	 * @see com.fasterxml.jackson.databind.ObjectMapper#writeValue
	 * @param register 注册模型
	 */
	public static void writeRegisterModel(Register register) {
		// 创建Jackson的核心对象ObjectMapper
		ObjectMapper mapper=new ObjectMapper();
		
		try {
			// 将对象转换为json字符串
			String json=mapper.writeValueAsString(register);
			System.out.println(json);
			
			FileWriter userFile=new FileWriter("UserInfo.json", true);
			BufferedWriter writeLine= new BufferedWriter(userFile);
			writeLine.write(json);
//			// 将对象转换为json字符串，并以追加的方式写入文件
//			mapper.writeValue(userFile, register);
			writeLine.newLine();
			writeLine.close();
			register.setRegisterSuccess(true);
		}
		catch(Exception e) {
			register.setRegisterSuccess(false);
			e.printStackTrace();
		}
	}
	
//	public static void main(String args[]) {
//		Register newUser=new Register("3200429417", "123456", "男");
//		HandleRegister handleUser=new HandleRegister();
//		handleUser.writeRegisterModel(newUser);
//	}
}
