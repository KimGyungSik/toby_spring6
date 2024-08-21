package tobyspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class SortTest {

    Sort sort;

    @BeforeEach
    void beforeEach() {
        // 준비 (given)
        sort = new Sort();
        System.out.println(this); // 매번 새로운 인스턴스를 생성
    }

    @Test
    void sort() {
        // 실행 (when)
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b")); // Arrays.asList() -> 가변 list 생성

        // 검증 (then)
        Assertions.assertThat(list).isEqualTo(List.of("b","aa")); // List.of -> 불변 list 생성
    }

    @Test
    void sort3Items() {
        // 실행 (when)
        List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc","b")); // Arrays.asList() -> 가변 list 생성

        // 검증 (then)
        Assertions.assertThat(list).isEqualTo(List.of("b","aa","ccc")); // List.of -> 불변 list 생성
    }

    @Test
    void sortAlreadySorted() {
        // 실행 (when)
        List<String> list = sort.sortByLength(Arrays.asList("b","aa","ccc")); // Arrays.asList() -> 가변 list 생성

        // 검증 (then)
        Assertions.assertThat(list).isEqualTo(List.of("b","aa","ccc")); // List.of -> 불변 list 생성
    }


}