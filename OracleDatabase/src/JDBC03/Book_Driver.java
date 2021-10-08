package JDBC03;

import java.util.ArrayList;
import java.util.Scanner;

public class Book_Driver
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("\n*** 메뉴 선택 ***");
			System.out.printf("1. 데이터추가. ");
			System.out.printf("2. 데이터열람. ");
			System.out.printf("3. 데이터수정. ");
			System.out.printf("4. 데이터삭제. ");
			System.out.println("5. 프로그램 종료. ");
			System.out.print(">> 메뉴선택 : ");
			String choice = sc.nextLine();
			
			if(choice.equals("5"))
				break;
			
			Book_Dao bd = new Book_Dao();
			switch(choice) {
				case "1": 
					Book_Dto dbto = new Book_Dto();
					System.out.print("책제목을 입력하세요 : ");
					dbto.setSubject(sc.nextLine());
					System.out.print("입고가격을 입력하세요 : ");
					dbto.setInprice(Integer.parseInt(sc.nextLine()));
					System.out.print("대여제목을 입력하세요 : ");
					dbto.setRentprice(Integer.parseInt(sc.nextLine()));
					System.out.print("제작년도를 입력하세요 : ");
					dbto.setMakeyear(Integer.parseInt(sc.nextLine()));
					System.out.print("등급을 입력하세요 : ");
					dbto.setGrade(sc.nextLine());
					int result = bd.insertSql(dbto);
					
					if(result == 1)
						System.out.println("레코드 추가 성공");
					else
						System.out.println("레코드 추가 실패");
					break;
				case "2":
					// 데이터 열람 명령이 입력되면, Book_Dao클래스의 객체를 만들고, 그 안의 멤버 메소드 중
					// 데이터를 모두 조회해서 리턴해줄 수 있는 메소드를 호출합니다.
					// 그리고 그 리턴된 결과를 화면에 출력합니다.
					ArrayList<Book_Dto> list = bd.selectAll();
					// 리턴된 list를 화면에 출력
					System.out.println("책번호 \t  제작년도 \t 가격 \t\t 대여가격 \t 등급 \t 제목 ");
					System.out.println("------------------------------------------------------------------------");
					for(Book_Dto dto : list) { // list에 있는 값이 하나씩 dto에 저장
						System.out.printf("%-5d \t\t %-4d \t %6d\t %4d\t\t %-3s\t %-30s\n",
								dto.getBooknum(), dto.getMakeyear(), dto.getInprice(), dto.getRentprice(), dto.getGrade(), dto.getSubject());
					}
					
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
