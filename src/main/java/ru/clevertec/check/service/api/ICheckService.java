package main.java.ru.clevertec.check.service.api;

import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.entity.Product;
import main.java.ru.clevertec.check.entity.dto.Check;

import java.util.List;

public interface ICheckService {

    Check generateCheck(List<Product> list, DiscountCard discountCard);

   void saveCheckToCSVFile(String fileName, Check check, DiscountCard discountCard);
}
