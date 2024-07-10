Консольное приложение по формированию чека.

Стек:  Java 21

### Как запустить:

1. **Компиляция всех файлов:**

   ```bash
    javac -d compile -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java
   ```

2. **Запуск приложения и передача аргументов:**

   ```bash
   java -cp compile main.java.ru.clevertec.check.CheckRunner id-quantity discountCard=xxxx balanceDebitCard=xxxx pathToFile=./src/main/resources/products.csv saveToFile=./result.csv
   ```

Заменить `id-quantity`, `discountCard=xxxx`, `balanceDebitCard=xxxx`, pathToFile, saveToFile на свои аргументы как в примере ниже:

   ```bash
   java -cp compile main.java.ru.clevertec.check.CheckRunner 1-2 2-3 3-6 discountCard=5555 balanceDebitCard=100 pathToFile=./src/main/resources/products.csv saveToFile=./result.csv
   ```

3. **Результат**

   При успешном выполнении выполнении команды формирется файл **result.csv** с выводом чека (пример в ветке).

### Исключения

В ходе выполения команды могут возникнуть следующие исключительные ситуации, которые записываются в файлы (
пример в ветке):

- **BAD REQUEST**: Неверные входные данные, недостаточное количество товаров.
- **NOT ENOUGH MONEY**: Недостаток средств (баланс меньше, чем сумма в чека).
- **INTERNAL SERVER ERROR**: Любые другие исключительные ситуациию
