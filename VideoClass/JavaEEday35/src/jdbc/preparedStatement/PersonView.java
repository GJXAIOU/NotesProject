package jdbc.preparedStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author GJXAIOU
 * @create 2019-08-02-9:39
 */
public class PersonView {
    public static void main(String[] args) {
        PersonDao personDao = new PersonDao();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("1. 添加数据");
            System.out.println("2. 删除数据");
            System.out.println("3. 更新数据");
            System.out.println("4. 查询所有数据");
            System.out.println("5. 查看指定数据");
            System.out.println("6. 退出");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$");

            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("请输入姓名");
                    String name = scanner.next();
                    System.out.println("请输入性别");
                    String gender = scanner.next();
                    System.out.println("请输入分数");
                    int score = scanner.nextInt();
                    System.out.println("请输入家乡：江苏、上海、杭州");
                    String home = scanner.next();
                    System.out.println("请输入爱好（多选）：游泳、打球、跑步");
                    String hobby = scanner.next();

                    Person person = new Person();
                    person.setName(name);
                    person.setGender(gender);
                    person.setScore(score);
                    person.setHome(home);
                    person.setHobby(hobby);

                    personDao.addTest(person);
                    break;
                case 2:
                    System.out.println("请输入要删除的ID号");
                    int idDelete = scanner.nextInt();
                    personDao.deleteTest(idDelete);
                    break;
                case 3:
                    Person person1 = new Person();
                    System.out.println("请输入要修改人员的ID号：");
                    int idUpdate = scanner.nextInt();
                    person1.setId(idUpdate);
                    System.out.println("请输入修改后的姓名：");
                    String nameUpdate = scanner.next();
                    person1.setName(nameUpdate);
                    System.out.println("请输入修改后的性别：");
                    String genderUpdate = scanner.next();
                    person1.setGender(genderUpdate);
                    personDao.updateTest(person1);
                    break;
                case 4:
                    System.out.println("person数据表中所有数据为：");
                    for (Person person2 : personDao.selectTest()) {
                        System.out.println(person2.toString());
                    }
                    break;
                case 5:
                    System.out.println("请输入要查询人员的ID号：");
                    int idSelect = scanner.nextInt();
                    System.out.println(personDao.selectByIdTest(idSelect).toString());

                    break;
                case 6:
                    System.out.println("退出程序");
                    System.exit(0);
                    break;

                default:
                    break;
            }
        }

    }
}
