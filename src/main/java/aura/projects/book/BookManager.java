package aura.projects.book;

import java.sql.Date;
import java.util.Scanner;

public class BookManager {
    private Book [] books = new Book[100];

    /**
     * 添加一本书到图书管理库中
     * @param book 需要加入图书管理库的书
     */
    public void Add(Book book){
        for (int i=0;i<books.length;i++){
            if (books[i]==null){
                books[i] = book;
                System.out.println("添加成功！");
                break;
            }
        }
    }

    /**
     * 修改图书管理库中的书
     * @param bid 要修改的图书编号
     * @param book 修改后的图书资料
     */
    public void Modify(int bid,Book book){
        for (Book b:books){
            if (bid == b.getBid()){
                b.setName(book.getName());
                b.setPrice(book.getPrice());
                b.setAuthor(book.getAuthor());
                b.setDate(book.getDate());
                System.out.println("修改成功");
                break;
            }
        }
    }

    public boolean Exists(int bid){
        boolean b = false;
        for (int i=0;i<books.length;i++){
            if (bid == books[i].getBid()){
                b =  true;
                break;
            }else {
                b = false;
            }
        }
        return b;
    }

    public void Delete(int bid){
        boolean bool = false;
        for (int i =0 ; i<books.length;i++){
            if (bid == books[i].getBid()){
                bool = true;
                for (int j=i;j<books.length-1;j++){
                    books[j] = books[j+1];
                }
                System.out.println("删除成功！");
                break;
            }
        }
        if(bool == false) {
            System.out.println("无此图书");
        }

    }

    public void queryAll(){
        if (books[0] == null){
            System.out.println("没有图书");
            return;
        }
        System.out.println("编号\t名称\t\t单价\t作者\t出版日期");
        for(Book b : books) {
            if(b == null) {
                break;
            }
            System.out.println(b);
        }
    }

    public void Menu(){
        System.out.println("\t\t*****************");
        System.out.println("\t\t* 图书关系系统   2.0  *");
        System.out.println("\t\t*****************");
        System.out.println("\t--------------------------------------");
        System.out.println("\t  1.添加；2.修改；3.删除；4.查询；5.退出");
        System.out.println("\t--------------------------------------");
    }

    public void Start(){
        Scanner sc = new Scanner(System.in);
        Menu();
        int menuNo = sc.nextInt();
        int bid;
        String name;
        double price;
        String author;
        Date date;
        do {
            switch (menuNo){
                case 1:
                    System.out.print("* 请输入图书编号：");
                    bid = sc.nextInt();
                    System.out.print("* 请输入图书名：");
                    name = sc.next();
                    System.out.print("* 请输入图书单价：");
                    price = sc.nextDouble();
                    System.out.print("* 请输入图书作者:");
                    author = sc.next();
                    System.out.print("* 请输入图书出版日期(年-月-日)：");
                    date = Date.valueOf(sc.next());
                    Book book = new Book(bid,name,price,author,date);
                    Add(book);
                    break;
                case 2:
                    System.out.print("* 请输入要修改的图书编号：");
                    bid = sc.nextInt();
                    if(Exists(bid)) {
                        System.out.print("* 请输入新的图书名：");
                        name = sc.next();
                        System.out.print("* 请输入新的图书单价：");
                        price = sc.nextDouble();
                        System.out.print("* 请输入新的图书作者:");
                        author = sc.next();
                        System.out.print("* 请输入新的图书出版日期(年-月-日)：");
                        date = Date.valueOf(sc.next());
                        Book newBook = new Book(bid,name,price,author,date);
                        Modify(bid, newBook);
                    }else {
                        System.out.println("无此图书！");
                    }
                    break;
                case 3:
                    System.out.print("* 请输入要删除的图书编号：");
                    bid = sc.nextInt();
                    if(Exists(bid)) {
                        Delete(bid);
                    }else {
                        System.out.println("无此图书！");
                    }
                    break;
                case 4:
                    queryAll();
                    break;
                case 5:
                    System.out.println("退出图书管理系统！");
                    System.exit(0);
            }
            Menu();
            menuNo = sc.nextInt();
        }while (true);




    }


}
