package main.java.ru.clevertec.check.entity;

import java.util.Objects;

public class DiscountCard {

    private int id;
    private int number;
    private int discountAmountInPercent;


    public DiscountCard() {
    }

    public DiscountCard(int discountAmountInPercent) {
        this.discountAmountInPercent = discountAmountInPercent;
    }


    public DiscountCard(int number, int discountAmountInPercent) {
        this.number = number;
        this.discountAmountInPercent = discountAmountInPercent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDiscountAmountInPercent() {
        return discountAmountInPercent;
    }

    public void setDiscountAmountInPercent(int discountAmountInPercent) {
        this.discountAmountInPercent = discountAmountInPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return id == that.id && number == that.number && discountAmountInPercent == that.discountAmountInPercent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, discountAmountInPercent);
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
               "id=" + id +
               ", number=" + number +
               ", discountAmountInPercent=" + discountAmountInPercent +
               '}';
    }
}
