import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()) {
            firstWordList = sc.nextLine();
        }
        if(sc.hasNext()) {
            secondWordList = sc.nextLine();
        }
        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        System.out.println(result);

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        List<String> validFirstWordList = isValidInput(firstWordList);
        List<String> validSecondWordList = isValidInput(secondWordList);
        return  validFirstWordList.stream()
                .filter(validSecondWordList::contains)
                .distinct()
                .map(s -> String.join(" ", s.split("")))
                .sorted(Comparator.comparing(a -> ((String) a)))
                .collect(Collectors.toList());
    }

    public static List<String> isValidInput(String input) {
        if (input.contains(",,")) {
            throw new RuntimeException("wrong input.");
        }
        return Arrays.stream(input.split(",")).map(s -> {
            if ( ! s.matches("[a-zA-Z]+") ){
                throw new RuntimeException("wrong input.");
            }else {
                return s.toUpperCase();
            }
        }).collect(Collectors.toList());
    }
}
