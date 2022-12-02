import java.util.Arrays;
import java.util.stream.IntStream;

public class test1_4_2 {
    public static void main(String[] args) {
        int[] coin = {1,0,1};
        int k = 3;

        Solution8 s = new Solution8();
        System.out.println(s.solution(coin, k));
    }
}

class Solution8 {
    public int solution(int[] coin, int k) {
        if (coin.length == 1)
            return 0;
        
        int sum = IntStream.of(coin).sum();
        if (sum == 0 || sum == coin.length)
            return 0;
        else if (coin.length == k)
            return -1;
        
        int cnt0 = changeCoin(Arrays.copyOf(coin, coin.length), k, 0);
        int cnt1 = changeCoin(Arrays.copyOf(coin, coin.length), k, 1);

        int answer = Math.min(cnt0, cnt1);
        if (answer == Integer.MAX_VALUE)
            answer = -1;

        return answer;
    }
    
    public int changeCoin(int[] coin, int k, int target) {
        int cnt = 0;
        for (int i = 0; i <= coin.length - k; i++) {
            if (coin[i] != target) {
                cnt++;
                for (int j = 0; j < k; j++) {
                    coin[i + j] = 1 - coin[i + j];
                }
            }
        }

        int targetCnt = 0;
        for (int c : coin) {
            if (c == target)
                targetCnt++;
        }

        if (coin.length != targetCnt)
            cnt = Integer.MAX_VALUE;
        
        return cnt;
    }

    // int[] coins;
    // public int solution(int[] coin, int k) {
    //     int answer = 0;

    //     HashSet<String> set = new HashSet<>();
    //     String sBack = "";
    //     String sFront = "";
    //     coins = new int[coin.length];

    //     for (int i = 0; i < coin.length; i++) {
    //         sBack += "0";
    //         sFront += "1";
    //         coins[i] = coin[i];
    //     }

    //     while (true) {
    //         String s = "";
    //         for (int c : coins) {
    //             s += c;
    //         }

    //         if (s.equals(sBack) || s.equals(sFront)) {
    //             break;
    //         }

    //         if (set.contains(s)) {
    //             answer = -1;
    //             break;
    //         }
    //         else {
    //             set.add(s);
    //         }

    //         int idx = getIdx(k);
    //         if (idx != -1) {
    //             changeCoin(idx, k);
    //             answer++;
    //         }
    //     }

    //     return answer;
    // }

    // public int getIdx(int k) {
    //     int idx = -1;

    //     for (int i = 0; i < coins.length - 1; i++) {
    //         if (coins[i] != coins[i+1]) {
    //             idx = i + 1;
    //             break;
    //         }
    //     }

    //     if (coins.length - k < idx) {
    //         idx = -1;
    //     }

    //     return idx;
    // }

    // public void changeCoin(int idx, int k) {
    //     for (int i = 0; i < coins.length; i++) {
    //         if (idx <= i && i < idx + k) {
    //             coins[i] = coins[i] == 0 ? 1 : 0;
    //         }
    //     }
    // }
}