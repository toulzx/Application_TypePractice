package Start;

import gameModule.controller.HandleDataList;
import gameModule.view.RankListView;
import loginModule.view.LoginView;
import loginModule.view.RegisterView;

/**
 * @author lzx
 */
public class StartApplication {

    //��½��������
    public static void main(String[] args) {
        try {
            new LoginView().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
