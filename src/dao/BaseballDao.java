package dao;

import java.util.Arrays;
import java.util.Scanner;

import dto.BatterDto;
import dto.HumanDto;
import dto.PitcherDto;
import util.ObjectArrayUtil;

public class BaseballDao implements IBaseballDao {

	private HumanDto[] playerArr;
	private Scanner sc;
	private FileIO fileio;
	private int counter;

	public BaseballDao() {
		playerArr = new HumanDto[10];
		sc = new Scanner(System.in);
		fileio = new FileIO("baseball");
		counter = 0;

		fileio.create();
	}

	@Override
	public void insert() {
		System.out.println("선수 타입을 선택하시오(1:투수/2:타자)");

		int playerTypeFlag = sc.nextInt();

		switch (playerTypeFlag) {
		case 1:
			insertPicher();
			break;
		case 2:
			insertBatter();
			break;
		default:
			break;
		}

		counter++; // 배열의 다음으로 이동
	}

	private void insertPicher() {
		System.out.print("번호 >> ");
		int number = sc.nextInt();

		System.out.print("이름 >> ");
		String name = sc.next();

		System.out.print("나이 >> ");
		int age = sc.nextInt();

		System.out.print("키 >> ");
		double height = sc.nextDouble();

		System.out.print("포지션 >> ");
		String position = sc.next();

		System.out.print("win >> ");
		int win = sc.nextInt();

		System.out.print("lose >> ");
		int lose = sc.nextInt();

		System.out.print("방어율 >> ");
		double defense = sc.nextDouble();

		playerArr[counter] = new PitcherDto(number, name, age, height, position, win, lose, defense);
	}

	private void insertBatter() {
		System.out.print("번호 >> ");
		int number = sc.nextInt();

		System.out.print("이름 >> ");
		String name = sc.next();

		System.out.print("나이 >> ");
		int age = sc.nextInt();

		System.out.print("키 >> ");
		double height = sc.nextDouble();

		System.out.print("포지션 >> ");
		String position = sc.next();

		System.out.print("bat count >> ");
		int batcount = sc.nextInt();

		System.out.print("hit >> ");
		int hit = sc.nextInt();

		System.out.print("타율 >> ");
		double hivAvg = sc.nextDouble();

		playerArr[counter] = new BatterDto(number, name, age, height, position, batcount, hit, hivAvg);
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		// 이름입력
		System.out.print("삭제하고 싶은 선수의 이름 >> ");
		String name = sc.next();

		// 검색
		int findIndex = search(name);

		if (findIndex == -1) {
			System.out.println("선수 정보를 찾을 수 없습니다.");
			return;
		}

		HumanDto player = playerArr[findIndex];

		player.setNumber(0);
		player.setName("");
		player.setAge(0);
		;
		player.setHeight(0.0);

		if (player instanceof PitcherDto) {
			PitcherDto pitcher = (PitcherDto) player;
			deletePitcher(pitcher);
		} else if (player instanceof BatterDto) {
			BatterDto batter = (BatterDto) player;
			deleteBatter(batter);
		}
	}

	private void deletePitcher(PitcherDto pitcher) {
		pitcher.setPosition("");
		pitcher.setWin(0);
		pitcher.setLose(0);
		pitcher.setDefence(0.0);
	}

	private void deleteBatter(BatterDto batter) {
		batter.setPosition("");
		batter.setBatcount(0);
		batter.setHit(0);
		batter.setHivAvg(0.0);
	}

