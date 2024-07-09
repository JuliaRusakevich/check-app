package main.java.ru.clevertec.check.validator;

import main.java.ru.clevertec.check.entity.Product;
import static main.java.ru.clevertec.check.util.Constant.QUANTITY_FOR_WHOLESALE;


public class Validator {

    public void checkQuantity(int quantity, Product p) {
        if (quantity > p.getQuantity()) {
            throw new IllegalArgumentException
                    ("BAD REQUEST " + " Quantity in stock " + p.getQuantity() + " not enough for sale, quantity " + quantity);
        }
    }

    public boolean checkWholesaleProduct(int quantity, Product product) {
        return quantity >= QUANTITY_FOR_WHOLESALE && product.getWholesaleProduct();
    }

    public void checkBalance(double payment, double balanceOnDebitCard) {
        if (payment > balanceOnDebitCard) {
            throw new IllegalArgumentException("NOT ENOUGH MONEY");
        }
    }


}
