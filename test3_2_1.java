import java.util.LinkedList;
import java.util.Queue;

public class test3_2_1 {
    public static void main(String[] args) {
        int[][] maps = {
            {1, 0, 1, 1, 1}
            ,{1, 0, 1, 0, 1}
            ,{1, 0, 1, 1, 1}
            ,{1, 1, 1, 0, 1}
            ,{0, 0, 0, 0, 1}
        };
        Solution14 s = new Solution14();
        System.out.println(s.solution(maps));
    }
}

class Solution14 {
    class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean isValid(int width, int height) {
            if (this.x < 0 || this.x >= height)
                return false;
            if (this.y < 0 || this.y >= width)
                return false;
            return true;
        }
    }

    public int solution(int[][] maps) {
        int width = maps[0].length;
        int height = maps.length;

        boolean[][] visit = new boolean[height][width];
        Queue<Position> queue = new LinkedList<>();

        queue.add(new Position(0, 0));
        visit[0][0] = true;

        while(!queue.isEmpty()) {
            Position current = queue.poll();

            final int[][] move = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
            for (int i = 0; i < move.length; i++) {
                Position p = new Position(current.x + move[i][0], current.y + move[i][1]);
                if (!p.isValid(width, height)) 
                    continue;
                if (maps[p.x][p.y] == 0)
                    continue;
                if (visit[p.x][p.y])
                    continue;

                queue.add(p);
                visit[p.x][p.y] = true;
                maps[p.x][p.y] = maps[current.x][current.y] + 1;
            }
        }

        int answer = maps[height - 1][width - 1];
        if (answer == 1) {
            answer = -1;
        }
        
        return answer;
    }
}