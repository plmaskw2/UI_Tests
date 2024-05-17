package org.serenity.tests;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.serenity.tasks.OpenHomePage;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;

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
    public void shouldBeAbleToOpenHomePage() {
        givenThat(john).wasAbleTo(OpenHomePage.at("http://example.com"));

        // Replace with appropriate question or check
        then(john).should(seeThat(WebElementQuestion.the("//h1"), containsText("Example Domain")));

        driver.quit();
    }
}
