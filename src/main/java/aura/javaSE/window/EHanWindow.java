package aura.javaSE.window;

/**
 * 单例模式（饿汉）
 */
public class EHanWindow {
    private static EHanWindow EHWindow = new EHanWindow();
    private EHanWindow(){

    }
    public static EHanWindow getEHWindow() {
        return EHWindow;
    }

    public static void main(String[] args) {
        EHanWindow win1 = EHanWindow.getEHWindow();
        EHanWindow win2 = EHanWindow.getEHWindow();
        System.out.println(win1);
        System.out.println(win2);
    }


}
