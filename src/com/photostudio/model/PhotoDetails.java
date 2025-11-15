package com.photostudio.model;

/**
 * Зберігає деталі фотографій для замовлення (кількість, стиль).
 * Відповідає діаграмі класу PhotoDetails.
 */
public class PhotoDetails {

    // Атрибути
    private int quantity;
    private String style;

    /**
     * Public конструктор, що ініціалізує об'єкт.
     */
    public PhotoDetails(int quantity, String style) {
        this.quantity = quantity;
        this.style = style;
    }

    // Public "гетери" для доступу до приватних даних

    public int getQuantity() {
        return quantity;
    }

    public String getStyle() {
        return style;
    }
}