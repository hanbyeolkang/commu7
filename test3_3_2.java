import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class test3_3_2 {
    public static void main(String[] args) {
        int[][] reply = {
            {0}, {8}, {1, 3}, {2}, {1}, {1, 4, 6}, {2, 5}, {3, 6}, {4}
        };
        Solution17 s = new Solution17();
        System.out.println(s.solution(reply));
    }
}

class Member {
    int visit = -1; // -1 이 아닌것으로 visit 여부 확인
    int groupNumber = -1; // -1 이 아닌 것으로 그룹 확정 확인
}

class Solution17 {
    Stack<Integer> stack;
    int visitDepth;
    int groupCount;

    public int solution(int[][] reply) {
        // 변수들 초기화
        visitDepth = 0;
        groupCount = 0;
        stack = new Stack<>();

        Member[] members = new Member[reply.length];
        List<List<Integer>> followers = new ArrayList<>();
        for (int i = 0; i < members.length; i++) {
            members[i] = new Member();
            followers.add(new ArrayList<>());
        }

        // 회장(0)은 제외 하고 graph 생성
        for (int i = 1; i < reply.length; i++) {
            for (int f : reply[i]) {
                followers.get(f).add(i);
            }
        }

        for (int i = 1; i < reply.length; i++) {
            if (members[i].groupNumber == -1) {
                // 그룹이 결정 되지 않은 멤버들 그룹 짓기
                groupingDFS(members, followers, i);
            }
        }

        boolean[] skip = new boolean[groupCount];
        for (int i = 1; i < followers.size(); i++) {
            for (int f : followers.get(i)) {
                if (members[i].groupNumber != members[f].groupNumber) {
                    // 같은 그룹이 아닌데 그래프 연결점이 있는 경우 skip한다.
                    skip[members[f].groupNumber] = true;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < groupCount; i++) {
            if (!skip[i]) {
                answer++; // skip되지 않은 그룹들만 카운트
            }
        }
        return answer;
    }


    private int groupingDFS(Member[] members, List<List<Integer>> followers, int num) {
        int ret = visitDepth;
        members[num].visit = visitDepth;
        stack.push(num);
        visitDepth += 1;

        for (int f : followers.get(num)) {
            if (members[f].visit == -1) {
                // 방문한 적 없는 팔로워 방문
                // 여러 그룹과 연결된 경우 첫번째 그룹을 선택
                ret = Math.min(ret, groupingDFS(members, followers, f));
            }
            if (members[f].groupNumber == -1) {
                // 더 먼저 방문했던 쪽으로 결정
                ret = Math.min(ret, members[f].visit);
            }
        }

        // 순환루프를 찾은 경우
        if (ret == members[num].visit) {
            while (true) {
                int now = stack.pop();
                // 순환하는 동아리원들에게 같은 그룹값(groupCount)을 갖도록 함
                members[now].groupNumber = groupCount;
                if (now == num) break;
            }
            // 순환그룹 개수 증가
            groupCount++;
        }

        return ret;
    }
}