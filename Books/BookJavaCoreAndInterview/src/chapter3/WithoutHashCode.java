package chapter3;

import java.util.HashMap;

/**
 * @author GJXAIOU
 * @create 2019-08-17-18:23
 */
class KeyValue{
    // 重写的hashCode中的成员变量以及get方法返回值☆☆☆必须使用包装类
    public Integer id;

    public KeyValue(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof KeyValue)){
            return false;
        }else {
            return this.getId().equals(((KeyValue) o).getId());
        }
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
    


public class WithoutHashCode {
    public static void main(String[] args) {
        KeyValue keyValue1 = new KeyValue(1);
        KeyValue keyValue2 = new KeyValue(1);
        HashMap<KeyValue, String> keyValueStringHashMap = new HashMap<>();
        keyValueStringHashMap.put(keyValue1, "id is 1");
        System.out.println(keyValueStringHashMap.get(keyValue2));
    }
}// 返回值为：null
