package dao;

import common.Common;

import vo.ReservationVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static dao.MenuListDAO.getValidDate;

public class ReserveHotelDAO {
    Connection conn = null;
    PreparedStatement psmt = null;
    Statement stmt = null;
    ResultSet rs = null;
    static String startD;
    static String endD;
    Scanner sc = new Scanner(System.in);



    // 예약 가능한 방을 조회하고 리스트로 반환
    public List<ReservationVO> reservation(int hotelid) {

        //Scanner sc =new Scanner(System.in);
        List<ReservationVO> list = new ArrayList<>();

        System.out.println("[1]베이직 (1인) [2]스위트 (2인) [3]럭셔리 (4인)");
        int roomType = sc.nextInt();
        System.out.println();
        String[] type = {"베이직", "스위트", "럭셔리"};
        String selectType = type[roomType - 1];

        startD = getValidDate(sc, "시작 날짜를 입력하세요 (형식: YYYY-MM-DD): ");
        endD = getValidDate(sc, "종료 날짜를 입력하세요 (형식: YYYY-MM-DD): ");

        System.out.println("시작 날짜: " + startD);
        System.out.println("종료 날짜: " + endD);

        System.out.println(startD);
        System.out.println(endD);
        String query = "SELECT r.roomID, r.hotelID, r.roomType, r.price, r.roomNumber " +
                "FROM room r " +
                "WHERE r.hotelID = " + hotelid + " " +
                "AND r.roomType = '" + selectType + "' " +
                "AND NOT EXISTS ( " +
                "    SELECT 1 " +
                "    FROM reservation v " +
                "    WHERE v.roomid = r.roomid " +
                "    AND ( " +
                "        (v.startDate < TO_DATE('" + endD + "', 'YYYY-MM-DD') AND " +
                "         v.endDate > TO_DATE('" + startD + "', 'YYYY-MM-DD')) " +
                "    ) " +
                ")";

        try {
            conn = Common.getConnection();
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
            Common.close(stmt);
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

    public boolean BookARoom1(int br,int hotelid, String userID) {
        String sql = "INSERT INTO reservation (reserveID, userID, hotelID, roomID, startDate, endDate) " +
                "VALUES (reservation_seq.nextval, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'))";
        // Reserve DAO 객체 통해서
        // getter setter 통해서
//        ReserveHotelDAO dao = new ReserveHotelDAO();
//        startD = dao.startD;
//        endD = dao.endD;
        System.out.println(startD);
        System.out.println(endD);
        try {
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, userID);
            psmt.setInt(2, hotelid);
            psmt.setInt(3, br);
            psmt.setString(4, startD);
            psmt.setString(5, endD);
            psmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 예외 발생 시 false 반환
        } finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
//    public List<ReservationVO> updateRoom(String userid, int hotelid, int reserveNo) {
//        List<ReservationVO> list = new ArrayList<>();  // 추가: ReservationVO 리스트 초기화
//        String sql = "update reservation set roomid = ?, userid = ?, hotelid = ?, startdate = ?, enddate = ? " +
//                "where reserveid = ?";
//        String query = "select * from room where hotelid = " + hotelid;
//
//        try {
//            // 하나의 Connection 객체만 생성
//            conn = Common.getConnection();
//            stmt = conn.createStatement();
//            psmt = conn.prepareStatement(sql);
//            rs = stmt.executeQuery(query);
//
//            // Room 정보 리스트에 추가
//            while (rs.next()) {
//                int roomID = rs.getInt("ROOMID");
//                int hotelID = rs.getInt("HOTELID");
//                String roomTypeStr = rs.getString("ROOMTYPE");
//                int price = rs.getInt("PRICE");
//                int roomNumber = rs.getInt("ROOMNUMBER");
//
//                ReservationVO vo = new ReservationVO(roomID, hotelID, roomTypeStr, price, roomNumber);
//                list.add(vo);
//            }
//
//            // Scanner 객체를 이용해 입력받기
//            Scanner sc = new Scanner(System.in);
//            System.out.println("원하시는 방ID를 입력하세요");
//            int br = sc.nextInt();
//            sc.nextLine();  // 버퍼 정리
//            System.out.println("체크인 날짜를 선택하세요 (yyyy-mm-dd 형식)");
//            String checkin = sc.nextLine();
//            System.out.println("체크아웃 날짜를 선택하세요 (yyyy-mm-dd 형식)");
//            String checkout = sc.nextLine();
//
//            // 예약 업데이트 실행
//            psmt.setInt(1, br);
//            psmt.setString(2, userid);
//            psmt.setInt(3, hotelid);
//            psmt.setString(4, checkin);  // Date 형식으로 변환하는 것이 좋음
//            psmt.setString(5, checkout);
//            psmt.setInt(6, reserveNo);
//            psmt.executeUpdate();
//
//            System.out.println("예약 정보가 성공적으로 업데이트되었습니다.");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Common.close(rs);
//            Common.close(stmt);
//            Common.close(psmt);
//            Common.close(conn);
//        }
//        return list;
//    }
//
//
}
