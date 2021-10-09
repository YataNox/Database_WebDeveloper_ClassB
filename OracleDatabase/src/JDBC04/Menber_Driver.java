package JDBC04;

import java.util.Scanner;

import JDBC03.Book_Dao;

public class Menber_Driver 
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
			Member_Dao Mdao = new Member_Dao();
			
			// 입력한 번호에 따른 기능 실행
			switch(choice) {
				case "1": 
					insert(sc, Mdao); // 158 Line
					break;
				case "2":
					select(Mdao); // 140 Line
					break;
				case "3":
					update(sc, Mdao); // 60 Line
					break;
				case "4":
					delete(sc, Mdao); // 47 Line
					break;
				default:
					System.out.println("잘못 입력된 값입니다.");
			}
		}
		
		System.out.println("프로그램 종료");
		sc.close();
	}

	// memberlist 조회 함수 - Dao.selectSql 메소드와 연결
	private static void select(Member_Dao mdao) {
		
	}
	
	// memberlist 삽입 함수 - Dao.insertSql 메소드와 연결
	private static void insert(Scanner sc, Member_Dao mdao) {
		
	}

	// memberlist 삭제 함수 - Dao.deleteSql 메소드와 연결
	private static void delete(Scanner sc, Member_Dao mdao) {
		
	}
	
	// memberlist 수정 함수 - Dao.updateSql 메소드와 연결
	private static void update(Scanner sc, Member_Dao mdao) {
		
	}
}
