package com.king.king_lens.Grid_List;

/**
 * Created by NgocTri on 10/22/2016.
 */

public class Product {
    private int imageId;
    private String title;
    private String description;
   // private String price;

    public Product(int imageId, String title, String description) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        //this.price=price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  //  public String getPrice(){return price;}
}
