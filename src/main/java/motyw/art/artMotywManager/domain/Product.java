package motyw.art.artMotywManager.domain;

import motyw.art.artMotywManager.util.ProductAvailability;
import motyw.art.artMotywManager.validators.AddValidation;
import motyw.art.artMotywManager.validators.EditValidation;
import motyw.art.artMotywManager.validators.ImageFile;
import motyw.art.artMotywManager.validators.UniqueId;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {
    @Id
    @Column(unique = true, name = "product_id")
    @NotNull
    @NotBlank(groups = {AddValidation.class}, message = "{notEmpty}")
    @UniqueId(groups = {AddValidation.class}, message = "{id.alreadyExists}")
    @Size(groups = {AddValidation.class},max = 20, message = "{id.tooLong")
    String id;
    @Size(groups = {AddValidation.class, EditValidation.class}, max = 250, message = "{description.tooLong}")
    String description;
    @NotNull
    @Min(groups = {AddValidation.class, EditValidation.class}, value = 0, message = "{price.wrongFormat}")
    @Max(groups = {AddValidation.class, EditValidation.class}, value = 10000, message = "{price.wrongFormat}")
    double price;
    @NotNull
    @Enumerated(EnumType.STRING)
    ProductAvailability availability;
    @Transient
    @ImageFile(groups = {AddValidation.class, EditValidation.class}, message = "{image.invalidFile}")
    private CommonsMultipartFile imageFile;
    @Column(name = "image_data", columnDefinition = "mediumblob")
    private byte[] imageData;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy kk:mm:ss.SSS")
    @Column(name = "sale_date")
    private Date saleDate = null;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductAvailability getAvailability() {
        return availability;
    }

    public void setAvailability(ProductAvailability availability) {
        this.availability = availability;
    }

    public CommonsMultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(CommonsMultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
}

