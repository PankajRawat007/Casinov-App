package com.pankajrawat.homescreen;

public class AndroidcasinoModelReview {
    private String image;

    private String price;
    private String discount;
    private String rate;
    private String link;

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

    private String casinoname;
    private String description;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public AndroidcasinoModelReview(String image, String price, String discount, String casinoname, String description, String rate, String link) {
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.casinoname=casinoname;
        this.description=description;
        this.rate=rate;
        this.link=link;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }
}
