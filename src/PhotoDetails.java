public // Клас PhotoDetails як сутність (Information Expert, Creator)
class PhotoDetails {
    private String id = "PD" + System.currentTimeMillis();
    private int quantity;
    private String style;

    public PhotoDetails(int quantity, String style) {
        this.quantity = quantity;
        this.style = style;
    }

    public int getQuantity() { return quantity; }
    public String getStyle() { return style; }
}
