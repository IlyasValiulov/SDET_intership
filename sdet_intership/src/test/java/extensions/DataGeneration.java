package extensions;

import io.qameta.allure.Step;

import java.util.Random;

public class DataGeneration {
    @Step("Генерация посткода")
    public static String generatePostCode() {
        StringBuilder code = new StringBuilder();
        int len = 10;
        Random rnd = new Random();
        for (int i = 0; i < len; i++) {
            code.append(rnd.nextInt(0, 10));
        }
        return code.toString();
    }

    @Step("Генерация имени на основе посткода: {postCode}")
    public static String generateName(String postCode) {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < postCode.length(); i += 2) {
            int num = Integer.parseInt(String.valueOf(postCode.charAt(i)) + String.valueOf(postCode.charAt(i+1)));
            name.append((char)(num % 26 + 'a'));
        }
        return name.toString();
    }
}
