package dao;

import vo.UsersVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommonUserLoginDAO {
    List<UsersVO> list = new ArrayList<>();
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    public static String nick;
    Scanner sc = new Scanner(System.in);

    public List<UsersVO> signIn() {

        return List.of();
    }

}
