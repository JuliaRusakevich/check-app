package main.java.ru.clevertec.check.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static main.java.ru.clevertec.check.util.Constant.ERROR_CSV;

public class FileUtil {

    private FileUtil() {
    }

    public static void saveError(String fileName, String errorMessage) {
        createNewFile(fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.append("ERROR\n");
            bw.append(errorMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void createNewFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                boolean isNewFileCreated = file.createNewFile();
                if (isNewFileCreated) {
                    System.out.printf("File with name %s was created", fileName);
                    System.out.println();
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.printf("File with name %s exist", fileName);
            System.out.println();

        }
    }

    public static List<String> readContentFromCsvFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            FileUtil.saveError(ERROR_CSV, e.getMessage());
            System.out.println(e.getMessage());
        }
        return lines;
    }


    public static boolean isFileExist(File file) {
        if (!file.exists()) {
            try {
                throw new FileNotFoundException("File with name " + file.getName() + " does not exist");
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return true;
    }


}
