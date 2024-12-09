import java.util.*;

public class Task6 {
    public static void main(String[] args) {
        System.out.println("1. " + hiddenAnagram("Bright is the moon", "Bongo mirth"));
        System.out.println("2. " );
        System.out.println("3. " );
        System.out.println("4. " );
        System.out.println("5. " );
        System.out.println("6. " );
        System.out.println("7. " );
        System.out.println("8. " + formula("6 * 4 = 24"));
        System.out.println("9. " + isValid("abcdefghhgfedecba"));
        System.out.println("10. " + palindromeDescendant(23336014));
    }

    public static String hiddenAnagram(String first, String second) {
        StringBuilder firstSB = new StringBuilder();
        StringBuilder secondSB = new StringBuilder();
        for (int i = 0; i < first.length(); i++) {
            if (i < second.length()) {
                char c = second.charAt(i);
                if (65 <= c && c <= 90 || 97 <= c && c <= 122) secondSB.append(Character.toLowerCase(c)); 
            }
            char c = first.charAt(i);
            if (65 <= c && c <= 90 || 97 <= c && c <= 122) firstSB.append(Character.toLowerCase(c)); 
        }
        for (int i = 0; i <= firstSB.length() - secondSB.length(); i++) {
            StringBuilder subFirstSB = new StringBuilder(firstSB.substring(i, i + secondSB.length()));
            String anagram = subFirstSB.toString();
            boolean isAnagram = true;
            for (int j = 0; j < secondSB.length(); j++) {
                char c = secondSB.charAt(j);
                int index = subFirstSB.indexOf("" + c);
                if (index == -1) {
                    isAnagram = false;
                    break;
                }
                else subFirstSB.deleteCharAt(index);
            }
            if (isAnagram) return anagram;
        }
        return "noutfond";
    }

    public static boolean formula(String s) {
        String[] parts = s.split("=");
        for (String string : parts) {
            string.replaceAll(" ", "");
            System.out.println(string);
        }
        return false;
    }

    public static String isValid(String s) {
        HashMap<Character, Integer> letterCount = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (letterCount.containsKey(c)) letterCount.put(c, letterCount.get(c) + 1);
            else letterCount.put(c, 1);
        }
        ArrayList<Integer> counts = new ArrayList<>(letterCount.values());
        HashMap<Integer, Integer> letterCountCount = new HashMap<>();
        for (Integer i : counts) {
            if (letterCountCount.containsKey(i)) letterCountCount.put(i, letterCountCount.get(i) + 1);
            else letterCountCount.put(i, 1);
        }
        ArrayList<Map.Entry<Integer, Integer>> arrayList = new ArrayList<>(letterCountCount.entrySet());
        if (arrayList.size() == 1) return "YES";
        if (arrayList.size() == 2) {
            if (arrayList.get(0).getValue() == 1 && arrayList.get(0).getKey() - arrayList.get(1).getKey() == 1) return "YES";
            if (arrayList.get(0).getValue() == 1 && arrayList.get(0).getKey() == 1 || arrayList.get(1).getValue() == 1 && arrayList.get(1).getKey() == 1) return "YES";
            if (arrayList.get(1).getValue() == 1 && arrayList.get(1).getKey() - arrayList.get(0).getKey() == 1) return "YES";
        }
        return "NO";
    }

    public static boolean palindromeDescendant(int n) {
        String nString = Integer.toString(n);
        do {
            boolean isPalindrome = true;
            for (int i = 0; i < nString.length() / 2; i++) {
                if (nString.charAt(i) != nString.charAt(nString.length() - 1 - i)) {
                    isPalindrome = false;
                    break;
                }
            }
            if (isPalindrome) return true;
            else {
                StringBuilder newN = new StringBuilder();
                for (int i = 0; i < nString.length(); i += 2) {
                    newN.append(Character.getNumericValue(nString.charAt(i)) + Character.getNumericValue(nString.charAt(i + 1)));
                }
                nString = newN.toString();
            }
        }
        while (nString.length() >= 2);
        return false;
    }
}
