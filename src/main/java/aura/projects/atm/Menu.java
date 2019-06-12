package aura.projects.atm;

import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private Atm atm = new Atm();
    private int index;//下标
    private int cardIdNum = 2;

    public void loginMenu() {
        int no;

        String icardId,ipwd,zjhm;
        System.out.println("\n-----------ATM管理系统----------");
        System.out.println("\t1.登录 2.开户 3.退出");
        System.out.println("-------------------------------\n");
        System.out.print("-- 输入选择：");
        no = input.nextInt();
        switch (no){
            case 1:
                //登录
                System.out.print("-- 输入卡号：");
                icardId = input.next();
                System.out.print("-- 输入密码：");
                ipwd = input.next();
                index = atm.loginCheck(icardId, ipwd);//记录  账户 的 索引 （数组中的下标）
                if(index == -1) {
                    System.out.println("* 先开户 *");
                    loginMenu();
                }else {
                    mainMenu();//主菜单
                }
                break;
            case 2:
                //开户
                System.out.println("-- 输入用户身份证：");
                zjhm = input.next();
                System.out.println("-- 输入密码：");
                ipwd = input.next();
                icardId = "00"+this.cardIdNum;
                cardIdNum++;
                User user = new User();
                user.setCardId(icardId);
                user.setZjhm(zjhm);
                user.setPwd(ipwd);
                atm.addUser(user);
                loginMenu();
                break;
            case 3:
                System.out.println("退出成功");
                System.exit(0);
                break;
        }
    }

    private void mainMenu() {
        int no;
        int imoney = 0;
        String targetCardid;
        System.out.println("\n-------------------主菜单--------------------");
        System.out.println("\t1.存钱 2.取钱 3.转账4.查余额5.退出");
        System.out.println("----------------------------------------------\n");
        System.out.print("--输入选择：");
        no = input.nextInt();
        switch (no){
            case 1:
                //存钱
                System.out.print("-- 输入存入的金额:");
                imoney = input.nextInt();
                atm.addMoney(index, imoney);
                mainMenu();//主菜单
                break;
            case 2:
                //取钱
                System.out.print("-- 输入取出的金额:");
                imoney = input.nextInt();
                atm.reduceMoney(index, imoney);
                mainMenu();//主菜单
                break;
            case 3:
                //转账
                System.out.print("-- 输入目标账号：");
                targetCardid = input.next();
                System.out.print("-- 输入转入的金额：");
                imoney = input.nextInt();
                atm.changeMoney(index, targetCardid,imoney);
                mainMenu();//主菜单
                break;
            case 4:
                //查余额
                System.out.println("账号\t余额");
                atm.queryMoney(index);
                mainMenu();//主菜单
                break;
            default:
                System.out.println("已退出，请取卡！");
                System.exit(0);
        }
    }
}
