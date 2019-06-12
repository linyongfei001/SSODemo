package aura.projects.book;


import java.sql.Date;
import java.util.Objects;

public class Book {
    private int bid;
    private String name;
    private double price;
    private String author;
    private Date date;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Book(int bid, String name, double price, String author, Date date) {
        this.bid = bid;
        this.name = name;
        this.price = price;
        this.author = author;
        this.date = date;
    }

    public Book() {
    }

    @Override
    public String toString() {

        return bid+"\t"+name+"\t"+price+"\t"+author+"\t"+date;
    }
}
