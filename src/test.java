import java.util.*;

public class test {

    static TreeMap<String, Integer> map1More = new TreeMap<>();
    static TreeMap<String, Integer> map2More = new TreeMap<>();
    static TreeMap<String, Integer> mapEquals = new TreeMap<>();
    static int max = 0;

    public static String mix(String s1, String s2) {

        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();

        addAllLettersCount(map1, s1);
        addAllLettersCount(map2, s2);

        sortToHashMaps(map1, map2);

        defineMax(map1More);
        defineMax(map2More);
        defineMax(mapEquals);

        String result = createResultString();

        map1More = new TreeMap<>();
        map2More = new TreeMap<>();
        mapEquals = new TreeMap<>();
        max = 0;

        return result;
    }

    public static String createResultString() {

        String result = "";

        for (int i = max; i > 1; i--) {

            result = addValuesToString(map1More, "1", i, result);
            result = addValuesToString(map2More, "2", i, result);
            result = addValuesToString(mapEquals, "=", i, result);
        }
        return result;
    }

    public static String addValuesToString(TreeMap<String, Integer> treeMap, String symbol, int i, String result) {

        for (String key : treeMap.keySet()) {
            if (treeMap.get(key) == i) {
                result += result.length() > 0 ? "/" : "";
                result += String.format("%s:%s", symbol, key.repeat(i));
            }
        }
        return result;
    }

    public static void addAllLettersCount(Map<String, Integer> map, String s) {

        String[] array = s.split("");

        for (String string : array) {
            char ch = string.charAt(0);

            if (Character.isLowerCase(ch)) {
                int count = 0;

                for (String value : array) {

                    if (string.equals(value))
                        count++;

                }

                if (count > 1) map.put(string, count);
            }
        }
    }

    public static void sortToHashMaps(Map<String, Integer> map1, Map<String, Integer> map2) {

        map1More.putAll(map1);
        sortToHashMaps(map2);

    }

    public static void sortToHashMaps(Map<String, Integer> map2) {

        for (String key : map2.keySet()) {

            if (map1More.get(key) == null)
                map2More.put(key, map2.get(key));

            else {
                if (map2.get(key) > map1More.get(key)) {
                    map1More.remove(key);
                    map2More.put(key, map2.get(key));
                }

                else if (map2.get(key).equals(map1More.get(key))) {
                    map1More.remove(key);
                    mapEquals.put(key, map2.get(key));
                }
            }
        }
    }

    public static void defineMax(TreeMap<String, Integer> treeMap) {
        for (String key : treeMap.keySet()) {
            max = Math.max(treeMap.get(key), max);
        }
    }


}




