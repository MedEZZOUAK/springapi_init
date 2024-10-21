package ensate.ma.SpringAPI.auth;

import ensate.ma.SpringAPI.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
  private String Token;
  private Role role;
  private Integer id ;
}
