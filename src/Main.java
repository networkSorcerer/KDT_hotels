import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menuSelect();
    }
    public static void menuSelect() {
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