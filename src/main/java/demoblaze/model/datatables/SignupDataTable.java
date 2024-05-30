package demoblaze.model.datatables;

import lombok.Data;

@Data
public class SignupDataTable {
    private String username;
    private String password;
    private String error;
    private String codeResponse;
}
