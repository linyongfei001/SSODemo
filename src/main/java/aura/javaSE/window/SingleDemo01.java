package aura.javaSE.window;
//懒汉
public class SingleDemo01 {
    private SingleDemo01(){

    }
    private static SingleDemo01 single;
    public SingleDemo01 getInstance(){//2个线程
        if(single==null){//2个线程  作用：首先进行一次过滤  提高效率  假设
            synchronized(SingleDemo01.class){//
                if(single==null){//为了防止多线程创建多个对象
                    single=new SingleDemo01();
                }
            }
        }
        return single;
    }

}
