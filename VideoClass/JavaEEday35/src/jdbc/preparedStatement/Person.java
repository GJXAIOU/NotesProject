package jdbc.preparedStatement;

/**
 * @author GJXAIOU
 * @create 2019-08-01-20:09
 */
public class Person {
    private Integer id;
    private String name;
    private String gender;
    private Integer score;
    private String home;
    private String hobby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + this.getId()+
                ", name='" + this.getName() + '\'' +
                ", gender='" + this.getGender() + '\'' +
                ", score=" + this.getScore() +
                ", home='" + this.getHome() + '\'' +
                ", hobby='" + this.getHobby() + '\'' +
                '}';
    }
}
