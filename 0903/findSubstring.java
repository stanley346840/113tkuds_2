import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) return res;

        // 記錄 words 出現次數
        Map<String, Integer> wordMap = new HashMap<>();
        for (String w : words) {
            wordMap.put(w, wordMap.getOrDefault(w, 0) + 1);
        }

        // 對每個 offset (0 到 wordLen-1) 做 sliding window
        for (int i = 0; i < wordLen; i++) {
            int left = i, right = i;
            Map<String, Integer> window = new HashMap<>();
            int count = 0;

            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordMap.containsKey(word)) {
                    window.put(word, window.getOrDefault(word, 0) + 1);
                    count++;

                    // 超過需求就移動左指標縮小
                    while (window.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        window.put(leftWord, window.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // 剛好符合
                    if (count == wordCount) {
                        res.add(left);
                    }
                } else {
                    // 無效 word，重置
                    window.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return res;
    }
}
