package aura.projects.guessGame;

import java.util.Scanner;

public class Person {
    private String name;
    private int score =0;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "name='" + name + '\t' + "score=" + score;
    }

    /**
     * 选择角色
     */
    public void setRole(){
        System.out.println("--输入角色选择（1.蝙蝠侠2.蜘蛛侠3钢铁侠）");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch (i){
            case 1:
                System.out.println("人选角色是：蝙蝠侠");
                this.name="蝙蝠侠";
                break;
            case 2:
                System.out.println("人选角色是：蜘蛛侠");
                this.name = "蜘蛛侠";
                break;
            case 3:
                System.out.println("人选角色是：钢铁侠");
                this.name = "钢铁侠";
                break;
        }
    }

    /**
     * 猜拳
     */
    public int showFirst(){
        Scanner input = new Scanner(System.in);
        System.out.println("-- 请出拳(1.剪刀2石头3. 布)");
        int no = input.nextInt();
        switch(no) {
            case 1:
                System.out.println("人出拳：剪刀");
                break;
            case 2:
                System.out.println("人出拳：石头");
                break;
            case 3:
                System.out.println("人出拳：布");
                break;
        }
        return no;
    }



}
