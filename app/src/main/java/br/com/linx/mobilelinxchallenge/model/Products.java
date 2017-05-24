package br.com.linx.mobilelinxchallenge.model;

/**
 * Created by Wenderson Roberto on 23/05/2017.
 */
/**Classe Modelo contendo todas as informações do produto
 * @author Wenderson Roberto
 * @version 1.0
 */
public class Products {
    private Integer id;
    private String name;
    private String link;
    private String image;
    private String rating;
    private String price;
    private Double price_raw;
    private String last_price;
    private Double last_price_raw;
    private String desc;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Double getPrice_raw() {
        return price_raw;
    }

    public void setPrice_raw(Double price_raw) {
        this.price_raw = price_raw;
    }

    public String getLast_price() {
        return last_price;
    }

    public void setLast_price(String last_price) {
        this.last_price = last_price;
    }

    public Double getLast_price_raw() {
        return last_price_raw;
    }

    public void setLast_price_raw(Double last_price_raw) {
        this.last_price_raw = last_price_raw;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
