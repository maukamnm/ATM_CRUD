package Controller;
//11/18 10-12|| 11/19 11-4 || 11-20 11:40-2, 3:30-5, 9-11 ||

import java.util.Scanner;

import Model.ATM;

public class BankingController {

	static ATM atm = new ATM();
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		atm.login();
//		int start = sc.nextInt();
//		if (start == 1) {
//			atm.createUser();
//			atm.login();
//		}
//		if (start == 2) {
//			atm.login();
//		}
	}

}