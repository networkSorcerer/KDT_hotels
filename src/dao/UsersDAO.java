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

    // 관리자용 유저 전체 리스트 출력
    public List<UsersVO> usersList(){
        List<UsersVO> list = new ArrayList<>();
        String query = "SELECT * FROM USERS";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()){
                String userID = rs.getString("USERID");
                String password = rs.getString("PASSWORD");
                String name = rs.getString("NAME");
                int age = rs.getInt("AGE");
                String email = rs.getString("EMAIL");
                int grade = rs.getInt("GRADE");

                UsersVO vo = new UsersVO(userID, password, name, age, email, grade);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (SQLException e) {
            System.out.println("SELECT 에러 방생.");
        }
        return list;
    }

    // 유저 회원가입(등록)
    public boolean usersInsert(UsersVO vo){
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?)";

        try{
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getUserID());
            pstmt.setString(2, vo.getPassword());
            pstmt.setString(3, vo.getName());
            pstmt.setInt(4, vo.getAge());
            pstmt.setString(5, vo.getEmail());
            pstmt.setInt(6, vo.getGrade());
            pstmt.executeUpdate();  // insert, update, delete에 해당하는 함수
            return true;
        } catch (SQLException e) {
            System.out.println("INSERT 에러 발생.");
            return false;
        } finally{
            Common.close(pstmt);
            Common.close(conn);
        }
    }

    // 관리자용 유저 삭제
    public void usersDelete(String userID){
        String sql = "DELETE FROM USERS WHERE USER_ID = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            pstmt.executeUpdate();  // insert, update, delete에 해당하는 함수
        } catch (SQLException e) {
            System.out.println("DELETE 에러 발생.");
        } finally {
            Common.close(pstmt);
            Common.close(conn);
        }
    }

    // 유저리스트 출력구문
    public void usersListResult(List<UsersVO> list){
        for(UsersVO e : list){
            System.out.printf("ID: %-10s|이름: %-8s|나이: %-2d|email: %-20s|등급: %1d\n",
                    e.getUserID(), e.getName(), e.getAge(), e.getEmail(), e.getGrade());
        }
    }

}
