package AppDTO;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO implements Serializable {

	private static final long serialVersionUID = -5712717952116153637L;

	private String username;
	private String password;
}
