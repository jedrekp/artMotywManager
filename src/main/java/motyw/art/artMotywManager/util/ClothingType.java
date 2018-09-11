package motyw.art.artMotywManager.util;

public enum ClothingType {
    DRESS_TYPE("sukienka"),
    SKIRT_TYPE("spódnica"),
    PANTS_TYPE("spodnie"),
    SHIRT_TYPE("bluzka"),
    SWEATSHIRT_TYPE("bluza"),
    HAT_TYPE("czapka"),
    JACKET_TYPE("kurtka"),
    SUIT_TYPE("żakiet"),
    OTHER_TYPE("inny");

    private String typeName;

    ClothingType(String typeName){
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
