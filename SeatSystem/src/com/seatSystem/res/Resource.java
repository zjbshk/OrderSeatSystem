package com.seatSystem.res;

import com.seatSystem.annotation.Value;

public class Resource {

    @Value("退出系统")
    public static final String EXIT = "exit";
    public static final String BYE = "Bye";
    public static final String TASK = "task";


    @Value("创建一个任务")
    public static final String CREATE = "create";
    @Value("显示一个任务详细的用户信息")
    public static final String SHOW = "show";
    @Value("删除一个任务")
    public static final String REMOVE = "remove";
    @Value("将任务配置文件导入")
    public static final String IMPORT = "import";
    @Value("将任务配置文件导出")
    public static final String EXPORT = "export";
    @Value("列出任务列表")
    public static final String LIST = "list";
    @Value("设置任务属性")
    public static final String SET = "set";
    @Value("启动一个任务")
    public static final String START = "start";
    @Value("运行一个任务")
    public static final String RUN = "run";
    @Value("帮助")
    public static final String HELP = "help";


}
