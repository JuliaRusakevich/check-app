package main.java.ru.clevertec.check.service.api;

import main.java.ru.clevertec.check.entity.Product;

import java.util.HashMap;
import java.util.List;

public interface IProductService {

    List<Product> parseCSVtoListProduct(String fileName);

    List<Product> getProductForSale(HashMap<Integer, Integer> map, List<Product> list);

    Product getProductByIdFromList(Integer id, List<Product> stock);
}
