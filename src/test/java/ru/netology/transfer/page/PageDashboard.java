package ru.netology.transfer.page;


import com.codeborne.selenide.ElementsCollection;
import lombok.val;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.withText;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PageDashboard {
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public PageDashboard(){}

    public double getFirstCardBalance(){
        val text = cards.first().text();
        return extractBalance(text);
    }

    public double getCardBalance(String id){
        val text = cards.findBy(text(id)).text();
        return extractBalance(text);
    }
    private double extractBalance(String text) {
        val start = text.indexOf(this.balanceStart);
        val finish = text.indexOf(this.balanceFinish);
        val value = text.substring(start + this.balanceStart.length(), finish);
        return Double.parseDouble(value);
    }
    public void additionToCard(String card_id_to, String card_id_from, double addition){
        String sum;
        if(addition % 1 == 0) {
            sum = Integer.toString((int)addition);
        }else {
            sum = Double.toString(addition);
        }
        cards.findBy(text(card_id_to)).find(".button").click();
        $(withText("Пополнение баланса"));
        $("[data-test-id=amount] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=amount] input").setValue(sum);
        $("[data-test-id=from] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=from] input").setValue("555900000000" + card_id_from);
        $("[data-test-id=action-transfer]").click();
        $(withText("Ваши карты"));

    }
    public void resetCardsBalances(String card1, String card2){
        val bal1 = getCardBalance(card1);
        val bal2 = getCardBalance(card2);
        if ( Double.compare(bal1, bal2) < 0 ) {
            this.additionToCard(card1, card2, (bal2-bal1)/2);
        }else {
            this.additionToCard(card2, card1, (bal1-bal2)/2);
        }
    }


}
