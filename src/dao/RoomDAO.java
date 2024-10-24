package dao;

import common.Common;
import vo.RoomVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    // 예약하기 - 방종류 리스트
    public List<RoomVO> roomList() {
        List<RoomVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM ROOM";
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                int roomID = rs.getInt("ROOMID");
                int hotelID = rs.getInt("HOTELID");
                String roomType = rs.getString("ROOMTYPE");
                int price = rs.getInt("PRICE");
                int roomNumber = rs.getInt("ROOMNUMBER");

                RoomVO vo = new RoomVO(roomID, hotelID, roomType, price, roomNumber);
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

    // 방종류 출력구문
    public void roomListResult(List<RoomVO> list){
        for(RoomVO e : list){
            System.out.printf("[%-6d] %-10s > %-10d", e.getRoomNumber(), e.getRoomType(), e.getPrice());
        }
    }
}
