package main.java.ru.clevertec.check.service.api;

import main.java.ru.clevertec.check.entity.DiscountCard;

import java.util.List;

public interface IDiscountCardService {

    List<DiscountCard> parseCSVtoListDiscountCards(String fileName);

    DiscountCard findByNumber(int number, List<DiscountCard> discountCards);

    double getTotal(int quantity, double price);

    double applyDiscount(DiscountCard discountCard, double total);

    double applyWholesaleDiscount(double total);

}
