package loginModule.utils;

import java.awt.*;
/**
 * �����������ȡ��ǰ���Ե�һЩ��Ϣ
 *
 */
public class ScreenUtils {

    /**
     * ��ȡ��ǰ������Ļ�Ŀ��
     * @return ��Ļ���
     */
    public static int getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    /**
     * ��ȡ��ǰ������Ļ�ĸ߶�
     * @return ��Ļ�߶�
     */
    public static int getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}