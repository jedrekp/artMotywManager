package motyw.art.artMotywManager.util;

public enum  JeweleryType {
    NECKLACE_TYPE("naszyjnik"),
    EARINGS_TYPE("kolczyki"),
    BRACELET_TYPE("bransoletka"),
    DIFFERENT_JEWELERY_TYPE("inny");


    private String typeName;

    JeweleryType(String typeName){
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
