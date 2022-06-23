package com.example.inventory.payload;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemDTO {
    private Long id;

    //title should not be null or less than 2 char
    @NotEmpty
    @Size(min = 3, message = "Item title must have at least 3 characters")
    @Column(name = "Title", nullable = false)
    private String title;

    //description should have 10 or more char and not empty
    @NotEmpty
    @Size(min = 5,message = "Item description must have at least 5 char")
    @Column(name = "Description", nullable = false)
    private String description;

    //image must not be empty or null
    @NotEmpty
    @Column(name = "Image", nullable = false)
    private String image;

    //category should not be null or less than 2 char
    @NotEmpty
    @Size(min = 3, message = "Item category must have at least 3 characters")
    @Column(name = "Category", nullable = false)
    private String category;

    // price should not be null or less than 0
    @NotNull
    @Min(1)
    @Column(name = "Price", nullable = false)
    private Integer price;


    public ItemDTO() {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.category = category;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }

}
