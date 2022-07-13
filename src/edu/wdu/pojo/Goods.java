package edu.wdu.pojo;

public class Goods {
    private Integer id;
    private String name;
    private float price;
    private String imgUrl;

    public Goods() {
    }

    public Goods(Integer id, String name, float price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getimgUrl() {
        return imgUrl;
    }

    public void setimgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
