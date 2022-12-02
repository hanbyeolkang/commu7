import java.util.HashMap;

public class test3_1_1 {
    public static void main(String[] args) {
        String[][] clothes = {
            {"yellowhat", "headgear"}
            , {"bluesunglasses", "eyewear"}
            , {"green_turban", "headgear"}
        };
        Solution12 s = new Solution12();
        System.out.println(s.solution(clothes));
    }
}

class Solution12 {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<>();
        for (String[] c : clothes) {
            map.put(c[1], map.getOrDefault(c[1], 0) + 1);
        }
        for (Integer v : map.values()) {
            answer *= v + 1;
        }
        answer -= 1;
        return answer;
    }
}