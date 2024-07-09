package main.java.ru.clevertec.check.controller;

import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.service.CheckService;
import main.java.ru.clevertec.check.service.DiscountCardService;
import main.java.ru.clevertec.check.service.ProductService;
import main.java.ru.clevertec.check.util.FileUtil;
import main.java.ru.clevertec.check.validator.Validator;

import java.util.*;

import static main.java.ru.clevertec.check.util.Constant.*;

public class CheckController {

    ProductService productService = new ProductService();
    CheckService checkService = new CheckService();
    DiscountCardService discountCardService = new DiscountCardService();

    Validator validator = new Validator();

    public void generateCheckWithArgs(String[] args) {

        HashMap<Integer, Integer> map = new HashMap<>();
        String discountCardNumber = null;
        double balanceDebitCard = 0d;
        int idProduct;
        int quantity;

        for (String arg : args) {

            if (arg.matches("\\d+-\\d+")) {
                var IdAndQuantity = arg.split(HYPHEN_REGEX);
                idProduct = Integer.parseInt(IdAndQuantity[0]);
                quantity = Integer.parseInt(IdAndQuantity[1]);
                map.merge(idProduct, quantity, Integer::sum);
            }
            if (arg.startsWith("discountCard=")) {
                discountCardNumber = arg.split(EQUAL_REGEX)[1];
            }
            if (arg.startsWith("balanceDebitCard=")) {
                balanceDebitCard = Double.parseDouble(arg.split(EQUAL_REGEX)[1]);
            }

        }

        //read product.csv
        var stock = productService.parseCSVtoListProduct(PRODUCTS_CSV);
        //get products for sale
        var productsForSale = productService.getProductForSale(map, stock);
        //read discountCard.csv
        var listDiscountCards = discountCardService.parseCSVtoListDiscountCards(DISCOUNT_CARDS_CSV);
        //get discount card
        DiscountCard discountCard;
        if (discountCardNumber != null) {
            var discountCardNumberModify = Integer.parseInt(discountCardNumber);
            discountCard = discountCardService.findByNumber(discountCardNumberModify, listDiscountCards);
        } else {
            discountCard = new DiscountCard(WITHOUT_DISCOUNT);
        }
        //generate check
        var check = checkService.generateCheck(productsForSale, discountCard);
        //check balance
        try {
            var payment = check.getTotalPriceWitDiscount();
            validator.checkBalance(payment, balanceDebitCard);
        } catch (IllegalArgumentException e) {
            FileUtil.saveError(ERROR_CSV, e.getMessage());
            System.out.println("\n" + e.getMessage());
        }
        //save check to file
        checkService.saveCheckToCSVFile(RESULT_CSV, check, discountCard);
        //print check to console
        var result = FileUtil.readContentFromCsvFile(RESULT_CSV);
        for (String s : result) {
            System.out.println(s);
        }
    }


}
