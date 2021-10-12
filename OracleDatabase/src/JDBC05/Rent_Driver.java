package JDBC05;

import java.util.ArrayList;
import java.util.Scanner;

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
			
			// Rent_Dao를 공유해서 쓰는 방법 #1
			// Driver Class에서 객체를 생성하고 각 메소드에 전달인수로 전달
			// 공통적으로 사용할 멤버 접속 객체
			//Rent_Dao Rdao = Rent_Dao.getInstance();
			
			// 입력한 번호에 따른 기능 실행
			switch(choice) {
				case "1": 
					insert(sc); // 158 Line
					break;
				case "2":
					select(); // 140 Line
					break;
				case "3":
					update(sc); // 60 Line
					break;
				case "4":
					delete(sc); // 47 Line
					break;
				default:
					System.out.println("잘못 입력된 값입니다.");
			}
			
			System.out.println("프로그램 종료");
			sc.close();
		}
	}

	private static void delete(Scanner sc) {
		// Rent_Dao를 공유해서 쓰는 방법 #2
		// Rent_Dao를 Singleton 방식으로 구현하여 쓰는 방법
		Rent_Dao Rdao = Rent_Dao.getInstance();
		
	}

	private static void update(Scanner sc) {
		// Rent_Dao를 공유해서 쓰는 방법 #2
		// Rent_Dao를 Singleton 방식으로 구현하여 쓰는 방법
		Rent_Dao Rdao = Rent_Dao.getInstance();
		
	}

	private static void select() {
		// Rent_Dao를 공유해서 쓰는 방법 #2
		// Rent_Dao를 Singleton 방식으로 구현하여 쓰는 방법
		Rent_Dao Rdao = Rent_Dao.getInstance();
		ArrayList<Rent_Dto> list = Rdao.selectAll();
		
		System.out.println("대여날짜\t\t 대여순번 \t 도서번호 \t 회원번호 \t 할인금액");
		System.out.println("--------------------------------------------------------------------");
		
		for(Rent_Dto rdto : list) {
			System.out.printf("%s \t %-3d \t\t %d \t\t %d \t\t %d\n", rdto.getRentdate(),
					rdto.getNumseq(), rdto.getBooknum(), rdto.getMembernum(), rdto.getDiscount());
		}
		
	}

	private static void insert(Scanner sc) {
		// Rent_Dao를 공유해서 쓰는 방법 #2
		// Rent_Dao를 Singleton 방식으로 구현하여 쓰는 방법
		Rent_Dao Rdao = Rent_Dao.getInstance();
		
	}
	
}