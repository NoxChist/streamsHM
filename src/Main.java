import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        List<Integer> result = new ArrayList<>();
        for (int num : list) {
            if (num > 0 && num % 2 == 0) {
                result.add(num);
            }
        }
        result.sort(Comparator.naturalOrder());
        for (int num : result) {
            System.out.println(num);
        }
    }
}