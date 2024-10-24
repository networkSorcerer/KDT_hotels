import dao.HotelDAO;
import vo.HotelVO;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menuSelect();
    }
    public static void menuSelect() {
        Scanner sc = new Scanner(System.in);
        HotelDAO dao = new HotelDAO();
        while(true) {
            System.out.println("===== Main table =====");
            System.out.println("메뉴를 선택하세요 : ");
            System.out.println("[1]호텔 리스트 [2]호텔 추가 [3]호텔 수정 [4]호텔 삭제 [5]종료 : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 :
                    List<HotelVO> list = dao.hotelSelectAll();
                    dao.hotelSelectRst(list);
                    break;
                case 2 :
                    dao.hotelInsert();
                    break;
                case 3 :
                    dao.hotelUpdate();
                    break;
                case 4:
                    dao.hotelDelete();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }
    }

}