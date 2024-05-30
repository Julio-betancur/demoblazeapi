package demoblaze.model.datatables;

import lombok.Data;

@Data
public class LoginDataTable {
    private String username;
    private String password;
    private String codeResponse;
    private String token;
    private String error;
}
