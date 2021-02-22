package ru.netology.transfer.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.netology.transfer.data.DataHelper;

import static com.codeborne.selenide.Selenide.page;

public class PageLoginV3 {
    @FindBy(css="[data-test-id=login] input")private SelenideElement loginField;
    @FindBy(css="[data-test-id=password] input")private SelenideElement passwordField;
    @FindBy(css="[data-test-id=action-login]")private SelenideElement loginButton;
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
    return page(VerificationPage.class);
    }
}
