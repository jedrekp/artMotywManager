package motyw.art.artMotywManager.domain;

import motyw.art.artMotywManager.util.ClothingSize;
import motyw.art.artMotywManager.util.ClothingTheme;
import motyw.art.artMotywManager.util.ClothingType;
import motyw.art.artMotywManager.util.ProductAvailability;
import motyw.art.artMotywManager.validators.AddValidation;
import motyw.art.artMotywManager.validators.EditValidation;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static motyw.art.artMotywManager.validators.ValidationMessages.*;

@Entity
public class Clothing extends Product {
    @NotNull(groups = {AddValidation.class, EditValidation.class})
    @Column(name = "clothing_type")
    @Enumerated(EnumType.STRING)
    private ClothingType clothingType;
    @NotNull(groups = {AddValidation.class, EditValidation.class})
    @Enumerated(EnumType.STRING)
    private ClothingSize size;
    @NotNull(groups = {AddValidation.class, EditValidation.class})
    @Enumerated(EnumType.STRING)
    private ClothingTheme theme;
    @NotNull(groups = {AddValidation.class, EditValidation.class}, message = NOT_EMPTY)
    @Size(groups = {AddValidation.class, EditValidation.class}, min = 1, message = NOT_EMPTY)
    @Column(name = "cut_type")
    private String cutType;

    public Clothing(String id, ProductAvailability availability, String description, double price, CommonsMultipartFile imageFile,
                    ClothingType clothingType, ClothingSize size, ClothingTheme theme, String cutType) {
        this.id = id;
        this.availability = availability;
        this.description = description;
        this.price = price;
        this.imageFile = imageFile;
        this.clothingType = clothingType;
        this.size = size;
        this.theme = theme;
        this.cutType = cutType.toLowerCase();
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
        this.cutType = cutType.toLowerCase();
    }
}


