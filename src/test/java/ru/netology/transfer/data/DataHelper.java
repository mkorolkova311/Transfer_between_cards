package ru.netology.transfer.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String Login;
        private String Password;
    }

    public static AuthInfo getAuthInfo() {return new AuthInfo("vasya", "qwerty123");}
    public static AuthInfo getOtherAuthInfo() {return new AuthInfo("petya", "qwerty123");}

    @Value
    public static class VerificationCode {
        private String Code;
    }

        public static VerificationCode getVerificationCodeFor(AuthInfo AuthInfo) {return new VerificationCode("12345"); }
}
