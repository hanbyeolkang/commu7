import java.time.LocalTime;

public class test2_1 {
    public static void main(String[] args) {
        String[][] booked = {{"09:55", "hae"}, {"10:05", "jae"}};
        String[][] unbooked = {{"10:04", "hee"}, {"14:07","eom"}};

        Solution10 s = new Solution10();
        s.solution(booked, unbooked);
    }
}

class Solution10 {
    public String[] solution(String[][] booked, String[][] unbooked) {
        int aIdx = 0;
        int bIdx = 0;
        int uIdx = 0;
        LocalTime time = LocalTime.of(0, 1);
        String[] answer = new String[booked.length + unbooked.length];

        while (bIdx < booked.length || uIdx < unbooked.length) {
            LocalTime bTime = null;
            LocalTime uTime = null;
            
            if (bIdx < booked.length)
                bTime = LocalTime.parse(booked[bIdx][0] + ":00");
            if (uIdx < unbooked.length)
                uTime = LocalTime.parse(unbooked[uIdx][0] + ":00");

            if (uTime == null
                || (bTime != null
                    && ((bTime.equals(time) || bTime.isBefore(time))
                        || (bTime.equals(uTime) || bTime.isBefore(uTime))
                    )
                )
            ) {
                answer[aIdx] = booked[bIdx][1];
                aIdx++;
                bIdx++;
                time = bTime.isBefore(time) ? time.plusMinutes(10) : bTime.plusMinutes(10);
            }
            else {
                answer[aIdx] = unbooked[uIdx][1];
                aIdx++;
                uIdx++;
                time = uTime.isBefore(time) ? time.plusMinutes(10) : uTime.plusMinutes(10);
            }
        }

        return answer;
    }
}