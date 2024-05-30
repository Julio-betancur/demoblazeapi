package demoblaze.tasks;

import demoblaze.model.datatables.LoginDataTable;
import demoblaze.model.json.request.generator.BaseHeadersGenerator;
import demoblaze.model.json.request.generator.LoginBodyGenerator;
import demoblaze.model.json.request.model.LoginBody;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.RestQueryFunction;
import net.thucydides.model.util.EnvironmentVariables;

public class SendLogin implements Task {
    @Managed
    private EnvironmentVariables environmentVariables;
    private final LoginDataTable data;

    public SendLogin(LoginDataTable data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        LoginBodyGenerator loginBodyGenerator = new LoginBodyGenerator(data);
        LoginBody body = loginBodyGenerator.generate();
        BaseHeadersGenerator headers = new BaseHeadersGenerator();

        String endpoint = this.environmentVariables.optionalProperty("endpoints.blaze.login").orElse("/");

        RestQueryFunction restConfiguration = requestSpecification -> {
            requestSpecification.headers(headers.generateBaseHeaders());
            requestSpecification.body(body);
            requestSpecification.relaxedHTTPSValidation();
            return requestSpecification;
        };

        actor.attemptsTo(
                Post.to(endpoint).with(restConfiguration)
        );
    }

    public static SendLogin withInformation(LoginDataTable data) {
        return Instrumented.instanceOf(SendLogin.class).withProperties(data);
    }
}
