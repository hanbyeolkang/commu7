import java.util.LinkedList;
import java.util.Queue;

public class test3_2_2 {
    public static void main(String[] args) {
        int n = 8;
        int c = 3;
        int k = 2;
        int[][] contact = {
            {1, 4}, {2, 4}
            ,{3, 4}, {2, 6}
            ,{4, 6}, {4, 5}
            ,{6, 5}, {6, 7}
            ,{5, 7}, {7, 8}
        };
        Solution15 s = new Solution15();
        System.out.println(s.solution(n, c, k, contact));
    }
}

class Solution15 {
    class Citizen {
        int num;
        int receivedCnt = 0;
        boolean isBad = false;
        boolean sendComplete = false;
        LinkedList<Integer> contactList = new LinkedList<>();

        public Citizen (int num, boolean isBad) {
            this.num = num;
            this.isBad = isBad;
        }
        void addContact(int cont) {
            this.contactList.add(cont);
        }
        boolean isSendStatus(int k) {
            if (this.isBad)
                return true;
            if (this.receivedCnt >= k && !sendComplete)
                return true;
            return false;
        }
    }

    public int solution(int n, int c, int k, int[][] contact) {
        Queue<Integer> queue = new LinkedList<>();
        Citizen[] citizens = new Citizen[n + 1];
        citizens[0] = null;
        for (int num = 1; num <= c; num++) {
            queue.add(num);
            citizens[num] = new Citizen(num, true);
        }
        for (int num = c + 1; num <= n; num++) {
            citizens[num] = new Citizen(num, false);
        }
        for (int i = 0; i < contact.length; i++) {
            citizens[contact[i][0]].addContact(contact[i][1]);
        }

        while (!queue.isEmpty()) {
            int num = queue.poll();
            if (citizens[num].isSendStatus(k)) {
                LinkedList<Integer> contactList = citizens[num].contactList;
                for (int reveiver : contactList) {
                    citizens[reveiver].receivedCnt += 1;
                    queue.add(reveiver);
                }
                citizens[num].sendComplete = true;
            }
        }

        int answer = 0;
        for (int num = c + 1; num <= n; num++) {
            if (citizens[num].receivedCnt == 0) {
                answer += 1;
            }
        }
        return answer;
    }
}