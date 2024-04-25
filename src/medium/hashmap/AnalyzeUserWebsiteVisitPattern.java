package medium.hashmap;

import java.util.*;

/**
 * 1152. Analyze User Website Visit Pattern
 * This problem is focused on data structure using TreeMap (SortedMap)
 * Great problem to get familiar with a new PL (e.g. Rust)
 */
public class AnalyzeUserWebsiteVisitPattern {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        HashMap<String, Map<Integer, String>> overview = new HashMap<>();
        for (int i = 0; i < username.length; i++) {
            //Here the assumption is username.length == timestamp.length == website.length
            overview.computeIfAbsent(username[i], o -> new TreeMap<>()).put(timestamp[i], website[i]);
        }
        HashMap<String, Integer> patternCount = new HashMap<>();
        int maxCount = 0;
        String pattern = "";
        for (Map<Integer, String> m : overview.values()) {
            List<String> sortedArr = new ArrayList<>(m.values());
            HashSet<String> existing = new HashSet<>();
            int s = sortedArr.size();
            if (s < 3) {
                continue;
            }
            for (int i = 0; i < s - 2; i++) {
                for (int j = i + 1; j < s - 1; j++) {
                    for (int k = j + 1; k < s; k++) {
                        StringBuilder sb = new StringBuilder();
                        var p = sb.append(sortedArr.get(i)).append(".").append(sortedArr.get(j)).append(".").append(sortedArr.get(k));
                        var key = p.toString();
                        if (!existing.contains(key)) {
                            patternCount.compute(p.toString(), (patt, v) -> (v == null) ? 1 : v + 1);
                        }
                        existing.add(key);
                        var c = patternCount.get(key);
                        if (maxCount < c || (maxCount == c && key.compareTo(pattern) < 0)) {
                            maxCount = c;
                            pattern = key;
                        }
                    }
                }
            }
        }
        return Arrays.asList(pattern.split("\\."));
    }

    public static void main(String[] args) {
        AnalyzeUserWebsiteVisitPattern a = new AnalyzeUserWebsiteVisitPattern();
        var r = a.mostVisitedPattern(new String[]{"h","eiy","cq","h","cq","txldsscx","cq","txldsscx","h","cq","cq"}, new int[]{527896567,334462937,517687281,134127993,859112386,159548699,51100299,444082139,926837079,317455832,411747930}, new String[]{"hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","yljmntrclw","hibympufi","yljmntrclw"});
        System.out.println(r);
    }
}
