public class test4_1 {
    public static void main(String[] args) {
        int rows = 3;
        int columns = 4;
        int max_virus = 2;
        int[][] queries = {
            {3,2}
            ,{3,2}
            ,{2,2}
            ,{3,2}
            ,{1,4}
            ,{3,2}
            ,{2,3}
            ,{3,1}
        };
        Solution20 s = new Solution20();
        s.solution(rows, columns, max_virus, queries);
    }
}

class Solution20 {
    int[][] answer;
    boolean[][] visit;
    public int[][] solution(int rows, int columns, int max_virus, int[][] queries) {
        answer = new int[rows][columns];
        visit = new boolean[rows][columns];
        for (int[] q : queries) {
            virus(q[0] - 1, q[1] - 1, max_virus);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    visit[i][j] = false;
                }
            }
        }
        return answer;
    }

    public void virus(int i, int j, int max_virus) {
        visit[i][j] = true;
        if (answer[i][j] < max_virus) {
            answer[i][j] += 1;
        }
        else {
            if (i > 0 && !visit[i - 1][j])
                virus(i - 1, j, max_virus);
            if (i < answer.length - 1 && !visit[i + 1][j])
                virus(i + 1, j, max_virus);
            if (j > 0 && !visit[i][j - 1])
                virus(i, j - 1, max_virus);
            if (j < answer[0].length - 1 && !visit[i][j + 1])
                virus(i, j + 1, max_virus);
        }
    }
}