package aura.projects.atm;

public class User {
    private String cardId;
    private String pwd;
    private String zjhm;
    private double money;

    public User(String cardId, String pwd, String zjhm) {
        this.cardId = cardId;
        this.pwd = pwd;
        this.zjhm = zjhm;
    }

    public User() {
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return this.cardId+"\t"+this.money;
    }
    public void show() {
        System.out.println(this.cardId+"\t"+this.money);
    }
}
