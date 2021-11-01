package gameModule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import loginModule.model.Register;

/**
 * ��Ϸģ��,ע��ģ�͵����ࡣ
 * ÿ��ע��һ�����û��������Ը��ݸ��û�ģ�͵õ�һ���µ���Ϸģ�ͣ��˺���û���������Ϸ��Ϊ�����ڴ�ģ�ͻ����Ϸ�����
 * @see loginModule.model.Register
 *
 */
public class GameModel extends Register{

	// �ų����ԣ�������Ϸģ��ʱ���ó�Ա��������ӵ�json
	@JsonIgnore // �Ѷ�
	private int difficulty=5;
	@JsonIgnore// �������ʷ������ȷ��
	private double accuracy=0;
	@JsonIgnore // ������Ϸ���ָ���
	private long typeCount=0;
	@JsonIgnore// ������Ϸ������ȷ��
	private long rightCount=0;
	// ������ȷ������
	private double newAccuracy =0;


	/**
	 * ���췽�����ø����Ĭ�Ϲ��췽�����õ�һ����Ϸģ��ʵ����������ԭ�����Ծ�ΪĬ��ֵ��
	 * ���ڴ˻����������ˡ��Ѷȡ����ԣ������û��Ѿ�ͨ�ص���Ŀ������Ĭ���Ѷȡ�
	 * @see loginModule.model.Register#Register()
	 */
	public GameModel(){
		super();
		// �����û��Ѿ�ͨ�ص���Ŀ������Ĭ���Ѷ�
		switch(passNum) {
		case 0:
			difficulty=5;
			break;
		case 1:
			difficulty=10;
			break;
		case 3:
			difficulty=15;
			break;
		default:
			throw new IllegalStateException("Unexpected value: " + passNum);
		}
	}
	
	public void setDifficulty(int d) {
		difficulty=d;
	}
	public int getDifficulty() {
		return difficulty;
	}

	public double getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	
	public long getTypeCount() {
		return typeCount;
	}
	public void setTypeCount(long typeCount) {
		this.typeCount = typeCount;
	}

	public long getRightCount() {
		return rightCount;
	}
	public void setRightCount(long rightCount) {
		this.rightCount = rightCount;
	}

	public double getNewAccuracy() {
			return newAccuracy;
		}
	public void setNewAccuracy(double newAccuracy) {
		this.newAccuracy = newAccuracy;
	}

	
}
