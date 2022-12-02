import java.util.*;

public class test4_2 {
    public static void main(String[] args) {
        String[] s1 = {"A", "E", "B", "D", "B", "H", "F", "H", "C"};
        String[] s2 = {"G", "C", "G", "F", "J", "E", "B", "F", "B"};
        String k = "B";
        Solution21 s = new Solution21();
        s.solution(s1, s2, k);
    }
}

class Solution21 {
    class Subject {
        String name;
        int depth = Integer.MAX_VALUE;
        List<String> preSubjects = new LinkedList<>();

        Subject(String name) {
            this.name = name;
        }
        void setDepth(int depth) {
            this.depth = Math.min(this.depth, depth);
        }
        void addPreSubject(String preName) {
            this.preSubjects.add(preName);
        }
        void deletePreSubject(String preName) {
            if (this.preSubjects.contains(preName)) {
                this.preSubjects.remove(preName);
            }
        }
    }

    class SubjectMap {
        Map<String, Subject> map = new HashMap<>();

        void setSubject(String name) {
            if (!map.containsKey(name)) {
                map.put(name, new Subject(name));
            }
        }
        void setDepth(String name, int depth) {
            map.get(name).setDepth(depth);
        }
        List<String> getPreSubjects(String name) {
            return map.get(name).preSubjects;
        }
        void addPreSubject(String name, String preName) {
            setSubject(name);
            setSubject(preName);
            map.get(name).preSubjects.add(preName);
        }
        void deleteNotLinkedSubject() {
            LinkedList<String> list = new LinkedList<>();
            for (Subject subject : map.values()) {
                if (subject.depth == Integer.MAX_VALUE) {
                    list.add(subject.name);
                }
            }
            for (String name : list) {
                map.remove(name);
            }
        }
        void deleteSubject(String name) {
            for (Subject subject : map.values()) {
                subject.deletePreSubject(name);
            }
            map.remove(name);
        }
    }
    
    SubjectMap graph;
    Queue<String> queue;
    public String[] solution(String[] s1, String[] s2, String k) {
        graph = new SubjectMap();
        queue = new PriorityQueue<>();

        for (int i = 0; i < s1.length; i++) {
            graph.addPreSubject(s2[i], s1[i]);
        }
        
        setDepth(k, 0);
        graph.deleteNotLinkedSubject();
        
        List<String> answer = new ArrayList<>();
        while (!queue.isEmpty()) {
            String name = queue.poll();
            answer.add(name);
            graph.deleteSubject(name);
            for (Subject subject : graph.map.values()) {
                if (subject.preSubjects.size() == 0 && !queue.contains(subject.name)) {
                    queue.add(subject.name);
                }
            }
        }
        return answer.toArray(String[]::new);
    }

    public void setDepth(String name, int depth) {
        graph.setDepth(name, depth);
        List<String> preList = graph.getPreSubjects(name);

        if (preList.isEmpty()) {
            if (!queue.contains(name)) {
                queue.add(name);
            }
        }
        else {
            for (String preName : preList) {
                setDepth(preName, depth + 1);
            }
        }
    }
}