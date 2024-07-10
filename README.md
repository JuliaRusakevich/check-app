---
Консольное приложение по формированию чека.

Стек:  Java 21

### Как запустить:

1. **Компиляция всех файлов:**

   ```bash
    javac -d compile -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java
   ```

2. **Запуск приложения и передача аргументов:**

   ```bash
   java -cp compile main.java.ru.clevertec.check.CheckRunner id-quantity discountCard=xxxx balanceDebitCard=xxxx
   ```

Заменить `id-quantity`, `discountCard=xxxx`, `balanceDebitCard=xxxx` на свои аргументы как в примере ниже:

   ```bash
   java -cp compile main.java.ru.clevertec.check.CheckRunner 1-2 2-3 3-6 discountCard=5555 balanceDebitCard=100
   ```

3. **Результат**

   При успешном выполнении выполнении команды формирется файл **result.csv** (пример в ветке).

### Исключения

В ходе выполения команды могут возникнуть следующие исключительные ситуации, которые записываются в файл **error.csv** (
пример в ветке):

- **BAD REQUEST**: Неверные входные данные, недостаточное количество товаров.
- **NOT ENOUGH MONEY**: Недостаток средств (баланс меньше, чем сумма в чека).

---
