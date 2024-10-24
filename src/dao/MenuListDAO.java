package dao;

import common.Common;
import vo.UsersVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuListDAO {
    Scanner sc = new Scanner(System.in);
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    MasterMenuDAO masterMenuDAO = new MasterMenuDAO();
    List<UsersVO> list = new ArrayList<>();

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

    public List<UsersVO>signIn() throws SQLException {
        MenuListDAO menuListDAO = new MenuListDAO();
        System.out.println("=".repeat(10)+"L O G I N"+"=".repeat(10));
        System.out.print("id :");
        String inputID = sc.next();
        System.out.print("pw :");
        String inputPW = sc.next();
        if(inputID == "S2222" && inputPW == "2222"){
            masterMenuDAO.MasterMenu();
        }
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "select *from users where where id ='"+inputID+"'and password ='" + inputPW+"'";
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

}
