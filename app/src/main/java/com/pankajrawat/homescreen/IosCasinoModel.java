package com.pankajrawat.homescreen;

public class IosCasinoModel {
    private String image;

    private String price;
    private String discount;
    private String rate;
    private String casinoname;
    private String description;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public IosCasinoModel(String image, String price, String discount, String rate, String casinoname, String description, String link) {
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.rate = rate;
        this.casinoname = casinoname;
        this.description = description;
        this.link=link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCasinoname() {
        return casinoname;
    }

    public void setCasinoname(String casinoname) {
        this.casinoname = casinoname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