	@Override
	public void select() {
		// TODO Auto-generated method stub
		System.out.print("검색하고 싶은 선수의 이름 >> ");
		String name = sc.next();

		int findIndex = search(name);

		if (findIndex != -1) {
			System.out.println(playerArr[findIndex].toString());
		} else {
			System.out.println("선수 명단에 없습니다.");
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		// 이름입력
		System.out.print("수정하고 싶은 선수의 이름입력 >> ");
		String name = sc.next();

		int findIndex = search(name);

		if (findIndex == -1) {
			System.out.println("학생명단에 없습니다");

			return;
		}

		// 특성 수정
		System.out.println("수정할 데이터를 찾았습니다.");
		
		HumanDto player = playerArr[findIndex];
		
		if (player instanceof PitcherDto) {
			PitcherDto pitcher = (PitcherDto)player;
			updatePitcher(pitcher);
		} else if (playerArr[findIndex] instanceof BatterDto) {
			BatterDto batter = (BatterDto)player;
			updateBatter(batter);
		}

		System.out.println("수정을 완료했습니다");
	}
	
	private void updatePitcher(PitcherDto pitcher) {
		System.out.print("포지션 >> ");
		String position = sc.next();
		
		System.out.print("win >> ");
		int win = sc.nextInt();
		
		System.out.print("lose >> ");
		int lose = sc.nextInt();
		
		System.out.print("방어율 >> ");
		double defence = sc.nextDouble();
		
		pitcher.setPosition(position);
		pitcher.setWin(win);
		pitcher.setLose(lose);
		pitcher.setDefence(defence);
	}
	
	private void updateBatter(BatterDto batter) {
		System.out.print("포지션 >> ");
		String position = sc.next();
		
		System.out.print("bat count >> ");
		int batCount = sc.nextInt();
		
		System.out.print("hit >> ");
		int hit = sc.nextInt();
		
		System.out.print("타율 >> ");
		double hivAvg = sc.nextDouble();
		
		batter.setPosition(position);
		batter.setBatcount(batCount);
		batter.setHit(hit);
		batter.setHivAvg(hivAvg);
	}

	@Override
	public void battingSort() {
		// TODO Auto-generated method stub
		BatterDto[] batterArr = null;
		int batterCount = 0;
		
		for (int i = 0; i < playerArr.length; i++) {
			HumanDto player = playerArr[i];
			
			if (player != null) {
				if (player instanceof BatterDto) {
					batterCount++;
				}
			}
		}
		
		if (batterCount > 0) {
			batterArr = new BatterDto[batterCount];
		} else {
			throw new IllegalArgumentException("타자가 없습니다.");
		}
		
		batterCount = 0;
		
		for (int i = 0; i < playerArr.length; i++) {
			HumanDto player = playerArr[i];
			
			if (player != null) {
				if (player instanceof BatterDto) {
					batterArr[batterCount] = (BatterDto)player;
					batterCount++;
				}
			}
		}
		
		for (int i = 0; i < batterArr.length - 1; i++) {
			for (int j = i + 1; j < batterArr.length; j++) {
				double leftHivAvg = batterArr[i].getHivAvg();
				double rightHivAvg = batterArr[j].getHivAvg();
				boolean ascCondition = leftHivAvg > rightHivAvg;
				
				if (ascCondition) {
					ObjectArrayUtil.swap(batterArr, i, j);
				}
			}
		}
		
		for (int i = 0; i < batterArr.length; i++) {
			String printStr = (i + 1) + "위 " + batterArr[i].toString();
			
			System.out.println(printStr);
		}
	}

	@Override
	public void pitcherSort() {
		// TODO Auto-generated method stub
		PitcherDto[] pitcherArr;
		int pitcherCount = 0;
		
		for (int i = 0; i < playerArr.length; i++) {
			HumanDto player = playerArr[i];
			
			if (player != null) {
				if (player instanceof PitcherDto) {
					pitcherCount++;
				}
			}
		}
		
		if (pitcherCount > 0) {
			pitcherArr = new PitcherDto[pitcherCount];
		} else {
			throw new IllegalArgumentException("투수가 없습니다.");
		}
		
		pitcherCount = 0;
		
		for (int i = 0; i < playerArr.length; i++) {
			HumanDto player = playerArr[i];
			
			if (player != null) {
				if (player instanceof PitcherDto) {
					pitcherArr[pitcherCount] = (PitcherDto)player;
					pitcherCount++;
				}
			}
		}
		
		for (int i = 0; i < pitcherArr.length - 1; i++) {
			for (int j = i + 1; j < pitcherArr.length; j++) {
				double leftHivAvg = pitcherArr[i].getDefence();
				double rightHivAvg = pitcherArr[j].getDefence();
				boolean descCondition = leftHivAvg < rightHivAvg;
				
				if (descCondition) {
					ObjectArrayUtil.swap(pitcherArr, i, j);
				}
			}
		}
		
		for (int i = 0; i < pitcherArr.length; i++) {
			String printStr = (i + 1) + "위 " + pitcherArr[i].toString();
			
			System.out.println(printStr);
		}
	}

	public void print() {
		for (int i = 0; i < playerArr.length; i++) {
			HumanDto player = playerArr[i];
			
			if (player != null) {
				System.out.println(playerArr[i]);
			}
		}
	}

	private int search(String name) {
		int result = -1;

		for (int i = 0; i < playerArr.length; i++) {
			HumanDto player = playerArr[i];
			
			if (player != null && player.getName().equals(name)) {
				result = i;
				break;
			}
		}

		return result;
	}
	
	public void chnageDummyMode() {
		playerArr = new HumanDto[]{
				new PitcherDto(1, "가", 10, 1.1, "투수", 1, 2, 1.2),
				new PitcherDto(2, "나", 20, 2.1, "투수", 2, 3, 2.3),
				new PitcherDto(3, "다", 30, 3.1, "투수", 3, 4, 3.4),
				new PitcherDto(4, "라", 40, 4.1, "투수", 4, 5, 4.5),
				new PitcherDto(5, "마", 50, 5.1, "투수", 5, 6, 5.6),
				new BatterDto(6, "바", 60, 6.1, "타자", 6, 7, 6.7),
				new BatterDto(7, "사", 70, 7.1, "타자", 7, 8, 7.8),
				new BatterDto(8, "아", 80, 8.1, "타자", 8, 9, 8.9),
				new BatterDto(9, "자", 90, 9.1, "타자", 9, 10, 9.1),
				new BatterDto(10, "차", 100, 10.1, "타자", 10, 11, 10.11),
		};
	}
	
	public void save() {
		// 실제로 삭제된 데이터를 제외한 (정상적인)데이터가 몇개?
		int ci = 0;
		for (int i = 0; i < playerArr.length; i++) {
			if (playerArr[i] != null 
					&& !playerArr[i].getName().equals("")) {
				ci++;
			}
		}

		// 배열
		String[] arr = new String[ci];
		
		int j = 0;
		for (int i = 0; i < playerArr.length; i++) {
			if (playerArr[i] != null 
					&& !playerArr[i].getName().equals("")) {
				arr[j] = playerArr[i].toString();
				j++;
			}
		}
		
		fileio.dataSave(arr);
	}

	public void load() {
		String[] arr = fileio.dataLoad();

		if (arr == null || arr.length == 0) {
			counter = 0;
			return;
		}

		// (추가될)다음 데이터의 index
		counter = arr.length;
		
		int arrlength = arr.length; 
		HumanDto[] dtoArr = new HumanDto[arrlength];
		
		// string[] -> studentDto[]
		for (int i = 0; i < arr.length; i++) {
			dtoArr[i] = ParseHumanStr(arr[i]);
		}

		playerArr = dtoArr;
		
		System.out.println("데이터로드 성공!");
		System.out.println("변경된 코드");
	}
	
	private HumanDto ParseHumanStr(String inputStr) {
		HumanDto dto = null;
		
		String flagStr = inputStr.split("-")[4];
		
		switch(flagStr) {
			case "투수":
				return parsePitcherStr(inputStr);
			case "타자":
				return parseBatterStr(inputStr);
			default:
				break;
		}
		
		return dto;
	}
	
	private PitcherDto parsePitcherStr(String inputStr) {
		// 문자열 자르기
		String[] split = inputStr.split("-");

		// 자른 문자열을 dto에 저장하기 위한 처리
		int number = Integer.parseInt(split[0]);
		String name = split[1];
		int age = Integer.parseInt(split[2]);
		double height = Double.parseDouble(split[3]);
		String position = split[4]; 
		int win = Integer.parseInt(split[5]);
		int lose = Integer.parseInt(split[6]);
		double defense = Double.parseDouble(split[7]);

		return new PitcherDto(number, name, age, height, position, win, lose, defense);
	}
	
	private BatterDto parseBatterStr(String inputStr) {
		// 문자열 자르기
		String[] split = inputStr.split("-");

		// 자른 문자열을 dto에 저장하기 위한 처리
		int number = Integer.parseInt(split[0]);
		String name = split[1];
		int age = Integer.parseInt(split[2]);
		double height = Double.parseDouble(split[3]);
		String position = split[4]; 
		int batcount = Integer.parseInt(split[5]);
		int hit = Integer.parseInt(split[6]);
		double hitAvg = Double.parseDouble(split[7]);

		return new BatterDto(number, name, age, height, position, batcount, hit, hitAvg);
	}

}
