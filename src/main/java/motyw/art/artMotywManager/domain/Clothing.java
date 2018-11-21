package motyw.art.artMotywManager.domain;

import motyw.art.artMotywManager.util.ClothingSize;
import motyw.art.artMotywManager.util.ClothingTheme;
import motyw.art.artMotywManager.util.ClothingType;
import motyw.art.artMotywManager.util.ProductAvailability;
import motyw.art.artMotywManager.validators.AddValidation;
import motyw.art.artMotywManager.validators.EditValidation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Clothing extends Product {
    @NotNull
    @Column(name = "clothing_type")
    @Enumerated(EnumType.STRING)
    private ClothingType clothingType;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ClothingSize size;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ClothingTheme theme;
    @NotNull
    @NotBlank(groups = {AddValidation.class, EditValidation.class}, message = "{notEmpty}")
    @Column(name = "cut_type")
    private String cutType;

    public Clothing(String id, ProductAvailability availability, String description, double price,
                    ClothingType clothingType, ClothingSize size, ClothingTheme theme, String cutType) {
        this.id = id;
        this.availability = availability;
        this.description = description;
        this.price = price;
        this.clothingType = clothingType;
        this.size = size;
        this.theme = theme;
        this.cutType = cutType;
    }

    public Clothing() {
    }

    public ClothingType getClothingType() {
        return clothingType;
    }

    public void setClothingType(ClothingType clothingType) {
        this.clothingType = clothingType;
    }

    public ClothingSize getSize() {
        return size;
    }

    public void setSize(ClothingSize size) {
        this.size = size;
    }

    public ClothingTheme getTheme() {
        return theme;
    }

    public void setTheme(ClothingTheme theme) {
        this.theme = theme;
    }

    public String getCutType() {
        return cutType;
    }

    public void setCutType(String cutType) {
        this.cutType = cutType;
    }
}


