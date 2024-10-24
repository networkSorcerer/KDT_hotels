package dao;

import common.Common;
import vo.UsersVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    MasterMenuDAO masterMenuDAO = new MasterMenuDAO();
    List<UsersVO> list = new ArrayList<>();
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



    public List<UsersVO> usersList(){
        List<UsersVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT USER_ID, NAME, AGE, EMAIL, GRADE FROM USERS";
            rs = stmt.executeQuery(query);

            while(rs.next()){

            }

        } catch (SQLException e) {

        }
        return list;
    }

}
