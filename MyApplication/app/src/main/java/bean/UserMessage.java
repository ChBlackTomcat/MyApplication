package bean;

import java.io.Serializable;

/**
 * Created by 小黑 on 2019/8/4.
 */

public class UserMessage implements Serializable {
    private int id;
    private String account;
    private String password;
    private String province;
    private String username;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserMessage(int id,String account, String password, String province, String username) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.province = province;
        this.username = username;
    }

    public UserMessage(String account, String password, String province, String username) {
        this.account = account;
        this.password = password;
        this.province = province;
        this.username = username;
    }
}
