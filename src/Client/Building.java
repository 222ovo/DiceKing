package Client;

import java.io.Serializable;

public class Building implements Serializable {
    private String name;    //房产的名字
    private int price;      //房产的价格
    private int revenue;    //其他玩家踩中后获得的收益
    private int id = -1;    //所有者

    public Building(String name, int price, int revenue)
    {
        this.name = name;
        this.price = price;
        this.revenue = revenue;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
