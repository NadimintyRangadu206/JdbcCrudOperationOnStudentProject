package com.palle.crudoperations;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Student s = new Student();

		Scanner sc = new Scanner(System.in);
		System.out.println("Press a Number");
		int pressNo = sc.nextInt();

		switch (pressNo) {
		case 1:
			System.out.println(s.creatTable());
			break;
		case 2:
			System.out.println(s.save(2, "Venky", "Java", "Venky@gmail.com"));
			break;
		case 3:
			System.out.println(s.update(2, "Java", "pvj@gmail.com"));
			break;
		case 4:
			System.out.println(s.delete(2));
			break;
		case 5:
			s.getAllData();
			break;

		default:
			System.out.println("Exist");
			break;
		}

	}

}
