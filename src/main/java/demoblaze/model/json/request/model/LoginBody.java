package demoblaze.model.json.request.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginBody {
    private String username;
    private String password;
}
