import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Semen on 08-Nov-16.
 */
public class TestClass {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[^\\.,\\s;]+");
        Matcher matcher = pattern.matcher("камыш мышка кот кролик ток радар дарра кек кек ");
        String s;
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
