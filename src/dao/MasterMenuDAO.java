package dao;

import common.Common;
import vo.ReviewVO;
import vo.UsersVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MasterMenuDAO {



    public static void MasterMenu(){
        Scanner sc = new Scanner(System.in);
        MasterMenuDAO mDao = new MasterMenuDAO();

        boolean isMasterLogin;
        isMasterLogin = MasterLogin();
        if (!isMasterLogin) {
            System.out.println("관리자 로그인 실패");
        } else {
            while (true) {
                System.out.println("=====================");
                System.out.println("관리자 메뉴를 선택하세요.");
                System.out.println("[1]호텔관리 [2]리뷰관리 [3]회원관리 [4]로그아웃");
              
                int menuSel = sc.nextInt();
                switch (menuSel) {
                    case 1:
                        HotelDAO.MaterHotelMenu();
                        break;
                    case 2:
                        mDao.MasterReview();
                        break;
                    case 3:
                        mDao.MasterUserManage();
                        break;
                    case 4:
                        System.out.println("프로그램을 종료합니다.");
                        return;
                }
            }
        }

    }


    public static boolean MasterLogin() {
        Scanner sc = new Scanner(System.in);
        Connection conn;
        Statement stmt;
        ResultSet rs;

        System.out.println("====================");
        System.out.println("관리자 전용 로그인 절차");
        System.out.print("관리자 ID : ");
        String mID = sc.next();
        System.out.print("관리자 PW : ");
        String mPw = sc.next();

        String mLoginsSql = "SELECT USERID FROM USERS WHERE USERID = '"+mID+"' AND PASSWORD = '"+mPw+"' AND GRADE = 1";

        List<String> list = new ArrayList<>();
        String mName = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(mLoginsSql);
            while (rs.next()) {
                mName = rs.getString("USERID");
                list.add(mName);
            }
            if (list.isEmpty()) {
                System.out.println("ID 또는 PW가 일치하지 않습니다.");
                return false;
            }
            System.out.println("어세오세요 관리자 "+mName+"님");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("관리자 로그인 오류");
            return false;
        }
        return true;
    }

    public void MasterReview() {
        Scanner sc = new Scanner(System.in);
        ReviewDAO rDao = new ReviewDAO();
        while (true) {
            System.out.println("=============================================");
            System.out.println("               리뷰 관리 메뉴");
            System.out.println("[1] 모든 리뷰 리스트 출력 [2] 리뷰 삭제 [3]돌아가기");
            int menuSel = sc.nextInt();
            switch (menuSel) {
                case 1:
                    List<ReviewVO> list = rDao.reviewListAll();
                    rDao.reviewListAllResult(list);
                    break;
                case 2:
                    System.out.println("삭제할 리뷰의 고유 ID를 입력해 주십시오 :");
                    int delReview = sc.nextInt();
                    rDao.reviewDelete(delReview);
                    break;
                case 3:
                    return;
            }
        }
    }
    public void MasterUserManage() {
        Scanner sc = new Scanner(System.in);
        UsersDAO uDao = new UsersDAO();
        while (true) {
            System.out.println("===========================================================");
            System.out.println("                       이용자 관리 메뉴");
            System.out.println("[1]모든 회원 리스트 출력 [2]신규 관리자 등록 [3]회원 삭제 [4]돌아가기" );
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    List<UsersVO> list = uDao.usersList();
                    uDao.usersListResult(list);
                    break;
                case 2:
                    if(uDao.masterInsert()) System.out.println("신규 관리자 등록에 성공하였습니다.");
                    else System.out.println("신규 관리자 등록에 실패하였습니다.");
                    break;
                case 3:
                    System.out.print("삭제하고 싶은 유저의 ID를 입력해 주세요 : ");
                    String del = sc.next();
                    uDao.usersDelete(del);
                    break;
                case 4:
                    return;
            }
        }
    }
}
