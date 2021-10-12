package JDBC05;

import java.util.Scanner;

import JDBC04.Member_Dao;

public class Rent_Driver 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		// 기능 실행 무한 반복
		while(true) {
			System.out.println("\n*** 메뉴 선택 ***");
			System.out.printf("1. 데이터추가. ");
			System.out.printf("2. 데이터열람. ");
			System.out.printf("3. 데이터수정. ");
			System.out.printf("4. 데이터삭제. ");
			System.out.println("5. 프로그램 종료. ");
			System.out.print(">> 메뉴선택 : ");
			String choice = sc.nextLine();
			
			// 5 입력시 프로그램 종료
			if(choice.equals("5"))
				break;
			
			// 공통적으로 사용할 멤버 접속 객체
			Rent_Dao Rdao = new Rent_Dao();
			
			// 입력한 번호에 따른 기능 실행
			switch(choice) {
				case "1": 
					insert(sc, Rdao); // 158 Line
					break;
				case "2":
					select(Rdao); // 140 Line
					break;
				case "3":
					update(sc, Rdao); // 60 Line
					break;
				case "4":
					delete(sc, Rdao); // 47 Line
					break;
				default:
					System.out.println("잘못 입력된 값입니다.");
			}
		}
	}

	private static void delete(Scanner sc, Rent_Dao rdao) {
		
	}

	private static void update(Scanner sc, Rent_Dao rdao) {
		
	}

	private static void select(Rent_Dao rdao) {
		
		
	}

	private static void insert(Scanner sc, Rent_Dao rdao) {
		
	}
	
}