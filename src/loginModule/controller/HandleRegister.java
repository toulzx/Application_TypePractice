package loginModule.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.fasterxml.jackson.databind.ObjectMapper; // �����ⲿ��

import loginModule.model.Register;

/**
 * ע�ᴦ��
 * ��ģ���е�����ת��Ϊjson�ַ�����ʽ��д�뵽�ļ��У�
 * ʹ��json�洢�������ļ��������Ķ��޸ġ�
 * @author ��־��
 *
 */
public class HandleRegister {

	/**
	 * ע��ģ�ʹ�������
	 * ���ڲ�����ע��ģ�͡�����������Լ�����д��json�ļ���
	 * ʹ�����ⲿ��jackson�е�ObjectMapper�ࡣ���ø����writeValue������������ת��Ϊjson�ַ�����д��json�ļ���
	 * @see com.fasterxml.jackson.databind.ObjectMapper
	 * @see com.fasterxml.jackson.databind.ObjectMapper#writeValue(java.io.Writer, Object)
	 * @see com.fasterxml.jackson.databind.ObjectMapper#writeValue
	 * @param register ע��ģ��
	 */
	public static void writeRegisterModel(Register register) {
		// ����Jackson�ĺ��Ķ���ObjectMapper
		ObjectMapper mapper=new ObjectMapper();
		
		try {
			// ������ת��Ϊjson�ַ���
			String json=mapper.writeValueAsString(register);
			System.out.println(json);
			
			FileWriter userFile=new FileWriter("UserInfo.json", true);
			BufferedWriter writeLine= new BufferedWriter(userFile);
			writeLine.write(json);
//			// ������ת��Ϊjson�ַ���������׷�ӵķ�ʽд���ļ�
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
//		Register newUser=new Register("3200429417", "123456", "��");
//		HandleRegister handleUser=new HandleRegister();
//		handleUser.writeRegisterModel(newUser);
//	}
}
