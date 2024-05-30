package demoblaze.model.json.request.generator;

import demoblaze.model.datatables.SignupDataTable;
import demoblaze.model.json.request.model.SignupBody;

import java.util.Random;

public class SignupBodyGenerator {
    private final SignupDataTable data;
    private final SignupBody signupBody;
    Random random = new Random();
    public SignupBodyGenerator(SignupDataTable data) {
        this.data = data;
        this.signupBody = SignupBody.builder().build();
    }

    private void setUsername(){
        String dataUsername = data.getUsername();
        String username = dataUsername.equals("newuser")
                ?dataUsername+random.nextInt(360000)
                :dataUsername;
        signupBody.setUsername(username);
    }

    private void setPassword(){
        signupBody.setPassword(data.getPassword());
    }

    public SignupBody generate(){
        setUsername();
        setPassword();
        return signupBody;
    }

}
