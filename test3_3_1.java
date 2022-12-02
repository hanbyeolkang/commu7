public class test3_3_1 {
    public static void main(String[] args) {
        int n = 2;
        Solution16 s = new Solution16();
        System.out.println(s.solution(n));
    }
}

class Solution16 {
    public int solution(int n) {
        int answer = chk(n, n);
        return answer;
    }

    public int chk(int openCnt, int closeCnt) {
        if (openCnt == 0 && closeCnt == 0)
            return 1;
        else if (openCnt < 0 || openCnt > closeCnt)
            return 0;

        int answer = 0;
        answer += chk(openCnt - 1, closeCnt);
        answer += chk(openCnt, closeCnt - 1);
        return answer;
    }
}