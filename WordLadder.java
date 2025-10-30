import java.util.*;

public class WordLadder {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) return level;

                for (String neighbor : getNeighbors(word)) {
                    if (wordSet.contains(neighbor)) {
                        queue.add(neighbor);
                        wordSet.remove(neighbor);
                    }
                }
            }
            level++;
        }

        return 0;
    }

    private static List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char oldChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == oldChar) continue;
                chars[i] = c;
                neighbors.add(new String(chars));
            }
            chars[i] = oldChar;
        }
        return neighbors;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the begin word: ");
        String beginWord = sc.nextLine();

        System.out.print("Enter the end word: ");
        String endWord = sc.nextLine();

        System.out.print("Enter the number of words in the dictionary: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        List<String> wordList = new ArrayList<>();
        System.out.println("Enter the words in the dictionary:");
        for (int i = 0; i < n; i++) {
            wordList.add(sc.nextLine());
        }

        int result = ladderLength(beginWord, endWord, wordList);
        if (result == 0)
            System.out.println("No possible transformation.");
        else
            System.out.println("Shortest transformation length: " + result);
    }
}
