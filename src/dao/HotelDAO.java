package dao;


import common.Common;
import vo.HotelVO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<HotelVO> HotelSelectAll() {
        List<HotelVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM EMP";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int hotelID = rs.getInt("HOTELID");
                String hotelName = rs.getString("HOTELNAME");
                String region = rs.getString("REGION");
                String phone = rs.getString("PHONE");
                String hotelExpl = rs.getString("HOTELEXPL");

                HotelVO vo = new HotelVO(hotelID, hotelName, region, phone, hotelExpl);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void hotelInsert() {
        System.out.println("호텔 정보를 입력 하세요.");
        System.out.print("호텔고유번호(4자리) : ");
        int hNo = sc.nextInt();
        System.out.print("이름 : ");
        String hName = sc.next();
        System.out.print("지역 : ");
        String hRegion = sc.next();
        System.out.print("추가 설명 : ");
        String hExpl = sc.next();

        String sql = "INSERT INTO HOTEL(HOTELID, HOTELNAME, REGION, PHONE, HOTELXPL) + VALUES(?,?,?,?,?)";
        
    }
}
