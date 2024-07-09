package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.entity.Product;
import main.java.ru.clevertec.check.repository.ProductRepository;
import main.java.ru.clevertec.check.service.api.IProductService;
import main.java.ru.clevertec.check.util.FileUtil;
import main.java.ru.clevertec.check.validator.Validator;

import java.util.*;

import static main.java.ru.clevertec.check.util.Constant.ERROR_CSV;

public class ProductService implements IProductService {

    private final ProductRepository productRepository = new ProductRepository();

    private final Validator v = new Validator();

    @Override
    public List<Product> parseCSVtoListProduct(String fileName) {
        var contentFromCSV = FileUtil.readContentFromCsvFile(fileName);
        return productRepository.getContentFromCSV(contentFromCSV);
    }

    @Override
    public List<Product> getProductForSale(HashMap<Integer, Integer> map, List<Product> list) {

        List<Product> productsForSale = new ArrayList<>();

        for (Map.Entry<Integer, Integer> m : map.entrySet()) {

            var id = m.getKey();
            var quantityForSale = m.getValue();

            try {
                var product = getProductByIdFromList(id, list);
                v.checkQuantity(quantityForSale, product);
                product.setQuantity(quantityForSale);
                productsForSale.add(product);
            } catch (IllegalArgumentException | NoSuchElementException e) {
                FileUtil.saveError(ERROR_CSV, e.getMessage());
                System.out.println(e.getMessage());
            }

        }
        return productsForSale;
    }

    @Override
    public Product getProductByIdFromList(Integer id, List<Product> stock) {
        return stock.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .toList()
                .stream().findFirst().orElseThrow(() -> new NoSuchElementException("BAD REQUEST " + "No element in stock with id - " + id));
    }
}
