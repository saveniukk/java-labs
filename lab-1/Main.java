import java.util.Arrays;

//5. Знайти слова, які складаються тільки з різних символів. На виході – масив String.
public class Main {
    public static String[] findWords (String words) {
        String[] arrayOfWords =  words.split(" ");

        String[] resultArray = new String[arrayOfWords.length];
        int index = 0;

        for (String word : arrayOfWords) {
            if (hasAllUniqueChars(word)) {
                resultArray[index++] = word;
            }
        }
        resultArray = Arrays.copyOf(resultArray, index);
        return resultArray;
    }

    private static boolean hasAllUniqueChars(String word) {
        for (int j = 0; j < word.length(); j++) {
            for (int k = j + 1; k < word.length(); k++) {
                if (word.charAt(j) == word.charAt(k)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String words;

        if (args.length > 0) words = args[0];
        else words = "aaaa abc abb something";

        String[] result = findWords(words);
        System.out.println(Arrays.toString(result));
    }
}
