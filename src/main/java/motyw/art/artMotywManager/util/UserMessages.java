package motyw.art.artMotywManager.util;

public enum UserMessages {
    PRODUCT_DELETED_MSG("Z bazy danych został usunięty produkt o id - "),
    PRODUCT_NOT_FOUND_MSG("Brak wyników dla szukanych wartości.");

    private String userMessage;

    UserMessages(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }
}
