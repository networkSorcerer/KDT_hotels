package dao;

import common.Common;
import vo.ReviewVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReviewDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    // 관리자 리뷰리스트
    public List<ReviewVO> reviewListAll() {
        List<ReviewVO> list = new ArrayList<>();
        String query = "SELECT * FROM REVIEWS";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()){
                int reviewID = rs.getInt("REVIEWID");
                int hotelID = rs.getInt("HOTELID");
                String userID = rs.getString("USERID");
                String content = rs.getString("CONTENT");
                int star = rs.getInt("STAR");

                ReviewVO vo = new ReviewVO(reviewID, hotelID, userID, content, star);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SELECT 에러1 발생.");
        }
        return list;
    }

    // 유저 호텔 상세보기 - 리뷰리스트
    // 호텔 상세보기 시 호텔 번호 가져와서 해당 리뷰 리스트 출력함
    public List<ReviewVO> hotelReviewList(int hotelNo){
        List<ReviewVO> list = new ArrayList<>();
        String query = "SELECT * FROM REVIEW WHERE HOTELID = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, hotelNo);
            rs = pstmt.executeQuery(query);

            while(rs.next()){
                int reviewID = rs.getInt("REVIEWID");
                int hotelID = rs.getInt("HOTELID");
                String userID = rs.getString("USERID");
                String content = rs.getString("CONTENT");
                int star = rs.getInt("STAR");

                ReviewVO vo = new ReviewVO(reviewID, hotelID, userID, content, star);
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

    // 유저 리뷰 등록
    public boolean reviewInsert(ReviewVO vo){
        String sql = "INSERT INTO REVIEW VALUES(" +
                "REVIEWS_SEQ.NEXTVAL,?,?,?,?)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.getHotelID());
            pstmt.setString(2, vo.getUserID());
            pstmt.setString(3, vo.getContent());
            pstmt.setInt(4, vo.getStar());
            pstmt.executeUpdate();  // insert, update, delete에 해당하는 함수
            return true;
        } catch (SQLException e) {
            System.out.println("INSERT 에러 방생.");
            return false;
        } finally{
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        }
    }

    // 관리자 - 리뷰 삭제

    public void reviewDelete(int reviewID){
        String sql = "DELETE FROM REVIEWS WHERE REVIEWID = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reviewID);
            pstmt.executeUpdate();  // insert, update, delete에 해당하는 함수
            return true;
        } catch (SQLException e) {
            System.out.println("DELETE 에러 발생.");
            return false;
        } finally {
            Common.close(pstmt);
            Common.close(conn);
        }
    }

    // 관리자 리뷰 리스트 출력구문
    public void reviewListAllResult(List<ReviewVO> list){
        for(ReviewVO e : list){
            System.out.printf("No: %-4d|호텔번호: %-4d|유저ID: %-10s|평점: %1d|내용: %50s\n", e.getReviewID(), e.getHotelID(), e.getUserID(), e.getStar(), e.getContent());
        }
    }

    // 호텔 상세보기 - 리뷰 리스트
    public void hotelReviewListResult(List<ReviewVO> list){
        for(ReviewVO e : list){
            String IDstr = e.getUserID().substring(0, 4) + "**";
            System.out.printf("%5s(평점:%1d): %50s", IDstr, e.getStar(), e.getContent());
        }
    }
}
