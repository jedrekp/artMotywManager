package motyw.art.artMotywManager.util;

public enum ProductAvailability {
    AVAILABLE("dostępny"),
    SOLD("sprzedany");

    private String availabilityStatus;

    ProductAvailability(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }
}
