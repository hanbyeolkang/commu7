import java.util.Arrays;

public class test1_3_2 {
    public static void main(String[] args) {
        int goal = 43;
        int[] durations = {5,3,7,6,4};

        Solution6 s = new Solution6();
        System.out.println(s.solution(goal, durations));
    }   
}

class Solution6 {
    public long solution(int goal, int[] durations) {
        Arrays.sort(durations);

        int dMin = durations[0];
        int dMax = durations[durations.length - 1];

        long time = getFinishTime(goal, dMin, dMax, durations);
        long answer = getAmount(time, dMax, durations);

        return answer;
    }

    public long getFinishTime(int goal, int dMin, int dMax, int[] durations) {
        long min = dMin * goal / durations.length;
        long max = dMax * goal / durations.length;

        while (min < max) {
            long mid = (min + max) / 2;

            int cnt = 0;
            for (int d : durations) {
                cnt += mid / d;
            }

            if (cnt < goal)
                min = mid + 1;
            else
                max = mid;
        }

        return max;
    }

    public long getAmount(long time, int dMax, int[] durations) {
        long cnt = 0;
        long minCnt = time / dMax;
        for (int d : durations) {
            cnt += time / d - minCnt;
        }
        return cnt * 10000;
    }
}

// public long solution(int goal, int[] durations) {
//     long answer = 0;

//     Arrays.sort(durations);

//     int dMin = durations[0];
//     int dMax = durations[durations.length - 1];

//     long time = 0;
//     long timeMin = dMin * goal / durations.length;
//     long timeMax = dMax * goal / durations.length;

//     while (timeMin <= timeMax) {
//         long timeMid = (timeMin + timeMax) / 2;

//         int sum = 0;
//         for (int d : durations) {
//             sum += timeMid / d;
//         }

//         if (goal <= sum) {
//             time = timeMid;
//             timeMax = timeMid - 1;
//         }
//         else {
//             timeMin = timeMid + 1;
//         }
//     }

//     long cntMin = time / dMax;
//     for (int d : durations) {
//         answer += (time / d - cntMin) * 10000;
//     }

//     return answer;
// }