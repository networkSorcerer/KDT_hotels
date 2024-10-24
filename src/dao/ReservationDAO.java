package dao;

import common.Common;
import vo.ReservationVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    // 유저아이디 가져와서 해당 유저의 예약리스트 출력
    public List<ReservationVO> reservationList(String id){
        List<ReservationVO> list = new ArrayList<>();
        String query = "SELECT R.RESERVEID, R.USERID, R.HOTELID, H.HOTELNAME, R.STARTDATE, R.ENDDATE, R.ROOMID " +
                "FROM RESERVATION R LEFT OUTER JOIN HOTEL H " +
                "ON R.HOTELID = H.HOTELID" +
                "WHERE USERID = ?";

        try{
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery(query);

            while(rs.next()) {
                int reserveID = rs.getInt("RESERVEID");
                String userID = rs.getString("USERID");
                int hotelID = rs.getInt("HOTELID");
                String hotelName = rs.getString("HOTELNAME");
                Date startDate = rs.getDate("STARTDATE");
                Date endDate = rs.getDate("ENDDATE");
                int roomID = rs.getInt("ROOMID");

                ReservationVO vo = new ReservationVO(reserveID, userID, hotelID, hotelName, startDate, endDate, roomID);
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

    // 예약하기
    public boolean reservationInsert(ReservationVO vo){
        String sql = "INSERT INTO RESERVATION VALUES (RESERVATION_SEQ.NEXTVAL,?,?,?,?,?)";

        try{
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getUserID());
            pstmt.setInt(2, vo.getHotelID());
            pstmt.setDate(3, vo.getStartDate());
            pstmt.setDate(4, vo.getEndDate());
            pstmt.setInt(5, vo.getRoomID());
            pstmt.executeUpdate();  // insert, update, delete에 해당하는 함수
            return true;
        } catch (SQLException e) {
            System.out.println("INSERT 에러 방생.");
            return false;
        } finally {
            Common.close(pstmt);
            Common.close(conn);
        }
    }

    // 예약 수정
    public boolean reservationUpdate(ReservationVO vo){
        String sql = "UPDATE RESERVATION SET STARTDATE = ?, ENDDATE = ?, ROOMID = ? WHERE RESERVEID = ?";

        try{
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, vo.getStartDate());
            pstmt.setDate(2, vo.getEndDate());
            pstmt.setInt(3, vo.getRoomID());
            pstmt.setInt(4, vo.getReserveID());
            pstmt.executeUpdate();  // insert, update, delete에 해당하는 함수
            return true;
        } catch (SQLException e) {
            System.out.println("UPDATE 에러 방생.");
            return false;
        } finally {
            Common.close(pstmt);
            Common.close(conn);
        }
    }

    // 예약 취소
    public void reservationDelete(int reserveID){
        String sql = "DELETE FROM RESERVATION WHERE RESERVEID = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reserveID);
            pstmt.executeUpdate();  // insert, update, delete에 해당하는 함수
        } catch (SQLException e) {
            System.out.println("DELETE 에러 방생.");
        } finally {
            Common.close(pstmt);
            Common.close(conn);
        }
    }

    // 유저 예약리스트 출력구문
    public void reservationListResult(List<ReservationVO> list) {
        for(ReservationVO e : list){
            System.out.printf("No: %-10d|호텔: %20s|호실: %-6s|" +
                    "체크인: %tY-%tm-%td| 체크아웃: %ty-%tm-%td\n", e.getReserveID(), e.getHotelName(), e.getRoomID(), e.getStartDate(), e.getStartDate(), e.getStartDate(), e.getEndDate(), e.getEndDate(), e.getEndDate());
        }
    }
}
