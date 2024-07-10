package main.java.ru.clevertec.check.repository.api;


import java.util.List;

//E - entity Product or DiscountCard
public interface ICSVRepository <E> {

    List<E> getContentFromCSV(List<String> content);

}
