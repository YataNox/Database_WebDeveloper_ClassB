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
					insert(sc, bd);
					break;
				case "2":
					select(bd);
					break;
				case "3":
					update(sc, bd);
					break;
				case "4":
					delete(sc, bd);
					break;
				default:
					System.out.println("잘못 입력된 값입니다.");
			}
		}
		System.out.println("프로그램 종료");
		sc.close();
	}

	// 삭제 함수-----------------------------------------------------------------------------
	private static void delete(Scanner sc, Book_Dao bd) {
		// 삭제할 번호를 입력 받고, 그 번호를 전달 인수로 해서 dao의 delete() 메소드를 호출합니다.
		System.out.print("삭제할 책 번호를 입력하세요 : ");
		String num = sc.nextLine();
		int result = bd.deleteSql(num);
		
		if(result == 1)
			System.out.println("레코드 제거 성공");
		else
			System.out.println("레코드 제거 실패");
	}

	// 수정 함수-----------------------------------------------------------------------------
	private static void update(Scanner sc, Book_Dao bd) {
		String num;
		int result;
		
		// 수정할 도서 번호를 입력받습니다.
		while(true)
		{
			System.out.print("수정할 책 번호를 입력하세요 : ");
			num = sc.nextLine();
			if(num.equals(""))
				System.out.println("도서번호 입력은 필수입니다.");
			else 
				break;
		}
		// 입력받은 도서 번호로 도서를 조회해서 dto객체에 저장해둡니다. 원본 데이터의 로딩
		Book_Dto oldDto = bd.getDto(num);
		
		// 조회한 번호가 없는 번호이면 해당 도서가 없다고 출력하고 실행종료
		if(oldDto == null)
		{
			System.out.println("해당 도서가 없습니다.");
			return;
		}
		
		Book_Dto newDto = new Book_Dto();
		newDto.setBooknum(oldDto.getBooknum());
		
		// 수정할 항목들을 하나하나 전부 다 입력받습니다.
		// 다만 입력받은 내용이 없다면 (빈칸이라면, 엔터만 눌렀다면)
		// 새로 고쳐질 데이터가 저장될 객체에 원본 데이처로 해당 필드를 대체합니다.
		// 도서제목
		System.out.print("수정할 도서의 제목을 입력하세요 : ");
		String subject = sc.nextLine();
		if(subject.equals(""))
			newDto.setSubject(oldDto.getSubject());
		else
			newDto.setSubject(subject);
		
		// 제작년도
		System.out.print("수정할 도서의 제작년도를 입력하세요 : ");
		String makeyear = sc.nextLine();
		if(makeyear.equals(""))
			newDto.setMakeyear(oldDto.getMakeyear());
		else
			newDto.setMakeyear(Integer.parseInt(makeyear));
		
		// 입고가격
		System.out.print("수정할 도서의 입고가격을 입력하세요 : ");
		String inprice = sc.nextLine();
		if(inprice.equals(""))
			newDto.setInprice(oldDto.getInprice());
		else
			newDto.setInprice(Integer.parseInt(inprice));
		
		// 대여가격
		System.out.print("수정할 도서의 대여가격을 입력하세요 : ");
		String rentprice = sc.nextLine();
		if(rentprice.equals(""))
			newDto.setRentprice(oldDto.getRentprice());
		else
			newDto.setRentprice(Integer.parseInt(rentprice));
		
		// 등급
		System.out.print("수정할 도서의 등급을 입력하세요 : ");
		String grade = sc.nextLine();
		if(grade.equals(""))
			newDto.setGrade(oldDto.getGrade());
		else
			newDto.setGrade(grade);
		
		// 그리고 bd.update(newDto); 를 실행해서 수정합니다.
		result = bd.updateSql(newDto);
		
		if(result == 1)
			System.out.println("수정 완료");
		else
			System.out.println("수정 실패");
	}

	// 조회 함수-----------------------------------------------------------------------------
	private static void select(Book_Dao bd)
	{
		// 데이터 열람 명령이 입력되면, Book_Dao클래스의 객체를 만들고, 그 안의 멤버 메소드 중
		// 데이터를 모두 조회해서 리턴해줄 수 있는 메소드를 호출합니다.
		// 그리고 그 리턴된 결과를 화면에 출력합니다.
		ArrayList<Book_Dto> list = bd.selectAll();
		// 리턴된 list를 화면에 출력
		System.out.println("책번호 \t  제작년도 \t 가격 \t\t 대여가격 \t 등급 \t 제목 ");
		System.out.println("------------------------------------------------------------------------");
		
		for(Book_Dto dto : list)
		{ // list에 있는 값이 하나씩 dto에 저장
			System.out.printf("%-5d \t\t %-4d \t %6d\t %4d\t\t %-3s\t %-30s\n",
					dto.getBooknum(), dto.getMakeyear(), dto.getInprice(), dto.getRentprice(), dto.getGrade(), dto.getSubject());
		}
	}

	// 삽입 함수-----------------------------------------------------------------------------
	private static void insert(Scanner sc, Book_Dao bd) 
	{
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
	}
}
