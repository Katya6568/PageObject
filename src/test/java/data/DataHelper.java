package data;

import lombok.Value;

import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String number;

        private int balance;
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559 0000 0000 0001", 10000);
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002", 10000);
    }

    public static String generateInvalidAmount(int balance) {
        Random random = new Random();
        int amount = balance + Math.abs(random.nextInt());
        String invalidAmount = Integer.toString(amount);
        return invalidAmount;

    }

    public static String generateValidAmount(int balance) {
        final int max = balance;
        int amount = (int) (Math.random() * ++balance);
        String validAmount = Integer.toString(amount);
        return validAmount;

    }


}