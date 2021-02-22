package ru.netology.transfer.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.transfer.data.DataHelper;
import ru.netology.transfer.page.PageLogin;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;


public class MoneyTransferTest {
    private final String card1 = "0001";
    private final String card2 = "0002";
    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:7777/");
        val loginPage = new PageLogin();
        val authInfo = DataHelper.getAuthInfo();
        val VerificationPage = loginPage.ValidLogin(authInfo);
        val VerificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashBoard = VerificationPage.validVerify(VerificationCode);
        val balance1 = dashBoard.getCardBalance(card1);
        val balance2 = dashBoard.getCardBalance(card2);
        double addition = 5000.50;
        dashBoard.additionToCard(card2, card1, addition);
        assertEquals(balance1-addition, dashBoard.getCardBalance(card1),0);
        assertEquals(balance2+addition, dashBoard.getCardBalance(card2),0);
        dashBoard.resetCardsBalances(card1, card2);

    }

    @Test
    void shouldTransferMoneyBetweenOneCard() {
        open("http://localhost:7777/");
        val loginPage = new PageLogin();
        val authInfo = DataHelper.getAuthInfo();
        val VerificationPage = loginPage.ValidLogin(authInfo);
        val VerificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashBoard = VerificationPage.validVerify(VerificationCode);
        double addition = 100.0;
        double balance1 = dashBoard.getCardBalance(card1);
        dashBoard.additionToCard(card1, card1, addition);
        assertEquals(balance1,dashBoard.getCardBalance(card1),0);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsMoreLimit() {
        open("http://localhost:7777/");
        val loginPage = new PageLogin();
        val authInfo = DataHelper.getAuthInfo();
        val VerificationPage = loginPage.ValidLogin(authInfo);
        val VerificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashBoard = VerificationPage.validVerify(VerificationCode);
        double balance1 = dashBoard.getCardBalance(card1);
        double balance2 = dashBoard.getCardBalance(card2);
        double addition = balance2 + 15000.0;
        dashBoard.additionToCard(card1, card2, addition);
        assertEquals(balance1+addition,dashBoard.getCardBalance(card1),0);
        assertEquals(balance2-addition,dashBoard.getCardBalance(card2),0);
        dashBoard.resetCardsBalances(card1, card2);
    }
    @Test
    void shouldNotValidLogin() {
        open("http://localhost:7777/");
        val loginPage = new PageLogin();
        val authInfo = DataHelper.getOtherAuthInfo();
        loginPage.NotValidLogin(authInfo);

    }
}


