package Model;

public class User {
	// do I want to have account information in here, and transactions inside accounts?
	private int User_ID;
	private long CC_number;
	private int pin;
	private String F_name;
	private String L_name;
	private long phone;
	private String email;
	
	public User(int user_ID, long cC_number, int pin, String f_name, String l_name, long phone, String email) {
		super();
		User_ID = user_ID;
		CC_number = cC_number;
		this.pin = pin;
		F_name = f_name;
		L_name = l_name;
		this.phone = phone;
		this.email = email;
	}
	
	public int getUser_ID() {
		return User_ID;
	}
	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
	}
	public long getCC_number() {
		return CC_number;
	}
	public void setCC_number(long cC_number) {
		CC_number = cC_number;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getF_name() {
		return F_name;
	}
	public void setF_name(String f_name) {
		F_name = f_name;
	}
	public String getL_name() {
		return L_name;
	}
	public void setL_name(String l_name) {
		L_name = l_name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [User_ID=" + User_ID + ", CC_number=" + CC_number + ", pin=" + pin + ", F_name=" + F_name
				+ ", L_name=" + L_name + ", phone=" + phone + ", email=" + email + "]";
	}
	
	
	
//	`User_ID` int unsigned NOT NULL AUTO_INCREMENT,
//	`CC_number` varchar(16) NOT NULL,
//	`Pin` varchar(4) NOT NULL,
//	`Fist_name` varchar(25) NOT NULL,
//	`Last_name` varchar(25) NOT NULL,
//	`Phone` varchar(10) NOT NULL,
//	`Email` varchar(50) NOT NULL,
}
