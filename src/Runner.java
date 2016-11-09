import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Semen on 08-Nov-16.
 */
public class Runner {

    public static void main(String[] args) {
        try {
            kr1();

            kr3();
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
        String pathInput = "input2.txt";
        String pathOutput1 = "output1.txt";
        String pathOutput2 = "output2.txt";
        String pathOutput3 = "output3.txt";


        List<String> listOfStrings = readListOfStrings(pathInput);
        // task 1. 4 points
        Collections.sort(listOfStrings);
        writeToFile(pathOutput1, listOfStrings.iterator());
        //Task 2.
        List<String> listWithSortedStrings = new ArrayList<>();
        for (String s : listOfStrings) {
            List<Character> characterList = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                characterList.add(s.charAt(i));
            }
            Collections.sort(characterList);
            StringBuilder sb = new StringBuilder();
            for (char a : characterList) {
                sb.append(a);
            }
            listWithSortedStrings.add(sb.toString());
        }
        writeToFile(pathOutput2, listWithSortedStrings.iterator());

        // task 3. 10 points
        Comparator<String> comparator = (o1, o2) -> {
            if (o1.length() < o2.length()) return -1;
            if (o1.length() > o2.length()) return 1;
            o1 = o1.toLowerCase();
            o2 = o2.toLowerCase();
            return o1.compareTo(o2);
        };
        List<String> arrayList = new ArrayList<>();
        Pattern pattern = Pattern.compile("[^\\.,\\s;]+");
        Matcher matcher;
        Iterator<String> listIterator = listOfStrings.iterator();
        while (listIterator.hasNext()) {
            matcher = pattern.matcher(listIterator.next());
            List<String> tempList = new ArrayList<>();
            while (matcher.find()) {
                tempList.add(matcher.group());
            }
            Collections.sort(tempList, comparator);
            StringBuilder sb = new StringBuilder();
            for (String s : tempList) {
                sb.append(s);
                sb.append(" ");
            }
            arrayList.add(sb.toString());
        }
        writeToFile(pathOutput3, arrayList.iterator());
    }

    private static void writeToFile(String filename, Iterator<?> iterator) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File(filename));
            while (iterator.hasNext()) {
                fw.write((String) iterator.next());
                fw.write(System.lineSeparator());
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
































