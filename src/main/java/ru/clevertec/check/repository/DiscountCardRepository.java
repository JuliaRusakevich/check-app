package main.java.ru.clevertec.check.repository;

import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.repository.api.ICSVRepository;

import java.util.ArrayList;
import java.util.List;

import static main.java.ru.clevertec.check.util.Constant.SEMICOLON;

public class DiscountCardRepository implements ICSVRepository<DiscountCard> {
    @Override
    public List<DiscountCard> getContentFromCSV(List<String> content) {

        List<DiscountCard> discountCards = new ArrayList<>();
        boolean isHeader = true;
        int countColumns = 0;
        for (String line : content) {
            if (isHeader) {
                var headerColumns = line.split(SEMICOLON);
                countColumns = headerColumns.length;
                isHeader = false;
                continue;
            }
            String[] columnValues = line.split(SEMICOLON);
            if (columnValues.length == countColumns) {
                DiscountCard discountCard = buildDiscountCard(discountCards, columnValues);
                discountCards.add(discountCard);
            }
        }

        return discountCards;
    }

    private DiscountCard buildDiscountCard(List<DiscountCard> discountCards, String[] columnValues) {

        DiscountCard discountCard = new DiscountCard();

        discountCard.setId(Integer.parseInt(columnValues[0]));
        discountCard.setNumber(Integer.parseInt(columnValues[1]));
        discountCard.setDiscountAmountInPercent(Integer.parseInt(columnValues[2]));

        discountCards.add(discountCard);

        return discountCard;
    }
}
