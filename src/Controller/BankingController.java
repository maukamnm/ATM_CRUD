package Controller;

import View.Login_GUI;

public class BankingDriver {
	// 11/18 10-12|| 11/19 11-3 || 11/20 11:30-2, 3:30-6 || 11/22 3:15-5:30 || 11/23
	// 2-4:30 || Total = 15.75 || 11/24 10-12:15, 12:30-1, 1:30-5:30 Day total 6.75 || Total = 22.5 ||
	// 11/25 9-11, 11:45-2 --  day total = 4.25 ||
	static ATM atm = new ATM();

	public static void main(String[] args) {
		Login_GUI.createAndShowGUI();
	}
}
