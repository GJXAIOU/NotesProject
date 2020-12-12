package chapter3;
import	java.util.Comparator;
import	java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author GJXAIOU
 * @create 2019-08-18-16:21
 */
public class SortForList {
    public static void main(String[] args) {
        // 方法一：实体类中实现compareTo方法，然后调用Collections.sort();方法
        // 这里使用的实体类是之前已经实现compareTo方法的student类
        Student student1 = new Student(1);
        Student student2 = new Student(2);

        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        Collections.sort(students);

        // 方法二：不需要在实体类中定义compareTo方法，以及实现compareable接口
        // 直接在这里定义Collections.sort方法
        Collections.sort(students, new Comparator<Student> () {
            @Override
            public int compare(Student student1, Student student2) {
                if (student1.getId() == student2.getId()){
                    return 0;
                }else {
                    return student1.getId() > student2.getId() ? 1 : -1;
                }
            }
        });

        // 通过迭代器遍历ArrayList集合
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getId());
        }
    }
}


