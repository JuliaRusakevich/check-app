package main.java.ru.clevertec.check.entity.dto;


import java.util.Objects;

public class CheckProduct {

    private int id;
    private int quantity;
    private String description;
    private double price;
    private double total;
    private double discount;


    public CheckProduct() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckProduct item = (CheckProduct) o;
        return quantity == item.quantity && Double.compare(item.price, price) == 0 && Double.compare(item.total, total) == 0 && Double.compare(item.discount, discount) == 0 && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, description, price, total, discount);
    }


    @Override
    public String toString() {
        return "Item{" +
               "quantity=" + quantity +
               ", description='" + description + '\'' +
               ", price=" + price +
               ", total=" + total +
               ", discount=" + discount +
               '}';
    }
}
