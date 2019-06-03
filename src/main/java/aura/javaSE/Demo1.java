package aura.javaSE;

import org.junit.Test;

import java.util.Scanner;

public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("hello world");
//        Thread.sleep(1000);
//        System.out.println("hello world");
        test();
    }

    @Test
    public static void test(){
        System.out.println("hello world");
        int num1 = 33;
        int num2 = 44;
        String b1 = num2>num1?"ture":"flase";
        System.out.println(b1);
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        System.out.println(a);
        String b = sc.next();
        System.out.println(b);
    }
}
