package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.repository.DiscountCardRepository;
import main.java.ru.clevertec.check.service.api.IDiscountCardService;
import main.java.ru.clevertec.check.util.FileUtil;

import java.util.List;
import java.util.Objects;

import static main.java.ru.clevertec.check.util.Constant.*;

public class DiscountCardService implements IDiscountCardService {

    private final DiscountCardRepository discountCardRepository = new DiscountCardRepository();

    public List<DiscountCard> parseCSVtoListDiscountCards(String fileName) {
        var contentFromCSV = FileUtil.readContentFromCsvFile(fileName);
        return discountCardRepository.getContentFromCSV(contentFromCSV);
    }

    @Override
    public DiscountCard findByNumber(int number, List<DiscountCard> discountCards) {
        return discountCards.stream()
                .filter(discountCard -> Objects.equals(discountCard.getNumber(), number))
                .findFirst()
                .orElse(new DiscountCard(number, CUSTOM_DISCOUNT_AMOUNT_IN_PERCENT));

    }

    @Override
    public double getTotal(int quantity, double price) {
        return quantity * price;
    }

    @Override
    public double applyDiscount(DiscountCard discountCard, double total) {
        return total * discountCard.getDiscountAmountInPercent() / 100;
    }

    @Override
    public double applyWholesaleDiscount(double total) {
        return total * WHOLESALE_DISCOUNT_IN_PERCENT / 100;
    }

}
