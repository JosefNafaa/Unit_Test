package com.unitTests.unittesting.lcl;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }

    /**
     * Cette méthode compte le nombre de neufs dans un tableau.
     * @return le nombre de neufs
     */
    public static int arrayCount9(List<Integer> nums) {
        return Optional.ofNullable(nums)
                .map(list -> (int) list.stream().filter(a -> a == 9).count())
                .orElse(0);
    }

    public static void test1() throws Exception {
        int received = Main.arrayCount9(Arrays.asList(1, 2, 9));
        if(received != 1){
            throw new Exception("TEST1 a échoué");
        }
    }

    public static void test2() throws Exception {
        int received = Main.arrayCount9(Arrays.asList(1, 9, 9));
        if(received != 2){
            throw new Exception("TEST2 a échoué");
        }
    }

    public static void test3() throws Exception {
        int received = Main.arrayCount9(Arrays.asList(1, 9, 9, 3, 9));
        if(received != 3){
            throw new Exception("TEST3 a échoué");
        }
    }

    public static void test4() throws Exception {
        int received = Main.arrayCount9(Arrays.asList(1, 2, 3));
        if(received != 0){
            throw new Exception("TEST4 a échoué");
        }
    }

    public static void test5() throws Exception {
        int received = Main.arrayCount9(Arrays.asList(99, 9, 99, 0));

        if(received != 1){
            throw new Exception("TEST5 a échoué");
        }
    }


    public static void test6() throws Exception {
        int received = Main.arrayCount9(null);
        if(received != 0){
            throw new Exception("TEST6 a échoué");
        }
    }

    public static void test7() throws Exception {
        int received = Main.arrayCount9(Arrays.asList(1, 9, 2, 4, 3, 1));

        if(received != 1){
            throw new Exception("TEST7 a échoué");
        }
    }
}