package JDBC04;

import java.util.ArrayList;
import java.util.Calendar;
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
		
		System.out.println("회원번호 \t  회원이름 \t\t 핸드폰번호 \t\t\t 성별\t 나이\t 생일 \t\t\t 가입날짜\t\t 포인트");
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
		try {
			date = Date.valueOf(sc.nextLine());
			mdto.setBirth(date);
		}catch(IllegalArgumentException e)
		{
			System.out.println("날짜형식을 지켜주세요. (YYYY-MM-DD)");
			System.out.println("메인으로 돌아갑니다.");
			return;
		}
		
		// 입력받은 생일을 기반으로한 나이 기입
		Calendar d = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int age = d.get(Calendar.YEAR)- c.get(Calendar.YEAR) + 1;
		mdto.setAge(age);
		
		/*
		  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  	java.util.Date d = null;
		  	while(true){
		  		try{
		  			d = sdf.parse(sc.nextLine());
		  			break;
		  		}catch(ParseException e){
		  			e.printStackTrace();
		  		}
		  	}
		  	java.sql.Date birth = new java.sql.Date(d.getTime());
		  	pdto.setBirth(birth);
		 */
		
		System.out.print("가입날짜를 입력하세요 : ");
		try {
			date = Date.valueOf(sc.nextLine());
			mdto.setJoindate(date);
		}catch(IllegalArgumentException e)
		{
			System.out.println("날짜형식을 지켜주세요. (YYYY-MM-DD)");
			System.out.println("메인으로 돌아갑니다.");
			return;
		}
		
		System.out.print("보유 포인트를 입력하세요 : ");
		mdto.setBpoint(Integer.parseInt(sc.nextLine()));
		
		System.out.print("성별을 입력하세요 : ");
		mdto.setGender(sc.nextLine());
		
		int result = mdao.insertSql(mdto);
		
		if(result == 1)
			System.out.println("삽입 성공");
		else
			System.out.println("삽입 실패");

	}

	// memberlist 삭제 함수 - Dao.deleteSql 메소드와 연결
	private static void delete(Scanner sc, Member_Dao mdao) {
		System.out.print("삭제할 회원번호를 입력하세요 : ");
		String membernum = sc.nextLine();
		
		int result = mdao.deleteSql(membernum);
		
		if(result == 1)
			System.out.println("삭제 성공");
		else
			System.out.println("삭제 실패");
	}
	
	// memberlist 수정 함수 - Dao.updateSql 메소드와 연결
	private static void update(Scanner sc, Member_Dao mdao) {
		String num;
		int result = 0;
		
		while(true)
		{
			System.out.print("수정할 회원번호를 입력하세요 : ");
			num = sc.nextLine();
			if(num.equals(""))
				System.out.println("회원번호 입력은 필수입니다.");
			else 
				break;
		}
		
		Member_Dto oldDto = mdao.getDto(num);
		
		if(oldDto == null)
		{
			System.out.println("해당 회원이 없습니다.");
			return;
		}
		
		Member_Dto newDto = new Member_Dto();
		newDto.setMembernum(oldDto.getMembernum());
		
		System.out.print("수정할 이름을 입력하세요 : ");
		String name = sc.nextLine();
		if(name.equals(""))
			newDto.setName(oldDto.getName());
		else
			newDto.setName(name);
		
		System.out.print("수정할 핸드폰번호를 입력하세요 : ");
		String phone = sc.nextLine();
		if(phone.equals(""))
			newDto.setPhone(oldDto.getPhone());
		else
			newDto.setPhone(phone);
		
		System.out.print("수정할 생일을 입력하세요 : ");
		String input = sc.nextLine();
		if(input.equals(""))
			newDto.setBirth(oldDto.getBirth());
		else
		{
			Date birth = Date.valueOf(input);
			newDto.setBirth(birth);
		}

		System.out.print("수정할 가입날짜를 입력하세요 : ");
		input = sc.nextLine();
		if(input.equals(""))
			newDto.setJoindate(oldDto.getJoindate());
		else
		{
			Date joindate = Date.valueOf(input);
			newDto.setJoindate(joindate);
		}
		
		System.out.print("수정할 포인트를 입력하세요 : ");
		String bpoint = sc.nextLine();
		if(bpoint.equals(""))
			newDto.setBpoint(oldDto.getBpoint());
		else
			newDto.setBpoint(Integer.parseInt(bpoint));
		
		System.out.print("수정할 성별을 입력하세요 : ");
		String gender = sc.nextLine();
		if(gender.equals(""))
			newDto.setGender(oldDto.getGender());
		else
			newDto.setGender(gender);

		System.out.print("수정할 나이를 입력하세요 : ");
		String age = sc.nextLine();
		if(age.equals(""))
			newDto.setAge(oldDto.getAge());
		else
			newDto.setAge(Integer.parseInt(age));
		
		result = mdao.updateSql(newDto);
		
		if(result == 1)
			System.out.println("수정 성공");
		else
			System.out.println("수정 실패");
	}
}
