package aura.projects.atm;

public class Atm {
    private User [] userArr =null;
    Atm() {
        userArr =  new User[100];
        User user = new User();
        userArr[0] = user;
        userArr[0].setCardId("001");
        userArr[0].setPwd("111111");
    }

    /**
     * 登录时验证是否存在
     * @param icardId 登录账户
     * @param ipwd 登录密码
     * @return 返回状态码
     */
    public int loginCheck(String icardId,String ipwd){
        for (int i=0;i<userArr.length;i++){
            if(userArr[i] == null) {
                break;
            }
            if(userArr[i].getCardId().equals(icardId) && userArr[i].getPwd().equals(ipwd)) {
                return i;//返回数组中的下标
            }
        }
        return -1;
    }

    /**
     * 开户
     * @param user 新注册的用户
     */
    public void addUser(User user){
        for(int i = 0; i < userArr.length; i++) {
            if(userArr[i] == null) {
                userArr[i] = user;
                break;
            }
        }
        System.out.println("* 开户成功 *");
        System.out.println("卡号\t余额");
        System.out.println(user);
    }


    /**
     * 查询账户余额
     * @param index 要查询的账户在数组中的下标
     */
    public void queryMoney(int index) {
        System.out.println(userArr[index]);
    }

    /**
     * 转账
     * @param index 用户在数组中的下标
     * @param targetCardid  目标账户的卡号
     * @param imoney 要转账的钱
     */
    public void changeMoney(int index, String targetCardid, int imoney) {
        int index2=0;
        int i;
        for(i = 0; i <userArr.length;i++) {
            if(userArr[i] == null) {
                System.out.println("* 无此目标转账用户，退出系统！ *");
                System.exit(0);//退出 JVM

            }
            if(userArr[i].getCardId().equals(targetCardid)) {
                index2 = i;
                break;
            }
        }
        if(i == userArr.length ) {
            System.out.println("* 无此目标转账用户，退出系统！ *");
            System.exit(0);
        }
        userArr[index].setMoney(userArr[index].getMoney()-imoney);
        userArr[index2].setMoney(userArr[index2].getMoney()+imoney);
        System.out.println("--------转账后----------");
        System.out.println(userArr[index].getCardId()+":"+userArr[index]);
        System.out.println("目标账户:"+userArr[index2]);
    }

    /**
     * 取钱
     * @param index 用户在数组中的下标
     * @param imoney 要取的钱
     */
    public void reduceMoney(int index, int imoney) {
        userArr[index].setMoney(userArr[index].getMoney()-imoney);
        System.out.println("* "+userArr[index].getCardId()+" 取款成功 *");;
    }

    /**
     * 存钱
     * @param index 用户在数组中的下标
     * @param imoney 要存的钱
     */
    public void addMoney(int index, int imoney) {
        userArr[index].setMoney(userArr[index].getMoney()+imoney);
        System.out.println("* "+userArr[index].getCardId()+" 存款成功 *");
    }
}
