package gjxaiou;

/**
 * @author GJXAIOU
 * @create 2019-08-16-16:54
 */
public class City {
    private Integer value;
    private String text;

    public City(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
