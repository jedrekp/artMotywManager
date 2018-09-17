package motyw.art.artMotywManager.domain;

import motyw.art.artMotywManager.util.JewelerySubstance;
import motyw.art.artMotywManager.util.JeweleryType;
import motyw.art.artMotywManager.util.ProductAvailability;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;


@Entity
public class Jewelery extends Product {
    @Column(name = "jewelery_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private JeweleryType jeweleryType;
    @NotNull
    @Enumerated(EnumType.STRING)
    private JewelerySubstance substance;

    public Jewelery(String id, ProductAvailability availability, String description, double price, CommonsMultipartFile imageFile, JeweleryType jeweleryType, JewelerySubstance substance) {
        this.id = id;
        this.availability =availability;
        this.description = description;
        this.price = price;
        this.imageFile = imageFile;
        this.jeweleryType = jeweleryType;
        this.substance = substance;
    }

    public Jewelery() {
    }

    public JeweleryType getJeweleryType() {
        return jeweleryType;
    }

    public void setJeweleryType(JeweleryType jeweleryType) {
        this.jeweleryType = jeweleryType;
    }

    public JewelerySubstance getSubstance() {
        return substance;
    }

    public void setSubstance(JewelerySubstance substance) {
        this.substance = substance;
    }
}