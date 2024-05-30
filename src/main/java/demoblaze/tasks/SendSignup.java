package demoblaze.tasks;

import demoblaze.model.datatables.SignupDataTable;
import demoblaze.model.json.request.generator.BaseHeadersGenerator;
import demoblaze.model.json.request.generator.SignupBodyGenerator;
import demoblaze.model.json.request.model.SignupBody;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.RestQueryFunction;
import net.thucydides.model.util.EnvironmentVariables;

public class SendSignup implements Task {
    @Managed
    private EnvironmentVariables environmentVariables;

    private final SignupDataTable data;

    public SendSignup(SignupDataTable data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SignupBodyGenerator signupBodyGenerator = new SignupBodyGenerator(data);
        SignupBody body = signupBodyGenerator.generate();
        BaseHeadersGenerator headers = new BaseHeadersGenerator();

        String endpoint = this.environmentVariables.optionalProperty("endpoints.blaze.sign_up").orElse("/");

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

    public static SendSignup withInformation(SignupDataTable data){
        return Instrumented.instanceOf(SendSignup.class).withProperties(data);
    }
}
