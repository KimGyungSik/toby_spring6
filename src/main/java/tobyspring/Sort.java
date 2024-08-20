package tobyspring;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public static void main(String[] args) {
        List scores = Arrays.asList("z","x","spring","java");
        Collections.sort(scores, (Comparator<String>) (o1, o2) -> o1.length() - o2.length());

        scores.forEach(System.out::println);
    }
}
