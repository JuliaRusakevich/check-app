package main.java.ru.clevertec.check.entity.dto;

import java.util.List;

public class Check {

    private List<CheckProduct> checkProductList;
    private double totalPrice;
    private double totalDiscount;
    private double totalPriceWitDiscount;


    public Check() {
    }

    public Check(List<CheckProduct> checkProductList, double totalPrice, double totalDiscount, double totalPriceWitDiscount) {
        this.checkProductList = checkProductList;
        this.totalPrice = totalPrice;
        this.totalDiscount = totalDiscount;
        this.totalPriceWitDiscount = totalPriceWitDiscount;
    }


    public List<CheckProduct> getCheckProductList() {
        return checkProductList;
    }

    public void setCheckProductList(List<CheckProduct> checkProductList) {
        this.checkProductList = checkProductList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTotalPriceWitDiscount() {
        return totalPriceWitDiscount;
    }

    public void setTotalPriceWitDiscount(double totalPriceWitDiscount) {
        this.totalPriceWitDiscount = totalPriceWitDiscount;
    }

    @Override
    public String toString() {
        return "Check{" +
               "checkProductList=" + checkProductList +
               ", totalPrice=" + totalPrice +
               ", totalDiscount=" + totalDiscount +
               ", totalPriceWitDiscount=" + totalPriceWitDiscount +
               '}';
    }

}
