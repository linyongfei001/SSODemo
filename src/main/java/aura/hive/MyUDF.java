package aura.hive;

import org.apache.hadoop.hive.ql.exec.UDF;

//自定义函数

public class MyUDF extends UDF{
    //求一个数的平方
    //返回值  可以是 void？？ 不要是
    //参数   用户传入的
    public int evaluate(int a) {
        return a*a;
    }
    //自己定义一个字符串拼接  默认的分隔符的
    public String evaluate(String str1,String str2) {
        return str1+"-"+str2;
    }
    //求两个数的和

    public int evaluate(int a,int b) {
        return a+b;
    }

    //1.1.128.0         001.001.128.000  自定义udf
    public static String evaluate(String ip){
        String[] split = ip.split("\\.");
        StringBuffer sb=new StringBuffer();
        //循环遍历判断
        for(String tmp:split){
            //判断长度  长度1   00    2  0   3
            String ss="00000"+tmp;//000001    00000128
            sb.append(ss.substring(ss.length()-3)).append(".");
        }
        return sb.toString().substring(0,sb.length()-1);
    }
    public static void main(String[] args) {
        System.out.println(evaluate("192.1.1.12"));
    }
}
