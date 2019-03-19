package top.itreatment.net.bean;


/**
 * 用来盛放获取自习室列表数据
 */
public class CategoryBean {

    private String id; //自习室id
    private boolean disabled; //标识自习室是否可用
    private String name;    //自习室名称
    private String image;   //自习室图片url
    private String space;   //自习室座位数
    private String desc;    //自习室描述
    private String url;     //对应的页面url

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CategoryBean{" +
                "id='" + id + '\'' +
                ", disabled=" + disabled +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", space='" + space + '\'' +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
