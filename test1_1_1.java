
public class test1_1_1 {
    public static void main(String[] args) {
        int n = 11;
        int[] stations = {4, 11};
        int w = 1;

        Solution1 s = new Solution1();
        int answer = s.solution(n, stations, w);
        System.out.println(answer);
    }
}

class Solution1 {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int position = 1;
        int len = 0;
        for (int s : stations) {
            len = s - w - position;
            if (len > 0) {
                answer += len % (2*w + 1) == 0 ? len / (2*w + 1) : len / (2*w + 1) + 1;
            }
            position = s + w + 1;
        }

        if (position <= n) {
            len = n - position + 1;
            answer += len % (2*w + 1) == 0 ? len / (2*w + 1) : len / (2*w + 1) + 1;
        }

        return answer;
    }
}