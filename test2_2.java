import java.util.Arrays;

public class test2_2 {
    public static void main(String[] args) {
        int[] people = {2, 3};
        int[] tshirts = {1,2,3};
        Solution11 s = new Solution11();
        System.out.println(s.solution(people, tshirts));
    }
}

class Solution11 {
    public int solution(int[] people, int[] tshirts) {
        int answer = 0;

        Arrays.sort(people);
        Arrays.sort(tshirts);

        int tIdx = 0;
        for (int p : people) {
            int j = tIdx;
            for (; j < tshirts.length; j++) {
                if (p <= tshirts[j]) {
                    tIdx = j + 1;
                    answer += 1;
                    break;
                }
            }
            
            if (j == tshirts.length - 1)
                break;
        }

        return answer;
    }
}