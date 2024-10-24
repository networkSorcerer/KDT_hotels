package dao;

import java.util.Scanner;

public class MenuListDAO {
    Scanner sc = new Scanner(System.in);

    public void LoginMenu() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("===== Main table =====");
            System.out.println("메뉴를 선택하세요 : ");
            System.out.println("[1]로그인 [2]회원가입 [3]종료 : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 :

                case 2 :

                case 3 :
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }
    }


}
