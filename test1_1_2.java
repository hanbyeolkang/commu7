
public class test1_1_2 {
    public static void main(String[] args) {
        int[][] cost = {{0, 10}, {50, 20}, {100, 30}, {200, 40}};
        int[][] order = {{3, 50}, {7, 200}, {8, 200}};

        Solution2 s = new Solution2();
        System.out.println(s.solution(cost, order));
    }
}

class Solution2 {
    public int solution(int[][] cost, int[][] order) {
        int answer = 0;

        int maxMonth = 0;
        for (int[] o : order) {
            maxMonth = Math.max(maxMonth, o[0]);
        }

        int needCnt = 0;
        int[] monthOrder = new int[maxMonth];
        for (int[] o : order) {
            needCnt += o[1];
            monthOrder[o[0] - 1] += o[1];
        }
        
        int saveCnt = 0;
        for (int i = 0; i < cost.length - 1; i++) {
            if (needCnt == 0)
                break;

            int limitCnt = cost[i + 1][0] - cost[i][0];
            int fee = cost[i][1];
            int restCnt = 0;

            for (int j = 0; j < maxMonth; j++) {
                if (needCnt == 0 || needCnt <= saveCnt)
                    break;

                int cnt = Math.min(needCnt - saveCnt, limitCnt);
                answer += cnt * fee;
                saveCnt += cnt;
                needCnt -= monthOrder[j];
            
                if (monthOrder[j] != 0) {
                    int deliverCnt = Math.min(saveCnt, monthOrder[j]);
                    saveCnt -= deliverCnt;
                    monthOrder[j] -= deliverCnt;
                    restCnt += monthOrder[j];
                }
            }

            needCnt = restCnt;
            saveCnt = 0;
        }

        answer += needCnt * cost[cost.length - 1][1];

        return answer;
    }
}
