package main.java.ru.clevertec.check.repository;

import main.java.ru.clevertec.check.entity.Product;
import main.java.ru.clevertec.check.repository.api.ICSVRepository;


import java.util.ArrayList;
import java.util.List;

import static main.java.ru.clevertec.check.util.Constant.*;

public class ProductRepository implements ICSVRepository<Product> {

    @Override
    public List<Product> getContentFromCSV(List<String> content) {
        List<Product> products = new ArrayList<>();
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
                Product product = buildProduct(products, columnValues);
                products.add(product);
            }
        }

        return products;
    }


    private Product buildProduct(List<Product> products, String[] columnValues) {

        Product product = new Product();

        product.setId(Integer.parseInt(columnValues[0]));

        product.setDescription(columnValues[1]);

        var priceValueComma = columnValues[2];
        var modifyPriceValueWithPoint = priceValueComma.replaceAll(COMMA_REGEX, POINT_REGEX);
        product.setPrice(Double.parseDouble(modifyPriceValueWithPoint));

        var quantityInStockValue = columnValues[3];
        product.setQuantity(Integer.parseInt(quantityInStockValue));

        var wholesaleProductValue = columnValues[4];
        product.setWholesaleProduct(Boolean.parseBoolean(wholesaleProductValue));

        products.add(product);
        return product;
    }
}
