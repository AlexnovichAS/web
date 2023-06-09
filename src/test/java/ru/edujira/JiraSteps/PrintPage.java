package ru.edujira.JiraSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static ru.edujira.PageElements.PrintPageElements.resultCount;

public class PrintPage {

    @Step("Проверка общего количества задач")
    public static void checkInformationAboutTasks(String count) {
        String infoTasks = resultCount.should(exist).shouldBe(visible, Duration.ofSeconds(10)).getText();
        Assertions.assertEquals(count, infoTasks, "Ошибка, не соответствие общего количества. На странице фильтров: " + count
                + " ,а на странице на печать: " + infoTasks);
    }
}
