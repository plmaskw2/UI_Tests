package org.serenity.tests;

import io.cucumber.java.bs.A;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.serenity.tasks.OpenHomePage;

import java.util.function.Predicate;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;

@Epic("Sample Epic")
@Feature("Sample Feature")
@ExtendWith(SerenityJUnit5Extension.class)
public class SampleTest {

    private Actor john;
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        john = Actor.named("John").whoCan(BrowseTheWeb.with(driver));
    }

    @Test
    @Description("Sample Test")
    public void shouldBeAbleToOpenHomePage() {
        givenThat(john).wasAbleTo(OpenHomePage.at("http://example.com"));

        // Replace with appropriate question or check
        then(john).should(seeThat(WebElementQuestion.the("//h1"), s -> s.getText().equals("Example Domain")));
    }
}
