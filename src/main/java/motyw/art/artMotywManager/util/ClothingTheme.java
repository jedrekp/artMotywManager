package motyw.art.artMotywManager.util;

public enum ClothingTheme {
    ANIMAL_THEME("zwierzęcy"),
    FLORAL_THEME("roślinny"),
    ABSTRACT_THEME("abstrakcja"),
    OTHER_THEME("inny"),
    NO_THEME("brak");

    private String themeName;

    ClothingTheme(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeName() {
        return themeName;
    }
}
