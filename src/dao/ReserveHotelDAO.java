package dao;

import common.Common;
import vo.ReservationVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReserveHotelDAO {
    Connection conn = null;
    PreparedStatement psmt = null;
    Statement stmt = null;
    ResultSet rs = null;
    Scanner sc = null;

    public ReserveHotelDAO() {
        sc = new Scanner(System.in);
    }

    // 예약 가능한 방을 조회하고 리스트로 반환
    public List<ReservationVO> reservation(int hotelid, String userID) {
        List<ReservationVO> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("[1]베이직 (1인) [2]스위트 (2인) [3]럭셔리 (4인)");
        int roomType = sc.nextInt();
        String[] type = {"베이직", "스위트", "럭셔리"};
        String selectType = type[roomType - 1];

        System.out.println("체크인 날짜입력 (yyyy-mm-dd) : ");
        String checkin = sc.next();
        System.out.println("체크아웃 날짜입력 (yyyy-mm-dd) : ");
        String checkout = sc.next();

        String query = "SELECT r.roomID, r.hotelID, r.roomType, r.price, r.roomNumber " +
                "FROM room r " +
                "LEFT JOIN reservation v ON r.roomid = v.roomid " +
                "WHERE r.hotelID = " +hotelid+
                "AND r.roomType = '" +selectType+"'"+
                "AND (v.roomid IS NULL OR (v.endDate < TO_DATE('" +checkin+"', 'YYYY-MM-DD') OR v.startDate > TO_DATE('"+checkout+"', 'YYYY-MM-DD')))";


        try {
            conn = Common.getConnection();
//            psmt = conn.prepareStatement(query);
//            psmt.setInt(1, hotelid);
//            psmt.setString(2, selectType);
//            psmt.setString(3, checkin);
//            psmt.setString(4, checkout);
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);

            // 결과를 리스트에 추가
            while (rs.next()) {
                int roomID = rs.getInt("ROOMID");
                int hotelID = rs.getInt("HOTELID");
                String roomTypeStr = rs.getString("ROOMTYPE");
                int price = rs.getInt("PRICE");
                int roomNumber = rs.getInt("ROOMNUMBER");

                ReservationVO vo = new ReservationVO(roomID, hotelID, roomTypeStr, price, roomNumber);
                list.add(vo);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Common.close(rs);
            Common.close(psmt);
            Common.close(conn);
        }

        return list; // 조회된 결과 리스트 반환
    }

    // 예약 가능한 방 목록 출력
    public void reserveRoom(List<ReservationVO> list) {
        System.out.println("---------------------------------------");
        System.out.println("             예약 가능한 방 리스트");
        System.out.println("---------------------------------------");

        for (ReservationVO e : list) {
            System.out.print(e.getRoomID() + " ");
            System.out.print(e.getHotelID() + " ");
            System.out.print(e.getRoomType() + " ");
            System.out.print(e.getPrice() + " ");
            System.out.print(e.getRoomNumber() + " ");
            System.out.println();
        }

        System.out.println("---------------------------------------");
    }
}
