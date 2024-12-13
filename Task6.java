import java.util.*;

public class Task6 {
    public static void main(String[] args) {
        System.out.println("1. " + hiddenAnagram("Bright is the moon", "Bongo mirth"));
        System.out.println("2. " + stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[] {"b"}));
        System.out.println("3. " + nicoCipher("iloveher", "612345"));
        System.out.println("4. " );
        System.out.println("5. " );
        System.out.println("6. " + fractions("0.19(2367)"));
        System.out.println("7. " + pilish_string("CANIMAKEAGUESSNOW"));
        System.out.println("8. " + formula("16 * 10 = 160 = 14 + 120"));
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

    public static String stripUrlParams(String url, String[] toBeDeleted) {
        if (url.indexOf('?') == -1) return url;
        String afterQuestionMark = url.substring(url.indexOf('?') + 1);
        String[] keysAndValues = afterQuestionMark.split("&");
        HashMap<String, String> params = new HashMap<>();
        for (String pair : keysAndValues) {
            String[] keyAndValue = pair.split("=");
            params.put(keyAndValue[0], keyAndValue[1]);
        }
        for (String param : toBeDeleted) {
            if (params.containsKey(param)) params.remove(param);
        }
        StringBuilder updatedUrl = new StringBuilder();
        updatedUrl.append(url.substring(0, url.indexOf('?') + 1));
        for (Map.Entry<String, String> pair : params.entrySet()) {
            updatedUrl.append(pair.getKey() + "=" + pair.getValue() + "&");
        }
        if (updatedUrl.charAt(updatedUrl.length() - 1) == '&') updatedUrl.deleteCharAt(updatedUrl.length() - 1);
        if (updatedUrl.charAt(updatedUrl.length() - 1) == '?') updatedUrl.deleteCharAt(updatedUrl.length() - 1);
        return updatedUrl.toString();
    }

    public static String nicoCipher(String message, String key) {
        int height = message.length() / key.length();
        if (message.length() % key.length() != 0) height += 1;
        char[][] matrix = new char[height][key.length()];
        for (int i = 0; i < message.length(); i++) {
            matrix[i / key.length()][i % key.length()] = message.charAt(i);
        }
        char[][] sortedMatrix = new char[height][key.length()];
        ArrayList<Integer> charsOfKey = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            charsOfKey.add((int)key.charAt(i));
        }
        Collections.sort(charsOfKey);
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < height; j++) {
                sortedMatrix[j][i] = matrix[j][key.indexOf(charsOfKey.get(i))];
            }
            key = key.replaceFirst((char)(int)charsOfKey.get(i) + "", "+");
        }
        String eMessage = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < key.length(); j++) {
                if ((int)sortedMatrix[i][j] == 0) eMessage += " ";
                else eMessage += sortedMatrix[i][j];
            }
        }
        return eMessage.toString();
    }

    // public static int[] isExact(int n) {
        
    // }

    public static String fractions(String fraction) {
        int pointIndex = fraction.indexOf('.');
        int leftBracketIndex = fraction.indexOf('(');
        int rightBracketIndex = fraction.indexOf(')');
        int intPart = Integer.parseInt(fraction.substring(0, pointIndex));
        int period = Integer.parseInt(fraction.substring(leftBracketIndex + 1, rightBracketIndex));
        int numerator;
        int denominator;
        if (pointIndex + 1 == leftBracketIndex) {
            StringBuilder nines = new StringBuilder();
            for (int i = 0; i < rightBracketIndex - leftBracketIndex - 1; i++) {
                nines.append(9);
            }
            denominator = Integer.parseInt(nines.toString());
            numerator = intPart * denominator + period;
        }
        else {
            int nonPediod = Integer.parseInt(fraction.substring(pointIndex + 1, leftBracketIndex));
            StringBuilder ninesAndZeros = new StringBuilder();
            int nonPediodMultiplier = 1;
            for (int i = 0; i < rightBracketIndex - leftBracketIndex - 1; i++) {
                ninesAndZeros.append(9);
                nonPediodMultiplier *= 10;
            }
            for (int i = 0; i < leftBracketIndex - pointIndex - 1; i++) {
                ninesAndZeros.append(0);
            }
            denominator = Integer.parseInt(ninesAndZeros.toString());
            numerator = intPart * denominator + nonPediod * nonPediodMultiplier + period - nonPediod;
        }
        for (int i = numerator; i > 1; i--) {
            if (numerator % i == 0 && denominator % i == 0) {
                numerator /= i;
                denominator /= i;
                break;
            }
        }
        return numerator + "/" + denominator;
    }

    public static String pilish_string(String input) {
        String output = "";
        if ("".equals(input)) return output;
        int[] pi = new int[] {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9};
        for (int i = 0; i < pi.length; i++) {
            if (input.length() == 0) break;
            String word;
            if (input.length() >= pi[i]) {
                word = input.substring(0, pi[i]);
                input = input.substring(pi[i]);
            }
            else {
                word = input;
                char lastChar = word.charAt(word.length() - 1);
                while (word.length() != pi[i]) {
                    word = word += lastChar;
                }
                output += word;
                break;
            }
            output += word + " ";
        }
        return output.trim();
    }

    public static boolean formula(String s) {
        String[] parts = s.split("=");
        HashSet<Float> results = new HashSet<>();
        for (String string : parts) {
            String spaceless = string.replaceAll(" ", "");
            if (spaceless.indexOf('*') != -1) {
                int a = Integer.parseInt(spaceless.substring(0, spaceless.indexOf('*')));
                int b = Integer.parseInt(spaceless.substring(spaceless.indexOf('*') + 1));
                results.add((float)a * b);
                continue;
            }
            if (spaceless.indexOf('/') != -1) {
                int a = Integer.parseInt(spaceless.substring(0, spaceless.indexOf('/')));
                int b = Integer.parseInt(spaceless.substring(spaceless.indexOf('/') + 1));
                results.add((float)a / b);
                continue;
            }
            if (spaceless.indexOf('+') != -1) {
                int a = Integer.parseInt(spaceless.substring(0, spaceless.indexOf('+')));
                int b = Integer.parseInt(spaceless.substring(spaceless.indexOf('+') + 1));
                results.add((float)a + b);
                continue;
            }
            if (spaceless.indexOf('-') != -1) {
                int a = Integer.parseInt(spaceless.substring(0, spaceless.indexOf('-')));
                int b = Integer.parseInt(spaceless.substring(spaceless.indexOf('-') + 1));
                results.add((float)a - b);
                continue;
            }
            results.add(Float.parseFloat(spaceless));
        }
        return results.size() == 1;
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
