package gameModule.controller;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import gameModule.model.GameModel;



/**
 * 排行榜处理：
 * 从用户数据文件中读取所有用户数据，并按某一属性进行排名
 */
public class HandleDataList {

	public static final String USERNAME_ROOT = "root";
	private static final int LEVEL_FREE_NUM = 999;

	private static List<GameModel> mUserList = new ArrayList<GameModel>(); // 用户列表


	/**
	 * 读取 Json 文件中的数据
	 * @return java.util.List<gameModule.model.GameModel>
	 * @date 2021/10/28 14:34
	 */
	private static List<GameModel> fileRead() {

		try {
			File file = new File("UserInfo.json");
			Reader in = new FileReader(file); // 底层流
			BufferedReader userRead = new BufferedReader(in); // 上层流
			String json = null;
			GameModel readUser = null;
			ObjectMapper mapper = new ObjectMapper();
			while ((json = userRead.readLine()) != null) { // 不断读取用户数据
				readUser = mapper.readValue(json, GameModel.class); // 读取到的用户
				if (readUser.getTotalRight() != 0) {
					readUser.setNewAccuracy((double) readUser.getNewRight() / readUser.getNewType());
					readUser.setAccuracy((double) readUser.getTotalRight() / readUser.getTotalType());
				}
				mUserList.add(readUser); // 将读取到的用户添加到用户列表
			}
			userRead.close(); // 关闭上层流
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sortList(mUserList);

	}

	/**
	 * 更新 Json 文件中的数据
	 * 在退出登陆或退出游戏前将该用户的所有数据存入用户文件
	 * @param currentUser:
	 * @date 2021/10/28 15:15
	 */
	public static void saveCurrentUserData(GameModel currentUser) {

		if (currentUser.getRightCount() != 0){
			currentUser.setNewRight(currentUser.getRightCount());
			currentUser.setNewType(currentUser.getTypeCount());
		}
		currentUser.setNewAccuracy((double)currentUser.getNewRight()/currentUser.getNewType());
		currentUser.setTotalRight(currentUser.getTotalRight()+currentUser.getRightCount());
		currentUser.setTotalType(currentUser.getTotalType()+currentUser.getTypeCount());
		currentUser.setAccuracy((double)currentUser.getTotalRight()/currentUser.getTotalType());
		ObjectMapper mapper=new ObjectMapper();
		List<String> users = new ArrayList<String>(); // 所有用户

		try {
			String nowJson=mapper.writeValueAsString(currentUser); // 当前用户对应的 Json 字符串
			String readJson=null; // 读取到的用户对应的 Json 字符串
			GameModel readUser=null; // 读取到的用户
			File file=new File("UserInfo.json");
			Reader in=new FileReader(file); // 底层流
			BufferedReader userRead=new BufferedReader(in); // 上层流
			while((readJson=userRead.readLine()) != null) { // 不断读取用户对应的 Json 字符串
				// 更新用户数据
				readUser=mapper.readValue(readJson, GameModel.class);
				if(currentUser.getUsername().equals(readUser.getUsername())) {
					users.add(nowJson); // 读取到当前用户的原始数据，将新数据加入用户列表
				}
				else {
					users.add(readJson);
				}
			}
			userRead.close();

			Writer out=new FileWriter(file, false);
			BufferedWriter writeLine=new BufferedWriter(out); // 上层流
			Iterator<String> it = users.iterator();
			while(it.hasNext()) { // 将跟新数据后的用户列表重新写入文件
				writeLine.write(it.next());
				writeLine.newLine();
			}
			writeLine.close();

		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 按正确率排序
	 * @param userList:
	 * @return void
	 * @date 2021/10/28 14:52
	 * @author tou
	 */
	public static List<GameModel> sortList(List<GameModel> userList) {
		// 对用户列表按正确率进行排序
		Collections.sort(userList, new Comparator<>() { // 使用 Comparator 比较器接口
			@Override
			public int compare(GameModel user1, GameModel user2) {
				return ((Double) user2.getAccuracy()).compareTo(user1.getAccuracy()); // 按降序排列
			}
		});
		return userList;
	}

	/**
	 * 获取用户数据列表
	 * @return java.util.List<gameModule.model.GameModel>
	 * @date 2021/10/28 14:40
	 * @author tou
	 */
	public static List<GameModel> getUserList() {
//		if (mUserList.size() == 0) {
			mUserList = new ArrayList<>();
			mUserList = fileRead();
//		}
		return mUserList;
	}

	/**
	 * 删除某用户及其数据
	 * @param currentUser :
	 * @param targetUser :
	 * @return boolean 返回删除结果成功与否
	 * @date 2021/10/28 14:42
	 * @author tou
	 */
	public static boolean deleteUser(GameModel currentUser, GameModel targetUser) {
		if (USERNAME_ROOT.equals(currentUser.getUsername())) {
			boolean isSuccessful = mUserList.remove(targetUser);
			fileWrite();
			return isSuccessful;
		} else {
			return false;
		}
	}




	/**
	 * 打开关卡权限
	 * @param currentUser:
	 * @param targetUser :
	 * @return boolean 返回设置是否成功
	 * @date 2021/10/28 14:57
	 * @author tou
	 */
	public static boolean setLevelFree(GameModel currentUser, GameModel targetUser) {
		if (USERNAME_ROOT.equals(currentUser.getUsername())) {
			targetUser.setPassNum(LEVEL_FREE_NUM);
			saveCurrentUserData(targetUser);
			return true;
		} else {
			return false;
		}
	}



	/**
	 * 覆写用户数据文件
	 * @return boolean
	 * @date 2021/10/28 16:27
	 * @author tou
	 */
	private static boolean fileWrite() {
		// ����ļ����ݣ���д
		clearInfoForFile("UserInfo.json");
		// ����Jackson�ĺ��Ķ���ObjectMapper
		ObjectMapper mapper=new ObjectMapper();
		PrintStream stream = null;
		try {
			for (GameModel gameModel : mUserList) {
				// ������ת��Ϊjson�ַ���
				String json = mapper.writeValueAsString(gameModel);
				FileWriter userFile = new FileWriter("UserInfo.json", true); // �Ը�д�ķ�ʽд���ļ�
				BufferedWriter writeLine = new BufferedWriter(userFile);
				writeLine.write(json);
				writeLine.newLine();
				writeLine.close();
			}
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 清空文件内容
	 * @param fileName:
	 * @return void
	 * @date 2021/10/28 16:27
	 * @author tou
	 */
	public static void clearInfoForFile(String fileName) {
		File file =new File(fileName);
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter =new FileWriter(file);
			fileWriter.write("");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
