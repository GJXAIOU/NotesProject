package pojo;

/** 将提交的数据封装为一个对象，插入数据库
 * @author GJXAIOU
 * @create 2019-08-14-19:11
 */

public class IdInfo {
   private Integer id;
   private String front;
   private String back;
   private String idnumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    @Override
    public String toString() {
        return "IdInfo{" +
                "id=" + id +
                ", front='" + front + '\'' +
                ", back='" + back + '\'' +
                ", idnumber='" + idnumber + '\'' +
                '}';
    }
}
