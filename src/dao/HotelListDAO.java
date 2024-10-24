package dao;

import common.Common;
import vo.HotelVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelListDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner sc = null;

    public HotelListDAO() {

        sc = new Scanner(System.in);
    }
    public List<HotelVO> hotelSelect() throws SQLException {
        List<HotelVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection(); // 오라클 DB 연결
            stmt = conn.createStatement();  // statement 생성
            String query = "select * from hotel";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int hotelID = rs.getInt("hotelID");
                String hotelName = rs.getString("hotelName");
                String region = rs.getString("region");
                String phone = rs.getString("phone");
                String hotelExpl = rs.getString("hotelExpl");
                HotelVO vo = new HotelVO(hotelID, hotelName, region, phone, hotelExpl);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            System.out.println("Hotel 조회 실패 !!!");
            e.printStackTrace();
        }
        return list;
    }
    public void hotelSelectResult(List<HotelVO>list) {
        System.out.println("---------------------------------------");
        System.out.println("             호텔 정보");
        System.out.println("---------------------------------------");

        for(HotelVO e : list){
            System.out.print(e.getHotelID() + " ");
            System.out.print(e.getHotelName() + " ");
            System.out.print(e.getRegion() + " ");
            System.out.print(e.getPhone() + " ");
            System.out.print(e.getHotelExpl() + " ");
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }
}
