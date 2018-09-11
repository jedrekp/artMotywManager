package motyw.art.artMotywManager.util;

public enum ClothingSize {
    XS_SIZE("XS"),
    S_SIZE("S"),
    M_SIZE("M"),
    L_SIZE("L"),
    XL_SIZE("XL"),
    XXL_SIZE("XXL"),
    UNIVERSAL_SIZE("uniwersalny");

    private String sizeName;

    ClothingSize(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getSizeName() {
        return sizeName;
    }
}
