package ru.netology.transfer.page;

import ru.netology.transfer.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.withText;

public class PageLogin {
    public VerificationPage ValidLogin(DataHelper.AuthInfo info){
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }
    public void NotValidLogin(DataHelper.AuthInfo info){
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Ошибка"));

    }
}
