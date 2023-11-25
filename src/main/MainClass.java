package main;

import java.util.Scanner;

import dao.BaseballDao;
import dao.IBaseballDao;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		// menu
		IBaseballDao dao = new BaseballDao();
		
		// dummy
		((BaseballDao)dao).chnageDummyMode();

		while (true) {
			System.out.println("========= 선수정보 프로그램 ==========");
			System.out.println("1. 선수추가");
			System.out.println("2. 선수삭제");
			System.out.println("3. 선수검색");
			System.out.println("4. 선수수정");
			System.out.println("5. 타율순위");
			System.out.println("6. 방어율순위");
			System.out.println("7. 파일저장");
			System.out.println("8. 파일로드");
			// dummy
			System.out.println("그외 목록출력");

			System.out.print("메뉴의 번호 >> ");
			int menuNumber = sc.nextInt();

			try {
				switch (menuNumber) {
				case 1:
					dao.insert();
					break;
				case 2:
					dao.delete();
					break;
				case 3:
					dao.select();
					break;
				case 4:
					dao.update();
					break;
				case 5:
					dao.battingSort();
					break;
				case 6:
					dao.pitcherSort();
					break;
				case 7:
					((BaseballDao)dao).save();
					break;
				case 8:
					((BaseballDao)dao).load();
					break;
				default:
					// dummy
					((BaseballDao)dao).print();
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
