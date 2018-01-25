package blog.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {
	@Size(min = 6, max = 10, message = "Username should be in the range [6-10]")
	private String username;
	
	@NotNull
	@Size(min = 6, max = 50)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
