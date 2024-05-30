package demoblaze.tasks;

import demoblaze.model.datatables.LoginDataTable;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ValidateLogin implements Task {

    private final LoginDataTable data;

    public ValidateLogin(LoginDataTable data) {
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
            actor.should(
                    seeThatResponse("The body response has the correct information",
                            response -> response.body(containsString("Auth_token: " + data.getToken()))
                    )
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

    public static ValidateLogin response(LoginDataTable data) {
        return Instrumented.instanceOf(ValidateLogin.class).withProperties(data);
    }
}
