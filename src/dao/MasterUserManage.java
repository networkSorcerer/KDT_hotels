package dao;

import vo.UsersVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

// 관리자 메뉴 - 회원관리
public class MasterUserManage {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);
    UsersDAO usersDao;

    public void masterUserManage(){
        while(true){
            System.out.println("<회원 관리>");
            System.out.println("[1] 회원 목록");
            System.out.println("[2] 관리자 등록");
            System.out.println("[3] 회원 삭제");
            System.out.println("[4] 뒤로가기");
            int select = sc.nextInt();

            if(select == 1){
                List<UsersVO> list = usersDao.usersList();
                usersDao.usersListResult(list);
            }else if(select == 2){
                masterUserInsert();
            }else if(select == 3){
                masterUsersDelete();
            }else if(select == 4){
                break;
            }else{
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 관리자 등록
    public void masterUserInsert(){
        System.out.println("<관리자 등록>");
        System.out.print("ID: ");
        String id = sc.next();
        System.out.print("PW: ");
        String pw = sc.next();
        System.out.print("이름: ");
        String name = sc.next();
        System.out.print("나이: ");
        int age = sc.nextInt();
        System.out.print("email: ");
        String email = sc.next();
        int grade = 1;

        UsersVO usersVO = new UsersVO(id, pw, name, age, email, grade);
        if(usersDao.usersInsert(usersVO)){
            System.out.println("관리자 등록이 완료되었습니다.");
        }else {
            System.out.println("관리자 등록이 실패했습니다.");
        }
    }

    // 관리자 - 회원 삭제
    public void masterUsersDelete(){
        System.out.println("<회원 삭제>");
        System.out.print("ID: ");
        String id = sc.next();
        if(usersDao.usersDelete(id)){
            System.out.println("회원 삭제가 완료되었습니다.");
        }else {
            System.out.println("회원 삭제가 실패했습니다.");
        }
    }
}
