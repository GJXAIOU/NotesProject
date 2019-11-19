package pojo;

import java.util.StringTokenizer;

/**
 * @author GJXAIOU
 * @create 2019-08-23-9:52
 */
public class Users {
    private int id;
    private String username;
    private String password;
    private int sex;
    private int age;
    private String tel;
    private String address;
    private int isSupper;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsSupper() {
        return isSupper;
    }

    public void setIsSupper(int isSupper) {
        this.isSupper = isSupper;
    }
}
