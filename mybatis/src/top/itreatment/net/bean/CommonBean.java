package top.itreatment.net.bean;

public class CommonBean<T> {

    private Integer status;
    private String msg;
    private T data;


    public CommonBean() {
    }

    public CommonBean(Integer status) {
        this.status = status;
    }

    public CommonBean(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public CommonBean(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
