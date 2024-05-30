package demoblaze.tasks;

import demoblaze.model.datatables.SignupDataTable;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Ensure;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;

public class ValidateSignup implements Task {

    private final SignupDataTable data;

    public ValidateSignup(SignupDataTable data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.should(
                seeThatResponse(String.format("The response code is %s ", data.getCodeResponse()),
                        response -> response
                                .statusCode(Integer.parseInt(data.getCodeResponse())))
        );

        if (data.getError().equals("NA")) {

            String bodyResponse = SerenityRest.lastResponse().getBody().asString().trim();
            actor.should(
                    seeThat("The body response is an empty string",
                            actual -> bodyResponse,
                            equalTo("\"\""))
            );
        } else {
            actor.should(
                    seeThatResponse("The body response has the correct error message",
                            response -> response
                                    .body("errorMessage", equalTo(data.getError()))
                    )
            );
        }
    }

    public static ValidateSignup response(SignupDataTable data) {
        return Instrumented.instanceOf(ValidateSignup.class).withProperties(data);
    }
}
