package main.java.ru.clevertec.check.controller;

import main.java.ru.clevertec.check.exception.CustomException;
import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.entity.Product;
import main.java.ru.clevertec.check.service.CheckService;
import main.java.ru.clevertec.check.service.DiscountCardService;
import main.java.ru.clevertec.check.service.ProductService;
import main.java.ru.clevertec.check.util.FileUtil;
import main.java.ru.clevertec.check.validator.Validator;

import java.io.IOException;
import java.util.*;

import static main.java.ru.clevertec.check.util.Constant.*;

public class CheckController {

    ProductService productService = new ProductService();
    CheckService checkService = new CheckService();
    DiscountCardService discountCardService = new DiscountCardService();
    Validator validator = new Validator();

    public void generateCheckWithArgs(String[] args) throws CustomException {

        HashMap<Integer, Integer> map = new HashMap<>();
        String discountCardNumber = null;
        double balanceDebitCard = 0d;
        int idProduct;
        int quantity;
        String pathToFile = null;
        String saveToFile = null;

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

            if (arg.startsWith("pathToFile=")) {
                pathToFile = arg.split(EQUAL_REGEX)[1];
            }
            if (arg.startsWith("saveToFile=")) {
                saveToFile = arg.split(EQUAL_REGEX)[1];
            }
        }


        try {
            validator.checkStringArg(saveToFile);
        } catch (IllegalArgumentException e) {
            FileUtil.saveError(ERROR_RESULT_CSV, e.getMessage());

        }

        List<Product> stock;
        if (validator.checkStringArg(pathToFile)) {
            //read scv from pathToFile
            stock = productService.parseCSVtoListProduct(pathToFile);
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
            //save check by way from arg saveToFile
            if (validator.checkStringArg(saveToFile)) {
                checkService.saveCheckToCSVFile(saveToFile, check, discountCard);
            } else {

                System.out.println("Missed arg saveToFile");
                FileUtil.saveError(RESULT_CSV, BAD_REQUEST);
            }

        } else {
            System.out.println("Missed arg pathToFile");
            FileUtil.saveError(ERROR_RESULT_CSV, BAD_REQUEST);
        }


        //print check to console
        List<String> result = null;
        try {
            result = FileUtil.readContentFromCsvFile(RESULT_CSV);
        } catch (IOException e) {
            FileUtil.saveError(ERROR_CSV, e.getMessage());
            System.out.println(e.getMessage());
        }
        assert result != null;
        for (String s : result) {
            System.out.println(s);
        }
    }


}
