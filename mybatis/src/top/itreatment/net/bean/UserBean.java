package top.itreatment.net.bean;

import java.util.Map;

public class UserBean {




    private String id;
    private String is_enabled;
    private String category_id;
    private String gender;
    private String student_number;
    private String name;
    private String nickname;
    private String mobile;
    private String avatar;
    private String accessToken;
    private String objectId;
    private String lab_content_org_id;

    public void setIs_enabled(String is_enabled) {
        this.is_enabled = is_enabled;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setLab_content_org_id(String lab_content_org_id) {
        this.lab_content_org_id = lab_content_org_id;
    }

    public String getIs_enabled() {
        return is_enabled;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getGender() {
        return gender;
    }

    public String getStudent_number() {
        return student_number;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getId() {
        return id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getLab_content_org_id() {
        return lab_content_org_id;
    }


    @Override
    public String toString() {
        return "UserBean{" +
                "id='" + id + '\'' +
                ", is_enabled='" + is_enabled + '\'' +
                ", category_id='" + category_id + '\'' +
                ", gender='" + gender + '\'' +
                ", student_number='" + student_number + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", avatar='" + avatar + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", objectId='" + objectId + '\'' +
                ", lab_content_org_id='" + lab_content_org_id + '\'' +
                '}';
    }
}
