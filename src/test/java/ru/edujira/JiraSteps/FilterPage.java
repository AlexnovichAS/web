package ru.edujira.JiraSteps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;
import static ru.edujira.PageElements.FilterPageElements.*;
import static ru.edujira.PageElements.TaskPageElements.taskDetails;

public class FilterPage {

    @Step("Проверка значения: '{result}', у свойства '{name}' в деталях задачи")
    public static void checkTaskDetailsInFilter(String name, String result) {
        int i = 0;
        while (i <= 15) {
            if (taskDetails(name).getText().toLowerCase().trim().equals(result)) {
                break;
            } else {
                sleep(100);
                i++;
            }
        }
        String detailsResult = taskDetails(name)
                .should(exist, Duration.ofSeconds(10))
                .getText().toLowerCase().trim();
        Assertions.assertEquals(result.toLowerCase(), detailsResult,
                "Ошибка, в поле задачи: " + name + " указано: " + detailsResult);
    }

    @Step("Поиск задачи: тип '{type}' и название '{name}'")
    public static void findInFilter(String type, String name) {
        buttonFilter.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        buttonType.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        SelenideElement resultType = selectionTaskType(type).should(exist).shouldBe(visible, Duration.ofSeconds(10));
        resultType.click();
        resultType.pressEnter();
        fieldInput.should(exist).shouldBe(visible, Duration.ofSeconds(10)).sendKeys(name);
        buttonSearch.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        taskBox(name).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Перевод задачи в статус: {name}")
    public static void choiceStatus(String name) {
        buttonStatus(name).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Клик по кнопке: '{nameProcess}' и изменение статуса задачи на: '{name}'")
    public static void choiceBusinessProcess(String nameProcess, String name) {
        buttonStatus(nameProcess).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        buttonBusinessProcess(nameProcess, name).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Клик по значению: '{nameOptions}', в выпадающем меню функции: 'Экспорт'")
    public static void goForPrint(String nameOptions) {
        buttonSaved.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        chooseButtonOptions(nameOptions).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Проверка, что задача: '{nameTask}' найдена")
    public static void checkNameTaskInFilter(String nameTask) {
        String header = headerH1.getText();
        Assertions.assertEquals(nameTask, header, "Ошибка, найдена задача:" + header + "ожидалась: " + nameTask);
    }
}
