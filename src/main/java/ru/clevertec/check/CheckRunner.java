package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.controller.CheckController;


public class CheckRunner {
    public static void main(String[] args) {

        CheckController controller = new CheckController();
        controller.generateCheckWithArgs(args);


    }
}