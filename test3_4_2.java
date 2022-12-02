public class test3_4_2 {
    public static void main(String[] args) {
        String candy = "RYRYRGPRYRYRBB";
        int[] positions = {12, 1, 14, 7};
        // String candy = "BPBRBPBRB";
        // int[] positions = {3, 6, 9};

        Solution19 s = new Solution19();
        int[] answer = s.solution(candy, positions);
        for (int a : answer)
            System.out.println(a);
    }
}

class Solution19 {
    public int[] solution(String candy, int[] positions) {
        int[] answer = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            answer[i] = getCnt(candy.substring(0, positions[i]));
        }
        return answer;
    }

    public int getCnt(String candy) {
        if (candy.length() == 1) {
            return 0;
        }

        int cnt = 0;
        char c1_first = candy.charAt(0);
        char c2_last = candy.charAt(candy.length() - 1);
        String c1 = "";
        String c2 = "";
        for (int i = 0; i < candy.length() - 1; i++) {
            char c1_last = candy.charAt(i);
            char c2_first = candy.charAt(candy.length() - 1 - i);
            c1 = c1 + c1_last;
            c2 = c2_first + c2;
            if (c1_first == c2_first && c1_last == c2_last && c1.equals(c2)) {
                cnt += 1;
            }
        }
        return cnt;
    }
}