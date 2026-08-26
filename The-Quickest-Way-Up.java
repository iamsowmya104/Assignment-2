
import java.io.*;
import java.util.*;

class Result {

    public static int quickestWayUp(List<List<Integer>> ladders, List<List<Integer>> snakes) {
        int[] board = new int[101];
        for (int i = 1; i <= 100; i++) {
            board[i] = i;
        }

        for (List<Integer> ladder : ladders) {
            board[ladder.get(0)] = ladder.get(1);
        }

        for (List<Integer> snake : snakes) {
            board[snake.get(0)] = snake.get(1);
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        int[] moves = new int[101];
        Arrays.fill(moves, -1);

        queue.add(1);
        moves[1] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == 100) {
                return moves[current];
            }

            for (int die = 1; die <= 6; die++) {
                int next = current + die;

                if (next <= 100) {
                    int destination = board[next];

                    if (moves[destination] == -1) {
                        moves[destination] = moves[current] + 1;
                        queue.add(destination);
                    }
                }
            }
        }

        return -1;
    }

}

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        
        int t = scanner.nextInt();

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            List<List<Integer>> ladders = new ArrayList<List<Integer>>();

            for (int i = 0; i < n; i++) {
                List<Integer> ladder = new ArrayList<Integer>();
                ladder.add(scanner.nextInt());
                ladder.add(scanner.nextInt());
                ladders.add(ladder);
            }

            int m = scanner.nextInt();
            List<List<Integer>> snakes = new ArrayList<List<Integer>>();

            for (int i = 0; i < m; i++) {
                List<Integer> snake = new ArrayList<Integer>();
                snake.add(scanner.nextInt());
                snake.add(scanner.nextInt());
                snakes.add(snake);
            }

            int result = Result.quickestWayUp(ladders, snakes);
            System.out.println(result);
        }
        
        scanner.close();
    }
}
