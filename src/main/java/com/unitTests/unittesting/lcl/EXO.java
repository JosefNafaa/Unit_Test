package com.unitTests.unittesting.lcl;


import java.util.stream.IntStream;

public class EXO {
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
     * Cette méthode compare les deux chaînes de caractères pour compter les
     * caractères identiques et à la même place dans le mots.
     */
    public static int calculate(String car1, String car2) {
        return (int) IntStream.range(0, Math.min(car1.length(), car2.length()))
                .filter(i -> car1.charAt(i) == car2.charAt(i))
                .count();
    }


    public static void test1() throws Exception {
        int received = EXO.calculate("xxcaazz", "xxbaaz");
        if(received != 5){
            throw new Exception("TEST1 a échoué");
        }
    }
    public static void test2() throws Exception {
        int received = EXO.calculate("abc", "abc");
        if(received != 3){
            throw new Exception("TEST2 a échoué");
        }
    }
    public static void test3() throws Exception {
        int received = EXO.calculate("abc", "axc");
        if(received != 2){
            throw new Exception("TEST3 a échoué");
        }
    }
    public static void test4() throws Exception {
        int received = EXO.calculate("hello", "he");
        if(received != 2){
            throw new Exception("TEST4 a échoué");
        }
    }
    public static void test5() throws Exception {
        int received = EXO.calculate("eel", "hello");
        if(received != 2){
            throw new Exception("TEST5 a échoué");
        }
    }
    public static void test6() throws Exception {
        int received = EXO.calculate("h", "hello");
        if(received != 1){
            throw new Exception("TEST6 a échoué");
        }
    }
    public static void test7() throws Exception {
        int received = EXO.calculate("aurevoir", "hello");
        if(received != 0){
            throw new Exception("TEST7 a échoué");
        }
    }
}