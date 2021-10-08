package JDBC03;

import java.util.Scanner;

public class Book_Driver
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("\n*** 메뉴 선택 ***");
			System.out.printf("1. 데이터추가.");
			System.out.printf("2. 데이터열람.");
			System.out.printf("3. 데이터수정.");
			System.out.printf("4. 데이터삭제.");
			System.out.println("5. 프로그램 종료.");
			System.out.print(">> 메뉴선택 : ");
			String choice = sc.nextLine();
			
			if(choice.equals("5"))
				break;
			
			switch(choice) {
				case "1": 
					break;
				case "2":
					break;
				case "3":
					break;
				case "4":
					break;
				default:
					System.out.println("잘못 입력된 값입니다.");
			}
		}
		System.out.println("프로그램 종료");
		
	}
}
