package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import top.itreatment.net.bean.*;
import top.itreatment.net.core.ChooseSeatClient;
import top.itreatment.net.core.impl.ChooseSeatClientImpl;
import top.itreatment.net.res.Resource;
import top.itreatment.net.util.Util;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
//        ChooseSeatClient chooseSeatClient = new ChooseSeatClientImpl();
//        chooseSeatClient.login("201626702039", "zjb123456");
//        CommonBean<List<CategoryBean>> areaList = chooseSeatClient.getAreaList();
//        String id = areaList.getData().get(0).getId();
//
//        String id = "35";
//        CommonBean<CategoryInfoBean> categoryInfo = chooseSeatClient.getCategoryInfo(id);
//        long date = categoryInfo.getData().getDate();
//        long time = Util.getTime(date * 1000, categoryInfo.getData().getBeginTime(), 0, 0);
//        CommonBean<CategorySeatsInfoBean> categorySeatsInfoBeanCommonBean = chooseSeatClient.searchSeats((int) (time / 1000), 3 * 60 * 60, 4, id);
//        System.out.println(Util.toJson(categorySeatsInfoBeanCommonBean));
//        chooseSeatClient.unlockallseats();
//        String id1 = categorySeatsInfoBeanCommonBean.getData().getSeats().get(0).getId();
//        CommonBean<String> integerCommonBean = chooseSeatClient.lockSeats((int) (time / 1000), 3 * 60 * 60,id1);
//
//        System.out.println(Util.toJson(integerCommonBean));
//
//        if (Resource.SUCCESS.equals(integerCommonBean.getStatus())) {
//
//            try {
//                Thread.sleep(1000 * 20);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            CommonBean<List<MyBookingBean>> listCommonBean = chooseSeatClient.myBookingList();
//            MyBookingBean myBookingBean = listCommonBean.getData().get(0);
//
//            System.out.println(Util.toJson(myBookingBean));
//
//            CommonBean<SeatBean> cancelbooking = chooseSeatClient.cancelbooking(myBookingBean.getId());
//
//            System.out.println(Util.toJson(cancelbooking));
//        }
//        System.out.println(Util.toJson(categoryInfo));
//        CommonBean<Object> logout = chooseSeatClient.logout();
//
//        System.out.println(Util.toJson(logout));
        launch(args);
    }
}
