package gameModule.controller;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import gameModule.model.GameModel;


/**
 * ���а���
 * ���û������ļ��ж�ȡ�����û����ݣ�����ĳһ���Խ�������
 */
public class HandleDataList {

	public static final String USERNAME_ROOT = "root";
	private static final int LEVEL_FREE_NUM = 999;

	private static List<GameModel> mUserList = new ArrayList<GameModel>(); // �û��б�


	/**
	 * ��ȡ Json �ļ��е�����
	 * @return java.util.List<gameModule.model.GameModel>
	 * @date 2021/10/28 14:34
	 */
	private static List<GameModel> fileRead() {

		try {
			File file = new File("UserInfo.json");
			Reader in = new FileReader(file); // �ײ���
			BufferedReader userRead = new BufferedReader(in); // �ϲ���
			String json = null;
			GameModel readUser = null;
			ObjectMapper mapper = new ObjectMapper();
			while ((json = userRead.readLine()) != null) { // ���϶�ȡ�û�����
				readUser = mapper.readValue(json, GameModel.class); // ��ȡ�����û�
				if (readUser.getTotalRight() != 0) {
					readUser.setNewAccuracy((double) readUser.getNewRight() / readUser.getNewType());
					readUser.setAccuracy((double) readUser.getTotalRight() / readUser.getTotalType());
				}
				mUserList.add(readUser); // ����ȡ�����û���ӵ��û��б�
			}
			userRead.close(); // �ر��ϲ���
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sortList(mUserList);

	}

	/**
	 * ���� Json �ļ��е�����
	 * ���˳���½���˳���Ϸǰ�����û����������ݴ����û��ļ�
	 * @param currentUser:
	 * @date 2021/10/28 15:15
	 */
	public static void saveCurrentUserData(GameModel currentUser) {

		//
		if (currentUser.getRightCount() != 0){
			currentUser.setNewRight(currentUser.getRightCount());
			currentUser.setNewType(currentUser.getTypeCount());
		}
		currentUser.setNewAccuracy((double)currentUser.getNewRight()/currentUser.getNewType());
		currentUser.setTotalRight(currentUser.getTotalRight()+currentUser.getRightCount());
		currentUser.setTotalType(currentUser.getTotalType()+currentUser.getTypeCount());
		currentUser.setAccuracy((double)currentUser.getTotalRight()/currentUser.getTotalType());
		// ���� Jackson �ĺ��Ķ��� ObjectMapper
		ObjectMapper mapper=new ObjectMapper();
		List<String> users = new ArrayList<String>(); // �����û�

		try {
			String nowJson=mapper.writeValueAsString(currentUser); // ��ǰ�û���Ӧ�� Json �ַ���
			String readJson=null; // ��ȡ�����û���Ӧ�� Json �ַ���
			GameModel readUser=null; // ��ȡ�����û�
			File file=new File("UserInfo.json");
			Reader in=new FileReader(file); // �ײ���
			BufferedReader userRead=new BufferedReader(in); // �ϲ���
			while((readJson=userRead.readLine()) != null) { // ���϶�ȡ�û���Ӧ�� Json �ַ���
				// �����û�����
				readUser=mapper.readValue(readJson, GameModel.class);
				if(currentUser.getUsername().equals(readUser.getUsername())) {
					users.add(nowJson); // ��ȡ����ǰ�û���ԭʼ���ݣ��������ݼ����û��б�
				}
				else {
					users.add(readJson);
				}
			}
			userRead.close();

			Writer out=new FileWriter(file, false);
			BufferedWriter writeLine=new BufferedWriter(out); // �ϲ���
			Iterator<String> it = users.iterator();
			while(it.hasNext()) { // ���������ݺ���û��б�����д���ļ�
				writeLine.write(it.next());
				writeLine.newLine();
			}
			writeLine.close();

		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ȷ������
	 * @param userList:
	 * @return void
	 * @date 2021/10/28 14:52
	 * @author tou
	 *
	 */
	public static List<GameModel> sortList(List<GameModel> userList) {
		// ���û��б���ȷ�ʽ�������
		Collections.sort(userList, new Comparator<>() { // ʹ��Comparator�Ƚ����ӿ�
			@Override
			public int compare(GameModel user1, GameModel user2) {
				return ((Double) user2.getAccuracy()).compareTo(user1.getAccuracy()); // ����������
			}
		});
		return userList;
	}

	/**
	 * ��ȡ�û������б�
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
	 * ɾ��ĳ�û���������
	 * @param currentUser :
	 * @param targetUser :
	 * @return boolean ����ɾ������ɹ����
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
	 * �򿪹ؿ�Ȩ��
	 * @param currentUser:
	 * @param targetUser :
	 * @return boolean ���������Ƿ�ɹ�
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
	 * ��д�û������ļ�
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
	 * ����ļ�����
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
