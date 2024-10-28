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
    public boolean usersDelete(String userID){
        String sql = "DELETE FROM USERS WHERE USERID = ?";
        List<String> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT USERID FROM USERS WHERE USERID = '"+userID+"'");
            pstmt.setString(1, userID);
            pstmt.executeUpdate();  // insert, update, delete에 해당하는 함수
            while (rs.next()) {
                String delName = rs.getString("USERID");
                list.add(delName);
            }
            if (list.contains(userID)){
                return true;
            } else {
                System.out.println("존재하지 않는 유저 아이디 입니다.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("DELETE 에러 발생.");
            e.printStackTrace();
            return false;
        } finally {
            Common.close(pstmt);
            Common.close(conn);
            Common.close(stmt);
            Common.close(rs);
        }
        return false;
    }

    // 유저리스트 출력구문
    public void usersListResult(List<UsersVO> list){
        for(UsersVO e : list){
            System.out.printf("ID: %-10s|이름: %-8s|나이: %-2d|email: %-20s|등급: %1d\n",
                    e.getUserID(), e.getName(), e.getAge(), e.getEmail(), e.getGrade());
        }
    }
    //마스터 유저 등록
    public boolean masterInsert(){
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?)";
        System.out.print("등록할 관리자의 ID를 입력해 주십시오 : ");
        String masterId = sc.next();
        System.out.print("등록할 관리자의 PW를 입력해 주십시오 : ");
        String materPw = sc.next();
        System.out.print("등록할 관리자의 이름을 입력해 주십시오 : ");
        String materName = sc.next();
        System.out.print("등록할 관리자의 나이를 입력해 주십시오 : ");
        int materAge = sc.nextInt();
        System.out.print("등록할 관리자의 e메일을 입력해 주세요 : ");
        String materEmail = sc.next();
        try{
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, masterId);
            pstmt.setString(2, materPw);
            pstmt.setString(3, materName);
            pstmt.setInt(4, materAge);
            pstmt.setString(5, materEmail);
            pstmt.setInt(6, 1);
            pstmt.executeUpdate();  // insert, update, delete에 해당하는 함수
            return true;
        } catch (SQLException e) {
            System.out.println("Master User INSERT 에러 발생.");
            return false;
        } finally{
            Common.close(pstmt);
            Common.close(conn);
        }
    }

}

