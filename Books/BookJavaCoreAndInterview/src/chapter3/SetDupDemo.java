package chapter3;
import	java.util.TreeSet;
import	java.util.HashSet;

/**
 * @author GJXAIOU
 * @create 2019-08-17-14:45
 */

// 步一：自定义类遵从Comparable
class Student implements Comparable{
    private int id;

    public Student(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 重写了用于判断两个Student对象是否相等的equals方法， 如果不 重写， 将用Object的方法（这个方法是通过判断两个对象的地址是
     * 否—致来判断两个对象 是否相等的）。
     * TreeSet对象不会根据equa ls方法判断是否重复，也就是说， 即使程序员注释了这个 方法， 也不会影响运行结果， 但在自定义类中
     * ， 重写equals方法是很好的习惯。
     * @param s
     * @return
     */
    public boolean equals(Student s) {
        if (s.getId() == this.id){
            return true;
        }else {
            return false;
        }
    }

    // 步二：实现comparaTo方法
    @Override
    public int compareTo(Object o) {
        // 判断是否是学生类型
        if (o instanceof Student) {
            Student student = (Student) o;
            // 如果是学生类型，如果学号相等则不加入set
            if (this.getId() == student.getId()) {
                return 0;
            }else {
                return student.getId() > this.getId() ? 1 : -1;
            }
            // 不是学生类型对象则不加入
        }else {
            return  0;
        }
    }
}

public class SetDupDemo {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(1);
        System.out.println(hashSet.size());

        Student student1 = new Student(1);
        Student student2 = new Student(1);
        TreeSet<Student> treeSet = new TreeSet<>();
        treeSet.add(student1);
        treeSet.add(student2);
        System.out.println(treeSet.size());
    }
}
