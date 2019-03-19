# OrderSeat
----
该库名称是CSC.jar,位置在OrderSeat\SeatSystem\lib目录下

#### 核心类
```java
	//接口ChooseSeatClient
		|——实现类ChooseSeatClientImpl
	
	
	/**
     * 登录,以下所有方法必须在该方法执行后调用
     * 传入用户名和密码
     * 成功返回：{
     *     status:200
     *     data:UserBean用户信息
     * }
     *  失败返回：{
     *      status:100//或是其他数字
     *      msg: 失败的原因，密码错误等
     *  }
     */
	default CommonBean<UserBean> login(String username, String password) {
        return null;
    }

	/**
	 * 注销
	 * 成功返回：{
	 *     status:200
	 * }
	 *  失败返回：{
	 *      status:100//或是其他数字
	 *      msg: 失败的原因，密码错误等
	 *  }
	 */
    default CommonBean<Object> logout() {
        return null;
    }

	/**
	 * 获取自习室列表
	 * 成功返回：{
	 *     status:200
	 *     data:List<CategoryBean>，CategoryBean包含自习室id，楼栋，方位和座位数等
	 * }
	 *  失败返回：{
	 *      status:100//或是其他数字
	 *      msg: 失败的原因，密码错误等
	 *  }
	 */
    default CommonBean<List<CategoryBean>> getAreaList() {
        return null;
    }
	
	/**
	 * 获取预约的记录列表
	 */
    default CommonBean<List<MyBookingBean>> myBookingList() {
        return null;
    }

	/**
     * 获取这个时间，对应id的自习室的限制，时长限制，开始时间，人数等
     *
     * @param contentId 获取的自习室实例的ID
     * @return CommonBean<CategoryInfoBean>实例
     */
    default CommonBean<CategoryInfoBean> getCategoryInfo(String contentId) {
        return null;
    }

	 /**
     * 搜索座位，获取所有座位信息
     *
     * @param beginTime 开始时间，时间戳/1000精确到秒
     * @param duration  时长，以小时为单位
     * @param num       获取推荐座位的数量
     * @param contentId 自习室对应id
     * @return CommonBean<CategorySeatsInfoBean>，包含所有座位信息，推荐座位，和如果是多个推荐座位，他们的间距
     */
    default CommonBean<CategorySeatsInfoBean> searchSeats(Integer beginTime, Integer duration, Integer num, String contentId) {
        return null;
    }
	
	/**
     * 选定某个座位
     *
     * @param beginTime 开始时间，时间戳/1000精确到秒
     * @param duration  时长，以小时为单位
     * @param seats     传入多个座位id，有序的选座，失败就用下一个id选座，所有id都选座失败，或是有一个成功方法结束
     * @return CommonBean<String> 包含是否成功
     */
    default CommonBean<String> lockSeats(Integer beginTime, Integer duration, String... seats) {
        return null;
    }
	
	/**
     * 解除推荐座位锁定，当执行searchSeats方法时，服务器会将推荐座位进行锁定，其他人无法选择的状态，该方法即可解除
     *
     * @return CommonBean 是否解除成功
     */
    default CommonBean unlockallseats() {
        return null;
    }
	
	 /**
     * 取消预选座位
     *
     * @param bookingId 传入预选的预定id
     * @return 是否取消成功
     */
    default CommonBean cancelbooking(Integer bookingId) {
        return null;
    }

	/**
     * 获取指定自习室的座位情况
     *
     * @param contentld 自习室id
     * @return CommonBean<List<SeatBean>>实例
     */
    default CommonBean<List<SeatBean>> getBlankPOIs(String contentld) {
        return null;
    }

	/**
     * 获取指定自习室的推荐座位
     *
     * @param contentld 自习室id
     * @return CommonBean<List<SeatBean>>实例
     */
    default CommonBean<List<SeatBean>> getRecommendSeat(String contentld) {
        return null;
    }
	
```


#### 下面是一个使用示例
```java
	package com.test;

	import top.itreatment.net.bean.*;
	import top.itreatment.net.core.ChooseSeatClient;
	import top.itreatment.net.core.impl.ChooseSeatClientImpl;
	import top.itreatment.net.res.Resource;
	import top.itreatment.net.util.Util;

	import java.util.List;

	public class Main {

    public static void main(String[] args) {
        //初始化客户端
        ChooseSeatClient chooseSeatClient = new ChooseSeatClientImpl();
        //登陆
        chooseSeatClient.login("xxxxxx", "xxxxxx");

        //获取自习室列表,默认有4个
        CommonBean<List<CategoryBean>> areaList = chooseSeatClient.getAreaList();

        //取第一个自习室的id
        String id = areaList.getData().get(0).getId();

        //获取这个自习室在此刻，允许的开始时间，持续的时长和人数
        CommonBean<CategoryInfoBean> categoryInfo = chooseSeatClient.getCategoryInfo(id);

        //获取允许的最早时间戳
        long date = categoryInfo.getData().getDate();

        //将时间戳转换成整点时间
        long time = Util.getTime(date * 1000, categoryInfo.getData().getBeginTime(), 0, 0);

        //获取自习室符合要求的座位，和所有座位情况信息等
        CommonBean<CategorySeatsInfoBean> categorySeatsInfoBeanCommonBean = chooseSeatClient.searchSeats((int) (time / 1000), 3 * 60 * 60, 1, id);

        //获取符合要求的第一个座位的id
        String id1 = categorySeatsInfoBeanCommonBean.getData().getSeats().get(0).getId();

        //选择该座位，设置时长为3小时
        CommonBean<String> integerCommonBean = chooseSeatClient.lockSeats((int) (time / 1000), 3 * 60 * 60, id1);

        System.out.println(Util.toJson(integerCommonBean));

        if (Resource.SUCCESS.equals(integerCommonBean.getStatus())) {
            try {
                Thread.sleep(1000 * 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //获取预定的座位信息列表
            CommonBean<List<MyBookingBean>> listCommonBean = chooseSeatClient.myBookingList();
            MyBookingBean myBookingBean = listCommonBean.getData().get(0);

            System.out.println(Util.toJson(myBookingBean));

            //取消所选预定的座位
            CommonBean<SeatBean> cancelbooking = chooseSeatClient.cancelbooking(myBookingBean.getId());

            System.out.println(Util.toJson(cancelbooking));
        }
        System.out.println(Util.toJson(categoryInfo));

        //注销登陆
        CommonBean<Object> logout = chooseSeatClient.logout();

        System.out.println(Util.toJson(logout));
		}
	}

```


#### 通过该库api和poi库生成excel表格
```java
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
			CommonBean<UserBean> xxx = chooseSeatClient.login("xxxxxx", "xxxxxx");

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

```
