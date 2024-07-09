package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.entity.Product;
import main.java.ru.clevertec.check.entity.dto.Check;
import main.java.ru.clevertec.check.entity.dto.CheckProduct;
import main.java.ru.clevertec.check.service.api.ICheckService;
import main.java.ru.clevertec.check.util.FileUtil;
import main.java.ru.clevertec.check.validator.Validator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static main.java.ru.clevertec.check.util.Constant.*;


public class CheckService implements ICheckService {

    private final Validator validator = new Validator();
    private final DiscountCardService discountCardService = new DiscountCardService();

    @Override
    public Check generateCheck(List<Product> list, DiscountCard discountCard) {

        List<CheckProduct> checkProductList = new ArrayList<>();

        double totalPrice = 0;
        double totalDiscount = 0;
        double totalPriceWitDiscount = 0;

        for (Product p : list) {

            var id = p.getId();
            var quantityForSale = p.getQuantity();

            try {
                validator.checkQuantity(quantityForSale, p);

                double total = discountCardService.getTotal(quantityForSale, p.getPrice());

                double discount;
                var isWholesale = validator.checkWholesaleProduct(quantityForSale, p);
                if (isWholesale) {
                    discount = discountCardService.applyWholesaleDiscount(total);
                } else {
                    discount = discountCardService.applyDiscount(discountCard, total);
                }

                CheckProduct checkProduct = buildCheckProduct(p, id, total, discount);

                checkProductList.add(checkProduct);

                totalPrice += p.getPrice() * quantityForSale;
                totalDiscount += discount;
                totalPriceWitDiscount = totalPrice - totalDiscount;


            } catch (IllegalArgumentException | NoSuchElementException e) {
                FileUtil.saveError(ERROR_CSV, e.getMessage());
                System.out.println(e.getMessage());
            }

        }
        return new Check(checkProductList, totalPrice, totalDiscount, totalPriceWitDiscount);
    }

    @Override
    public void saveCheckToCSVFile(String fileName, Check check, DiscountCard discountCard) {

        LocalDateTime now = LocalDateTime.now();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.append("Date;Time\n");
            bw.append(formatDate(now)).append(";").append(formatTime(now)).append(";\n");
            bw.append("\nQTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n");

            for (CheckProduct p : check.getCheckProductList()) {
                bw
                        .append(String.valueOf(p.getQuantity())).append(SEMICOLON)
                        .append(p.getDescription()).append(SEMICOLON)
                        .append(formatDoubleToString(p.getPrice())).append(SEMICOLON)
                        .append(formatDoubleToString(p.getDiscount())).append(SEMICOLON)
                        .append(formatDoubleToString(p.getTotal())).append("\n");
            }

            if (discountCard != null) {
                bw
                        .append("\nDISCOUNT CARD;DISCOUNT PERCENTAGE\n")
                        .append((String.valueOf(discountCard.getNumber()))).append(SEMICOLON)
                        .append(String.valueOf(discountCard.getDiscountAmountInPercent())).append(PERCENT_SIGN +"\n");
            }

            bw.append("\nTOTAL PRICE;TOTAL DISCOUNT; TOTAL WITH DISCOUNT\n")
                    .append(formatDoubleToString(check.getTotalPrice())).append(SEMICOLON)
                    .append(formatDoubleToString(check.getTotalDiscount())).append(SEMICOLON)
                    .append(formatDoubleToString(check.getTotalPriceWitDiscount()));

        } catch (IOException e) {
            FileUtil.saveError(ERROR_CSV, e.getMessage());
            System.out.println(e.getMessage());

        }

    }

    private String formatDoubleToString(double value) {
        return String.format(PRICE_FORMAT, value);
    }

    private String formatDate(LocalDateTime value) {
        var dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return dateFormatter.format(value);
    }

    private String formatTime(LocalDateTime value) {
        var timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        return timeFormatter.format(value);
    }

    private CheckProduct buildCheckProduct(Product p, Integer id, double total, double discount) {
        CheckProduct checkProduct = new CheckProduct();
        checkProduct.setId(id);
        checkProduct.setQuantity(p.getQuantity());
        checkProduct.setDescription(p.getDescription());
        checkProduct.setPrice(p.getPrice());
        checkProduct.setDiscount(discount);
        checkProduct.setTotal(total);
        return checkProduct;
    }


}

