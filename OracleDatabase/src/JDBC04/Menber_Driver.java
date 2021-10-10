package JDBC04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

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
		ArrayList<Member_Dto> list = mdao.selectAll();
		
		System.out.println("회원번호 \t  회원이름 \t\t 핸드폰번호 \t\t 성별\t 나이\t 생일 \t\t\t 가입날짜\t\t 포인트");
		System.out.println("----------------------------------------------------------------------------------------------------");
		
		for(Member_Dto mto : list)
		{ // list에 있는 값이 하나씩 dto에 저장
			System.out.printf("%s \t\t %-12s \t %13s\t %s\t %-2d\t %-10s\t %-10s\t %-6s\n",
					mto.getMembernum(), mto.getName(), mto.getPhone(), mto.getGender(), 
					mto.getAge(), mto.getBirth(), mto.getJoindate(), mto.getBpoint());
		}
		
	}
	
	// memberlist 삽입 함수 - Dao.insertSql 메소드와 연결
	private static void insert(Scanner sc, Member_Dao mdao) {
		Member_Dto mdto = new Member_Dto();
		Date date = null;
		
		System.out.print("회원 이름을 입력하세요 : ");
		mdto.setName(sc.nextLine());
		
		System.out.print("핸드폰 번호를 입력하세요 : ");
		mdto.setPhone(sc.nextLine());
		
		System.out.print("생일을 입력하세요 : ");
		date = Date.valueOf(sc.nextLine());
		mdto.setBirth(date);
		
		System.out.print("가입날짜를 입력하세요 : ");
		date = Date.valueOf(sc.nextLine());
		mdto.setJoindate(date);
		
		System.out.print("보유 포인트를 입력하세요 : ");
		mdto.setBpoint(Integer.parseInt(sc.nextLine()));
		
		System.out.print("성별을 입력하세요 : ");
		mdto.setGender(sc.nextLine());

		System.out.print("나이를 입력하세요 : ");
		mdto.setAge(Integer.parseInt(sc.nextLine()));
		
		int result = mdao.insertSql(mdto);
		
		if(result == 1)
			System.out.println("삽입 성공");
		else
			System.out.println("삽입 실패");

	}

	private static char[] typeof(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	// memberlist 삭제 함수 - Dao.deleteSql 메소드와 연결
	private static void delete(Scanner sc, Member_Dao mdao) {
		
	}
	
	// memberlist 수정 함수 - Dao.updateSql 메소드와 연결
	private static void update(Scanner sc, Member_Dao mdao) {
		
	}
}