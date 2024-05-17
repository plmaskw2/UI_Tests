package org.serenity.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class OpenHomePage implements Task {

    private final String url;

    public OpenHomePage(String url) {
        this.url = url;
    }

    public static OpenHomePage at(String url) {
        return new OpenHomePage(url);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(url));
    }
}
