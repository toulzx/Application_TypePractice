package loginModule.utils;

import java.awt.*;
/**
 * 这个类用来获取当前电脑的一些信息
 */
public class ScreenUtils {

    /**
     * 获取当前电脑屏幕的宽度
     * @return 屏幕宽度
     */
    public static int getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    /**
     * 获取当前电脑屏幕的高度
     * @return 屏幕高度
     */
    public static int getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}