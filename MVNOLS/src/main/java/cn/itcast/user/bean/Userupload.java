package cn.itcast.user.bean;

public class Userupload {
    private Integer id;

    private Integer userId;

    private String uploadfile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUploadfile() {
        return uploadfile;
    }

    public void setUploadfile(String uploadfile) {
        this.uploadfile = uploadfile == null ? null : uploadfile.trim();
    }
}