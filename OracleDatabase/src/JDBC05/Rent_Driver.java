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
		}
		System.out.println("프로그램 종료");
		sc.close();
	}

	private static void delete(Scanner sc) {
		// Rent_Dao를 공유해서 쓰는 방법 #2
		// Rent_Dao를 Singleton 방식으로 구현하여 쓰는 방법
		Rent_Dao Rdao = Rent_Dao.getInstance();
		System.out.print("삭제할 대여순번를 입력하세요 : ");
		String numseq =  sc.nextLine();
		
		int result = Rdao.deleteSql(numseq);
		
		if(result == 1)
			System.out.println("삭제 성공");
		else
			System.out.println("삭제 실패");
		
	}

	private static void update(Scanner sc) {
		// Rent_Dao를 공유해서 쓰는 방법 #2
		// Rent_Dao를 Singleton 방식으로 구현하여 쓰는 방법
		Rent_Dao Rdao = Rent_Dao.getInstance();
		String num;
		int result = 0;
		
		while(true)
		{
			System.out.print("수정할 대여순번을 입력하세요 : ");
			num = sc.nextLine();
			if(num.equals(""))
				System.out.println("대여순번 입력은 필수입니다.");
			else 
				break;
		}
		
		Rent_Dto old_Rdto = Rdao.getDto(num);
		
		if(old_Rdto == null) {
			System.out.println("해당 순번이 없습니다.");
			return;
		}
		
		Rent_Dto new_Rdto = new Rent_Dto();
		new_Rdto.setNumseq(old_Rdto.getNumseq());
		
		System.out.print("수정할 날짜를 입력하세요. : ");
		String rentdate = sc.nextLine();
		if(rentdate.equals(""))
			new_Rdto.setRentdate(old_Rdto.getRentdate());
		else
			new_Rdto.setRentdate(rentdate);
		
		System.out.print("수정할 도서번호를 입력하세요 : ");
		String booknum;
		while(true) {
			booknum = sc.nextLine();
			if(booknum.equals(""))
			{
				new_Rdto.setBooknum(old_Rdto.getBooknum());
				break;
			}
			else 
			{
				String bn = Rdao.confirmBn(booknum); // 입력한 도서 번호로 조회
				if(bn == null)
					System.out.print("해당 도서가 없습니다. 다시 입력해주세요. : ");
				else {
					new_Rdto.setBooknum(Integer.parseInt(booknum));
					break;
				}
			}
				
		}
		
		System.out.print("수정할 회원번호를 입력하세요 : ");
		String membernum;
		while(true) {
			membernum = sc.nextLine();
			if(membernum.equals(""))
			{
				new_Rdto.setMembernum(old_Rdto.getMembernum());
				break;
			}
			else 
			{
				String mn = Rdao.confirmMn(membernum); // 입력한 도서 번호로 조회
				if(mn == null)
					System.out.print("해당 회원가 없습니다. 다시 입력해주세요. : ");
				else {
					new_Rdto.setMembernum(Integer.parseInt(membernum));
					break;
				}
			}
		}
		System.out.print("수정할 할인금액을 입력하세요 : ");
		String discount = sc.nextLine();
		if(discount.equals(""))
			new_Rdto.setDiscount(old_Rdto.getDiscount());
		else
			new_Rdto.setDiscount(Integer.parseInt(discount));
		
		result = Rdao.updateSql(new_Rdto);
		
		if(result == 1) 
			System.out.println("수정완료");
		else
			System.out.println("수정실패");
		
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
		Rent_Dto Rdto = new Rent_Dto();
		
		System.out.print("입력할 대여날짜를 입력하세요 : ");
		Rdto.setRentdate(sc.nextLine());
		
		System.out.print("입력할 도서번호를 입력하세요 : ");
		Rdto.setBooknum(Integer.parseInt(sc.nextLine()));
				
		System.out.print("입력할 회원번호를 입력하세요 : ");
		Rdto.setMembernum(Integer.parseInt(sc.nextLine()));
				
		System.out.print("입력할 할인금액을 입력하세요 : ");
		Rdto.setDiscount(Integer.parseInt(sc.nextLine()));
		
		int result = Rdao.insertSql(Rdto);
		
		if(result == 1)
			System.out.println("삽입성공");
		else
			System.out.println("삽입실패");
	}
	
}