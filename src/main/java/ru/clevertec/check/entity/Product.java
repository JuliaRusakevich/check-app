package main.java.ru.clevertec.check.entity;

import java.util.Objects;

public class Product {

    private Integer id;
    private String description;
    private double price;
    private int quantity;
    private boolean wholesaleProduct;


    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getWholesaleProduct() {
        return wholesaleProduct;
    }

    public void setWholesaleProduct(Boolean wholesaleProduct) {
        this.wholesaleProduct = wholesaleProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", description='" + description + '\'' +
               ", price=" + price +
               ", quantityInStock=" + quantity +
               ", wholesaleProduct=" + wholesaleProduct +
               '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(id, product.id) && Objects.equals(description, product.description) && Objects.equals(quantity, product.quantity) && Objects.equals(wholesaleProduct, product.wholesaleProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, quantity, wholesaleProduct);
    }
}
