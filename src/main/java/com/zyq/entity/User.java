package com.zyq.entity;

/**
 * @author zyq
 * @date 2018/7/20 14:55
 */
public class User {
    /*表单上的name的属性和这上面的属性应该是一样的*/
    private String username;
    private String password;

    public User(){

    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
