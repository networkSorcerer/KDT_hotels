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
                "AND (v.startDate <= TO_DATE(?, 'YYYY-MM-DD') AND v.endDate >= TO_DATE(?, 'YYYY-MM-DD')) " +
                "WHERE r.hotelID = ? " +
                "AND r.roomType = ? " +
                "AND v.roomid IS NULL";

        try {
            conn = Common.getConnection();
            psmt = conn.prepareStatement(query);
            psmt.setString(1, checkin);
            psmt.setString(2, checkout);
            psmt.setInt(3, hotelid);
            psmt.setString(4, selectType);

            rs = psmt.executeQuery();

            // 결과를 리스트에 추가
            while (rs.next()) {
                int roomID = rs.getInt("roomID");
                int hotelID = rs.getInt("hotelID");
                String roomTypeStr = rs.getString("roomType");
                int price = rs.getInt("price");
                int roomNumber = rs.getInt("roomNumber");

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
    public void reserveRoom() {
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
