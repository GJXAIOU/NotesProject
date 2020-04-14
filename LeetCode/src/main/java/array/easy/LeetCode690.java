package array.easy;

import java.util.HashMap;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/04/14 16:50
 */
public class LeetCode690 {

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    // 首先使用 HashMap 存放 id 以及完整信息
    HashMap<Integer, Employee> employeeMap = null;

    public int getImportance(List<Employee> employees, int id) {
        employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.id, employee);
        }
        return dfs(id);
    }

    public int dfs(int id) {
        Employee employee = employeeMap.get(id);
        int ans = employee.importance;
        // 遍历每一个直接后继
        for (Integer subordinate : employee.subordinates) {
            ans += dfs(subordinate);
        }
        return ans;
    }
}
