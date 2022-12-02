import java.util.Arrays;

public class test1_4_1 {
    public static void main(String[] args) {
        int[] A = {5,1,3,7};
        int[] B = {2,2,6,8};

        Solution7 s = new Solution7();
        System.out.println(s.solution(A, B));
    }
}

class Solution7 {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A); // 1,3,5,7
        Arrays.sort(B); // 2,2,6,8

        int idx = B.length - 1;
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] < B[idx]) {
                idx--;
                answer++;
            }
        }
        
        return answer;
    }
}