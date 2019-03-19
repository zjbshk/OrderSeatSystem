package com.seatSystem;

import com.seatSystem.bean.Task;
import com.seatSystem.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskSchedule implements Runnable {

    List<Task> taskList = null;

    private TaskSchedule() {

    }

    public static TaskSchedule getInstance(List<Task> taskList) {
        TaskSchedule taskSchedule = new TaskSchedule();
        taskSchedule.setTaskList(taskList);
        return taskSchedule;
    }


    @Override
    public void run() {
        if (taskList != null && !taskList.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
            for (Task task : taskList) {
                if (Task.State.PREPARE.equals(task.getState()) && checkTask(task)) {
                    Date time = task.getTime();
                    Date date = new Date();
                    String format = dateFormat.format(date);
                    String timeFormat = dateFormat.format(time);
                    if (format.equals(timeFormat)) {
                        Log.out("\n\t开始执行:" + task.getId() + "\t" + task.getName());
                        task.setState(Task.State.RUNNING);
                        new Thread(new RunTask(task)).start();
                    } else if (time.compareTo(date) == -1) {
                        task.setState(Task.State.BEFORE);
                    }
                }
            }
        }
    }

    private boolean checkTask(Task task) {
        if (task.getUserSeats() == null || task.getUserSeats().isEmpty() || task.getArea() == null) {
            task.setState(Task.State.ERROR);
            return false;
        }
        return true;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
