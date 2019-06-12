package aura.projects.guessGame;

import java.util.Scanner;

public class Game {
    private Person person = null;
    private Computer computer = null;
    private int count;
    {
        person = new Person();
        computer = new Computer();
        count = 0;
    }

    /**
     * 菜单展示
     */
    public void menu() {
        System.out.println("\t---------猜拳--------");
        System.out.println("\t规则：1.剪刀2石头3布");
        System.out.println("\t--------------------");
    }

    /**
     * 开始游戏
     */
    public void startGame(){
        //菜单
        this.menu();

        //设置角色
        computer.setRole();
        person.setRole();

        //开始游戏
        String next;
        Scanner input = new Scanner(System.in);
        System.out.println("-- 是否开始游戏(yes/no):");
        next  = input.next();
        while (next.equals("yes")){
            int pNo = person.showFirst();
            int cNo = computer.showFist();
            if(pNo == cNo ) {
                System.out.println("平局");
            }else if(pNo == 1 && cNo == 3 || pNo == 2 && cNo == 1 || pNo == 3 && cNo == 2) {
                System.out.println("人："+person.getName()+"赢了");
                person.setScore(person.getScore()+1);
            }else {
                System.out.println("机器："+computer.getName()+"赢了");
                computer.setScore(computer.getScore()+1);
            }
            count ++;
            System.out.println("--是否继续玩游戏");
            next = input.next();
        }
        showResult();

    }

    /**
     * 结果展示
     */
    public void showResult(){
        int ps = person.getScore();
        int cs = computer.getScore();
        System.out.println("----------总结果--------------");
        System.out.println(person.getName()+" VS "+computer.getName());
        System.out.println("对战："+count+"次");
        System.out.println("比分：人："+person.getName()+ps+",机器"+computer.getName()+cs);
        if(ps == cs) {
            System.out.println("平局了");
        }else if(ps > cs){
            System.out.println("人 "+ person.getName()+" 赢了");
        }else {
            System.out.println("机器 "+computer.getName()+"赢了");
        }

    }


}
