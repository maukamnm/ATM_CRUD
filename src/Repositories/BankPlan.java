package Repositories;

public interface ATM_Plan {

	public void login(long cc, int pin);

	public void createAccount();

	public String readAccountInfo();

	public String deleteAnAccount(int deleteID, int transID);

	public String search(String s, int Account_ID);

	public String sort(int x, int Account_ID);

	public void updateAccount(int Account_ID, String sign, long amountTrans);

	public String updatePin(int newPin);

}
