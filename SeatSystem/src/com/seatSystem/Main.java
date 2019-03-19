package com.seatSystem;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seatSystem.annotation.Value;
import com.seatSystem.bean.Task;
import com.seatSystem.res.Resource;
import com.seatSystem.util.Log;
import com.seatSystem.util.Util;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    private static List<Task> tasks = new ArrayList<>();
    private static Integer taskNum = 1;
    private static ScheduledExecutorService service;

    public static void main(String[] args) {
        welcome();
        runThread();
        start();
    }

    private static void welcome() {
        Log.out("\t\t欢迎使用，自习室选座系统\n");
    }

    private static void runThread() {
        service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(TaskSchedule.getInstance(tasks), 5, 1, TimeUnit.SECONDS);
    }

    private static void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(">");
            String line = scanner.nextLine().trim();
            String[] args = line.split("\\s+");
            if (args.length == 0) continue;

            String command = args[0];
            if (Resource.EXIT.equalsIgnoreCase(command)) {
                Log.out(Resource.BYE);
                service.shutdown();
                break;
            } else if (Resource.CREATE.equalsIgnoreCase(command)) {
                create(args);
            } else if (Resource.HELP.equalsIgnoreCase(command)) {
                help(args);
            } else if (Resource.SET.equalsIgnoreCase(command)) {
                set(args);
            } else if (Resource.START.equalsIgnoreCase(command)) {
                _start(args);
            } else if (Resource.RUN.equalsIgnoreCase(command)) {
                run(args);
            } else if (Resource.LIST.equalsIgnoreCase(command)) {
                list(args);
            } else if (Resource.IMPORT.equalsIgnoreCase(command)) {
                _import(args);
            } else if (Resource.REMOVE.equalsIgnoreCase(command)) {
                remove(args);
            } else if (Resource.SHOW.equalsIgnoreCase(command)) {
                show(args);
            } else if (Resource.EXPORT.equalsIgnoreCase(command)) {
                export(args);
            } else {
                Log.eOut("命令语句没有找到");
            }
        }
        scanner.close();
    }


    private static void help(String[] args) {
        Field[] declaredFields = Resource.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Value annotation = declaredField.getAnnotation(Value.class);
            String name = declaredField.getName();
            if (annotation != null) {
                String s = name.toLowerCase();
                String format = String.format("%-10s\t\t%s", s, annotation.value());
                System.out.println(format);
            }
        }
    }


    private static void run(String[] args) {
        tryChange(args, "run");
    }

    private static void _start(String[] args) {
        tryChange(args, "start");
    }

    private static void remove(String[] args) {
        tryChange(args, "remove");
    }

    private static void show(String[] args) {
        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                Log.out(task.getId() + "." + task.getName() + "\t\t用户:" + task.getUserSeats());
            }
        } else {
            Log.eOut("没有任务");
        }
    }

    private static void tryChange(String[] args, String tag) {
        if (args.length == 2) {
            String idsJoin = args[1];
            if (idsJoin.contains(",")) {
                String[] ids = idsJoin.split(",");
                for (int i = 0; i < ids.length; i++) {
                    String id = ids[i].trim();
                    _tryChange(id, tag);
                }
            } else {
                _tryChange(idsJoin, tag);
            }
        } else {
            Log.out("参数数量有误");
        }
    }

    private static void _tryChange(String id, String tag) {
        if (id.matches("\\d+")) {
            Task taskById = getTaskById(Integer.parseInt(id));
            if (taskById != null) {
                if (tag.equals("run")) {
                    taskById.setState(Task.State.PREPARE);
                    long l = System.currentTimeMillis() + 2000;
                    taskById.setTime(new Date(l));
                } else if (tag.equals("start")) {
                    taskById.setState(Task.State.PREPARE);
                } else if (tag.equals("remove")) {
                    tasks.remove(taskById);
                }
            } else {
                Log.eOut("任务id=" + id + "未找到");
            }
        } else {
            Log.eOut("id=" + id + "格式有误");
        }
    }

    private static Task getTaskById(int parseInt) {
        for (Task task : tasks) {
            if (task.getId() == parseInt) {
                return task;
            }
        }
        return null;
    }

    private static void set(String[] args) {
        if (args.length > 2) {
            String id = args[1];
            if (id.matches("\\d+")) {
                Task taskTemp = null;
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    if (task.getId() == Integer.parseInt(id)) {
                        taskTemp = task;
                    }
                }
                if (taskTemp != null) {
                    String[] strings = new String[args.length - 2];
                    Util.subArray(args, 2, strings);
                    Task task = new Task();
                    JCommander commander = JCommander.newBuilder().addObject(task).build();
                    try {
                        commander.parse(strings);
                    } catch (ParameterException e) {
                        Log.eOut(e.getMessage());
                        return;
                    }
                    Field[] declaredFields = task.getClass().getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        declaredField.setAccessible(true);
                        try {
                            Object o = declaredField.get(task);
                            if (o != null) {
                                declaredField.set(taskTemp, o);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Log.eOut("未找到id=" + id + "的任务");
                }
            } else {
                Log.eOut("id格式不正确，只能是数字");
            }
        } else {
            Log.eOut("请指明要修改任务的id和要修改的信息");
        }
    }

    private static void _import(String[] args) {
        if (args.length > 1) {
            File file = new File(args[1]);
            if (file.exists()) {
                Gson gson = new Gson();
                try {
                    List<Task> tasksTemp = gson.fromJson(new FileReader(file), new TypeToken<List<Task>>() {
                    }.getType());
                    if (tasksTemp != null && !tasksTemp.isEmpty()) {
                        for (Task task : tasksTemp) {
                            task.setId(taskNum++);
                        }
                        tasks.addAll(tasksTemp);
                        Log.out("任务添加" + tasksTemp.size() + "个,可以使用list命令查看");
                    } else {
                        Log.out("没有任务添加");
                    }
                } catch (Exception e) {
                    Log.eOut("任务文件解析异常:" + e.getMessage());
                }
            } else {
                Log.eOut("文件不存在");
            }
        } else {
            Log.eOut("请输入文件完整路径");
        }
    }


    private static void list(String[] args) {
        Log.out("=================任务列表=================");
        int tag = -1;
        StringBuilder sb = new StringBuilder();
        sb.append(Util.formatStrWidth("序号", 3, tag));
        sb.append(Util.formatStrWidth("名称", 13, 0));
        sb.append(Util.formatStrWidth("时间", 23, 0));
        sb.append(Util.formatStrWidth("状态", 13, tag));
        sb.append(Util.formatStrWidth("自习室id", 7, tag));
        sb.append(Util.formatStrWidth("开始", 4, tag));
        sb.append(Util.formatStrWidth("时长", 4, tag));
        sb.append(Util.formatStrWidth("用户", 10, tag));
        Log.out(sb.toString());
        for (Task task : tasks) {
            System.out.println(task);
        }
        Log.out("==========================================");
    }

    private static void create(String[] args) {
        if (args.length > 1 && Resource.TASK.equalsIgnoreCase(args[1])) {
            String[] strings = new String[args.length - 2];
            Util.subArray(args, 2, strings);
            Task task = new Task();
            JCommander commander = JCommander.newBuilder().addObject(task).build();
            try {
                commander.parse(strings);
            } catch (ParameterException e) {
                Log.eOut(e.getMessage());
                commander.usage();
                return;
            }
            task.setId(taskNum++);
            tasks.add(task);
        } else {
            Log.eOut("请指定创建的类型");
        }
    }

    private static void export(String[] args) {
        String config_json = null;
        if (args.length > 1) {
            if (top.itreatment.net.util.Util.isIn(",", args)) {
                String[] ids = Util.subArray(args, 1, new String[args.length - 1]);
                List<Task> tasksTmep = new ArrayList<>();
                for (Task task : tasks) {
                    if (top.itreatment.net.util.Util.isIn(String.valueOf(task.getId()), ids)) {
                        tasksTmep.add(task);
                    }
                }
                config_json = top.itreatment.net.util.Util.toJson(tasksTmep);
            }
        } else {
            config_json = top.itreatment.net.util.Util.toJson(tasks);
        }
        try {
            OutputStream os = new FileOutputStream("default.json");
            os.write(config_json.getBytes("utf-8"));
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
