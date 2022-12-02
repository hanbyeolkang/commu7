
public class test1_2_2 {
    public static void main(String[] args) {
        // int storey = 16;
        int storey = 2554;
        Solution4 s = new Solution4();
        System.out.println(s.solution(storey));
    }
}

class Solution4 {
    public int solution(int storey) {
        int answer = elevator(storey);
        return answer;
    }


    public int elevator(int storey) {
        if (storey <= 1)
            return storey;

        int under10 = storey % 10;
        int rest = storey / 10;

        int up = (10 - under10) + elevator(rest + 1);
        int down = under10 + elevator(rest);
        
        return Math.min(up, down);
    }
}