package com.pankajrawat.homescreen;

public class ModelReview {
    private String image;
    private String stars;
    private String name,description,link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ModelReview(String image, String name, String stars, String description, String link) {
        this.image = image;
        this.name = name;
        this.stars = stars;
        this.description = description;
        this.link=link;
    }

    public void setImage(String image) {


        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getStars() {
        return stars;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
