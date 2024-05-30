package demoblaze.model.json.request.generator;

import demoblaze.model.datatables.LoginDataTable;
import demoblaze.model.json.request.model.LoginBody;

public class LoginBodyGenerator {
    private final LoginDataTable data;
    private final LoginBody loginBody;

    public LoginBodyGenerator(LoginDataTable data) {
        this.data = data;
        this.loginBody = LoginBody.builder().build();
    }

    private void setUsername(){
        loginBody.setUsername(data.getUsername());
    }

    private void setPassword(){
        loginBody.setPassword(data.getPassword());
    }

    public LoginBody generate(){
        setUsername();
        setPassword();
        return loginBody;
    }
}
