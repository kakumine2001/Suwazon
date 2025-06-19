package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String user_id;
	private String user_name;
	private int age;
	private String gender;
	private String password;
	private boolean is_admin;
}
