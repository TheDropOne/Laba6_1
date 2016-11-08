import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Semen on 08-Nov-16.
 */
public class Runner {
    /*Задание. Родственные слова.

Назовем два слова родственными, если одно может быть получено из другого циклическим сдвигом на несколько позиций.

Например:  камыш – мышка

Во входном файле input.txt находится текст.
Разделители: пробел, точка, запятая, точка с запятой.
Исходный файл может быть пустым.
В файле может быть несколько строк.
Регистр не имеет значения.

В выходной файл output.txt вывести все группы родственных слов (слова одной группы – через пробел в одну строку, слова следующей группы – с новой строки).
Если слово не имеет родственных слов, то оно будет в своей группе одно.

При проходе по коллекции хотя бы один раз использовать итератор.
*/
    public static void main(String[] args) {
        try {
            kr1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void kr1() throws Exception {
        Pattern pattern = Pattern.compile("[^\\.,\\s;]{2,}");
        String path = "input.txt";
        Map<String, String> wordsMap = new HashMap<>();

        List<String> listOfStrings = readListOfStrings(path);

        Iterator<String> listIterator = listOfStrings.iterator();
        Matcher matcher;
        while (listIterator.hasNext()) {
            matcher = pattern.matcher(listIterator.next());
            String word;
            while (matcher.find()) {
                word = matcher.group();
                boolean isExist = false;
                for (String s : wordCombinations(word)) {
                    if (wordsMap.containsKey(s)) {
                        if (!wordsMap.get(s).contains(word)) {
                            wordsMap.replace(s, wordsMap.get(s) + " " + word);
                        }
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    wordsMap.put(word, word);
                }
            }
        }
        for (Map.Entry entry : wordsMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }
    }

    private static List<String> readListOfStrings(String path) {
        BufferedReader br = null;
        List<String> listOfStrings = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(new File(path)));
            String tempString = br.readLine();
            while (tempString != null) {
                listOfStrings.add(tempString);
                tempString = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println("Reading from file successfully failed. IOException");
        } finally {
            try {
                br.close();
            } catch (IOException | NullPointerException ex) {
                System.out.println("Stream didn't closed or not exists. Reading from file failed. ");
            }
        }
        return listOfStrings;
    }

    private static String[] wordCombinations(String s) {
        String[] array = new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            array[i] = s.substring(i, s.length()) + s.substring(0, i);
        }
        return array;
    }
}
































