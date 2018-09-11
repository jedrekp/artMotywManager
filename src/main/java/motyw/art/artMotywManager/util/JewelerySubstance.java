package motyw.art.artMotywManager.util;

public enum JewelerySubstance {
    METAL_SUBSTANCE("metal"),
    PRECIOUS_METAL_SUBSTANCE("metal szlachetny"),
    BEADING_SUBSTANCE("koraliki");

    private String substanceName;

    JewelerySubstance(String substanceName){
        this.substanceName = substanceName;
    }

    public String getSubstanceName() {
        return substanceName;
    }
}
