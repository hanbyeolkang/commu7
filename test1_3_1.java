import java.util.Arrays;

public class test1_3_1 {
    public static void main(String[] args) {
        int[] d = {1,3,2,5,4};
        int budget = 9;

        Solution5 s = new Solution5();
        s.solution(d, budget);
    }
}

class Solution5 {
    public int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);
        for (int amt : d) {
            if (amt <= budget) {
                budget -= amt;
                answer++;
            }
            else {
                break;
            }
        }
        return answer;
    }
}

/*
    int[] budgets;
    int max = IntStream.of(budgets).max().orElse(0);

    final int mid = (min + max) / 2;
    int sum = IntStream.of(budgets)
        .map(b -> Math.min(b, mid))
        .sum();
 */