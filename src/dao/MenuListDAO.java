package dao;

import common.Common;
import vo.UsersVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuListDAO {
    Scanner sc = new Scanner(System.in);
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    MasterMenuDAO masterMenuDAO = new MasterMenuDAO();
    List<UsersVO> list = new ArrayList<>();

    public void LoginMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("===== Main table =====");
            System.out.println("메뉴를 선택하세요 : ");
            System.out.println("[1]로그인 [2]회원가입 [3]종료 : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 :
                    list = signIn();
                    break;
                case 2 :
                    SignUp();
                    break;
                case 3 :
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }
    }

    public List<UsersVO>signIn() throws SQLException {
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
    public void SignUp() {  //회원 가입 메소드
        System.out.println("=".repeat(10) + "회원가입" + "=".repeat(10));
        System.out.print("ID 입력 (알파벳, 숫자 포함 10자 제한) : ");
        String id = sc.next();
        String pwd = null;
        while (true){
            System.out.print("PASSWORD 입력 (알파벳, 숫자, 특수기호 포함 15자 제한) : ");
            pwd = sc.next();
            System.out.println(checkPassword(pwd,id));
            if(checkPassword(pwd,id) == ""){break;}
        }
        System.out.print("성명 : ");
        String name = sc.next();
        System.out.print("나이 : ");
        int age = sc.nextInt();
        System.out.print("email : ");
        String email = sc.next();


        String sql = "INSERT INTO users(userID , password ,name, age  , email , grade ) VALUES(?,?,?,?,?)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            pstmt.setString(3, name);
            pstmt.setInt(4, age);
            pstmt.setString(5, email);
            pstmt.setInt(6, 0);

            int rst = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("회원가입이 완료되었습니다. 환영합니다. ");
        Common.close(pstmt);
        Common.close(conn);
    }
    private String checkPassword(String pwd, String id){
        // 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상)
        Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        Matcher passMatcher1 = passPattern1.matcher(pwd);

        if(!passMatcher1.find()){
            return "비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다.";
        }

        // 반복된 문자 확인
        Pattern passPattern2 = Pattern.compile("(\\w)\\1\\1\\1");
        Matcher passMatcher2 = passPattern2.matcher(pwd);

        if(passMatcher2.find()){
            return "비밀번호에 동일한 문자를 과도하게 연속해서 사용할 수 없습니다.";
        }

        // 아이디 포함 확인
        if(pwd.contains(id)){
            System.out.println("비밀번호에 ID를 포함할 수 없습니다.");
        }

        // 특수문자 확인
        Pattern passPattern3 = Pattern.compile("\\W");
        Pattern passPattern4 = Pattern.compile("[!@#$%^*+=-]");

        for(int i = 0; i < pwd.length(); i++){
            String s = String.valueOf(pwd.charAt(i));
            Matcher passMatcher3 = passPattern3.matcher(s);

            if(passMatcher3.find()){
                Matcher passMatcher4 = passPattern4.matcher(s);
                if(!passMatcher4.find()){
                    return "비밀번호에 특수문자는 !@#$^*+=-만 사용 가능합니다.";
                }
            }
        }

        //연속된 문자 확인
        int ascSeqCharCnt = 0; // 오름차순 연속 문자 카운트
        int descSeqCharCnt = 0; // 내림차순 연속 문자 카운트

        char char_0;
        char char_1;
        char char_2;
        int diff_0_1;
        int diff_1_2;

        for(int i = 0; i < pwd.length()-2; i++){
            char_0 = pwd.charAt(i);
            char_1 = pwd.charAt(i+1);
            char_2 = pwd.charAt(i+2);

            diff_0_1 = char_0 - char_1;
            diff_1_2 = char_1 - char_2;

            if(diff_0_1 == 1 && diff_1_2 == 1){
                ascSeqCharCnt += 1;
            }

            if(diff_0_1 == -1 && diff_1_2 == -1){
                descSeqCharCnt -= 1;
            }
        }

        if(ascSeqCharCnt > 1 || descSeqCharCnt > 1){
            return "비밀번호에 연속된 문자열을 사용할 수 없습니다.";
        }

        return "";
    }
}
