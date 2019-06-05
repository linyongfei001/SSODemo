package aura.javaSE.window;

/**
 * 单例模式（懒汉）
 */
public class LanHanWindow {
    private LanHanWindow(){

    }
    private static LanHanWindow LHWindow ;

    public static LanHanWindow getLHWindow(){
        if (LHWindow == null){
            LHWindow = new LanHanWindow();
        }
        return LHWindow;
    }


    public static void main(String[] args) {
        LanHanWindow win1 = LanHanWindow.getLHWindow();
        LanHanWindow win2 = LanHanWindow.getLHWindow();
        System.out.println(win1);
        System.out.println(win2);
    }

}
