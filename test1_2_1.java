import java.util.Arrays;

public class test1_2_1 {
    public static void main(String[] args) {
        // int[] numbers = {6, 10, 2};
        int[] numbers = {5, 34, 30, 3, 9};

        Solution3 s = new Solution3();
        s.solution(numbers);
    }
}

class Solution3 {
    public String solution(int[] numbers) {
        String answer = "";

        String[] arr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            arr[i] = String.valueOf(numbers[i]);

        Arrays.sort(arr, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));

        for (String s : arr)
            answer += s;

        if (answer.startsWith("0"))
            answer = "0";

        return answer;
    }
}

/*
    String answer = IntStream.of(numbers)
        .mapToObj(String::valueOf(n))
        .sorted((s1, s2) -> (s2+s1).compareTo(s1+s2))
        .collect(Collectors.joining());

    if (answer.startsWith("0"))
        answer = "0";

    return answer;
 */