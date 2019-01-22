package motyw.art.artMotywManager.util;

public enum UserMessages {
    PRODUCT_DELETED_MSG("Z bazy danych został usunięty produkt o id - "),
    PRODUCT_NOT_FOUND_MSG("Brak wyników dla szukanych wartości."),
    UPLOAD_SIZE_EXCEEDED("Operacja nie powiodła się. Rozmiar zdjęcia nie może przekraczać 4 MB");

    private String userMessage;

    UserMessages(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }
}
