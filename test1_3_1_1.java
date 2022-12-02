
public class test1_3_1_1 {
    public static void main(String[] args) {
        int[] budgets = {120, 110, 140, 150};
        int M = 485;

        Solution9 s = new Solution9();
        System.out.println(s.solution(budgets, M));
    }
}

class Solution9 {
    public int solution(int[] budgets, int M) {
        int answer = 0;
        
        int min = 0;
        int max = 0;
        int sum = 0;
        for (int b : budgets) {
            if (max < b)
                max = b;
            sum += b;
        }
        
        if (sum <= M) {
            answer = max;
        }
        else {
            while (min <= max) {
                int mid = (min + max) / 2;

                sum = 0;
                for (int b : budgets) {
                    sum += mid < b ? mid : b;
                }

                if (sum <= M) {
                    min = mid + 1;
                    answer = mid;
                }
                else {
                    max = mid - 1;
                }
            }
        }
        
        return answer;
    }
}
