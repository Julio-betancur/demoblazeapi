package demoblaze.model.json.request.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupBody {
    private String username;
    private String password;
}
