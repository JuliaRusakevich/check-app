package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.controller.CheckController;

/*
compile javac -d compile -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java

1 OK
start java -cp compile main.java.ru.clevertec.check.CheckRunner 1-2 2-3 3-6 discountCard=5555 balanceDebitCard=100 pathToFile=./src/main/resources/products.csv saveToFile=./result.csv
2 без pathToFile=
start java -cp compile main.java.ru.clevertec.check.CheckRunner 1-2 2-3 3-6 discountCard=5555 balanceDebitCard=100 saveToFile=./result.csv
3 без saveToFile=
start java -cp compile main.java.ru.clevertec.check.CheckRunner 1-2 2-3 3-6 discountCard=5555 balanceDebitCard=100 pathToFile=./src/main/resources/products.csv
 */

public class CheckRunner {
    public static void main(String[] args) {

        CheckController controller = new CheckController();
        controller.generateCheckWithArgs(args);


    }
}