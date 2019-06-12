package aura.projects.guessGame;

public class Computer {
    private String name;
    private int score =0;

    public String getName() {
        return name;
    }

    public Computer() {
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

    public Computer(String name) {
        this.name = name;
    }

    /**
     * 计算机随机选择角色
     */
    public void setRole() {
        int r = (int)(Math.random()*(3 - 1 + 1)+ 1);
        switch(r) {
            case 1:
                System.out.println("计算机角色：吸血鬼");
                this.name = "吸血鬼";
                break;
            case 2:
                System.out.println("计算机角色：狼人");
                this.name = "狼人";
                break;
            case 3:
                System.out.println("计算机角色：变形金刚");
                this.name = "变形金刚";
                break;
        }
    }

    /**
     * 计算机随机出拳
     * @return
     */
    public int showFist() {
        int r = (int)(Math.random()*(3 - 1 + 1)+ 1);
        switch(r) {
            case 1:
                System.out.println("计算机出拳：剪刀");
                break;
            case 2:
                System.out.println("计算机出拳：石头");
                break;
            case 3:
                System.out.println("计算机出拳：布");
                break;

        }
        return r;
    }






}
