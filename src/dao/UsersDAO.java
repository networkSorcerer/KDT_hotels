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



