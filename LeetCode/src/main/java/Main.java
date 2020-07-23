
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class Node {
        public int i;
        public int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Node> house = new ArrayList<Node>();
        List<Node> shopee = new ArrayList<Node>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = scanner.nextInt();
                if (value == 1) {
                    house.add(new Node(i, j));
                } else {
                    shopee.add(new Node(i, j));
                }
            }
        }
        if (shopee.size() == 0) {
            System.out.println(-1);
            return;
        }
        int res = Integer.MAX_VALUE;
        for (Node node : shopee) {
            int sum = 0;
            for (Node node2 : house) {
                sum += Math.abs(node.i - node2.i) + Math.abs(node.j - node2.j);
            }
            res = Math.min(res, sum);
        }
        System.out.println(res);
    }

}