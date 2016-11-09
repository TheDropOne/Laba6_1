import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Semen on 08-Nov-16.
 */
public class Runner {
    /*Задание. Разработать консольное приложение, выполняющее следующие действия:

    1. На вход поступает текстовый файл input.in.
    2. На 4 балла. Отсортировать строки текста по возрастанию количества символов в строке.
        Результат записать в выходной файл output1.out.
    3. На 5 баллов (задание 2 + задание 3). Отсортировать символы в каждой строке текста по
        возрастанию кодов символов. Результат записать в выходной файл output2.out.
    4. На 6-7 баллов (задание 2 + задание 3 + задание 4). Отсортировать слова в каждой строке
        файла по алфавиту. Регистр не имеет значения. Разделители: пробел, точка, запятая, точка
        с запятой. Результат записать в выходной файл output3.out.

    Дополнительные баллы (за каждый пункт 1 балл):

        1. Использовать коллекцию.
        2. Использовать итератор.
        3. Реализовать компараторы для сортировки.
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


    private static void kr2() {

    }

    private static void kr3() {
        String pathInput = "input.in";
        String pathOutput1 = "output1.out";


        List<String> listOfStrings = readListOfStrings(pathInput);
        // task 1. 4 points
        Set<String> setOfStrings = new TreeSet<>();
        setOfStrings = (Set<String>) listOfStrings;
        for (String setOfString : setOfStrings) {
            System.out.println(setOfString);
        }
        writeToFile(pathOutput1,setOfStrings.iterator());
    }

    private static void writeToFile(String filename, Iterator<?> iterator) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File(filename));
            while (iterator.hasNext()) {
                fw.write((String) iterator.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException | NullPointerException ex) {
                System.out.println("Stream didn't closed or not exists. Reading from file failed. ");
            }
        }
    }

}
































