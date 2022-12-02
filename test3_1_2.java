import java.util.HashMap;

public class test3_1_2 {
    public static void main(String[] args) {
        int[] bell = {1, 2, 1, 1, 1, 2, 2, 1};
        // int[] bell = {2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1};
        Solution13 s = new Solution13();
        System.out.println(s.solution(bell));
    }
}

class Solution13 {
    public int solution(int[] bell) {
        for (int i = 0; i < bell.length; i++) {
            if (bell[i] == 2) {
                bell[i] = -1;
            }
        }

        int answer = 0;
        int sum = bell[0];

        // key: 0 부터 idx 까지의 sum
        // value: 새로운 sum 값이 나온 idx
        HashMap<Integer, Integer> dict = new HashMap<>();
        dict.put(0, -1); // for 문을 도는 중 sum 값이 0 이 되는 경우가 있을 수 있으므로
        dict.put(sum, 0);

        for (int i = 1; i < bell.length; i++) {
            sum += bell[i];
            if (!dict.containsKey(sum)) {
                dict.put(sum, i);
            }
            else {
                // 0 부터 x 까지의 합이 a 이고,
                // 0 부터 y 까지의 합이 a 이라면 (x < y)
                // x 부터 y 까지의 합이 0 이 된다.
                answer = Math.max(answer, i - dict.get(sum)); // 길이
            }
        }

        return answer;
    }
}