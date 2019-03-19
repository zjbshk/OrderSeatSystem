package com.test;

import top.itreatment.net.bean.CategoryBean;
import top.itreatment.net.bean.CommonBean;
import top.itreatment.net.bean.UserBean;
import top.itreatment.net.core.ChooseSeatClient;
import top.itreatment.net.core.impl.ChooseSeatClientImpl;
import top.itreatment.net.util.Util;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        ChooseSeatClient chooseSeatClient = new ChooseSeatClientImpl();
        CommonBean<UserBean> zjb123456 = chooseSeatClient.login("201626702039", "zjb123456");

        CommonBean<List<CategoryBean>> areaList = chooseSeatClient.getAreaList();
        System.out.println(Util.toJson(areaList));

    }

//    private static void create(CategoryBean categoryBean, ChooseSeatClient chooseSeatClient) {
//        String contentId = categoryBean.getId();
//        String name = categoryBean.getName();
//        CommonBean<List<SeatBean>> blankPOIs = chooseSeatClient.getBlankPOIs(contentId);
//        List<SeatBean> data = blankPOIs.getData();
//        data.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getY())));
//        System.out.println(Util.toJson(data));
//        chooseSeatClient.unlockallseats();
//
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet(name);
//        for (SeatBean datum : data) {
//            String y = datum.getY();
//            String x = datum.getX();
//            int rowIndex = Integer.parseInt(y) / 2;
//            int columnIndex = Integer.parseInt(x) / 2;
//            HSSFRow row = sheet.getRow(rowIndex);
//            if (row == null) {
//                row = sheet.createRow(rowIndex);
//            }
//            HSSFCell cell = row.getCell(columnIndex);
//            if (cell == null) {
//                cell = row.createCell(columnIndex);
//            }
//            cell.setCellValue(Integer.parseInt(datum.getTitle()));
//        }
//
//        try {
//            workbook.write(new File("C:\\Users\\Administrator\\Desktop\\" + contentId + "_" + name + ".xls"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
