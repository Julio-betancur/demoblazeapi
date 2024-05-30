package demoblaze.stepdefinitions;

import demoblaze.model.datatables.LoginDataTable;
import demoblaze.tasks.SendLogin;
import demoblaze.tasks.ValidateLogin;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

import java.util.Map;

public class LoginStepDefinition {
    @DataTableType
    public LoginDataTable convert(Map<String, String> table) {
        return new ObjectMapper().convertValue(table, LoginDataTable.class);
    }

    @Managed
    private EnvironmentVariables env;

    @Managed
    private Actor actor;

    @Before(order = 0)
    public void setTheStage() {
        String theRestApiBaseUrl = EnvironmentSpecificConfiguration.from(this.env).getProperty("restapi.baseurl.blaze");
        OnStage.setTheStage(new OnlineCast());
        actor = OnStage.theActorCalled("User").whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @When("I send the user credentials")
    public void iSendTheUserCredentials(LoginDataTable data) {
        actor.attemptsTo(
                SendLogin.withInformation(data)
        );
    }
    @Then("I validate the response content for the login")
    public void iValidateTheResponseContentForTheLogin(LoginDataTable data) {
        actor.attemptsTo(
                ValidateLogin.response(data)
        );
    }

}
