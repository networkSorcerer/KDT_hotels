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
        int hotelNo = sc.nextInt();
        System.out.print("이름 : ");
        String hotelName = sc.next();
        System.out.print("지역 : ");
        String hotelRegion = sc.next();
        System.out.print("전화번호 : ");
        String hotelPhone = sc.next();
        System.out.print("추가 설명 : ");
        String hotelExpl = sc.next();

        String sql = "INSERT INTO HOTEL(HOTELID, HOTELNAME, REGION, PHONE, HOTELXPL) + VALUES(?,?,?,?,?)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, hotelNo);
            pstmt.setString(2, hotelName);
            pstmt.setString(3, hotelRegion);
            pstmt.setString(4, hotelPhone);
            pstmt.setString(5, hotelExpl);


        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }
    public void hotelDelete() {
        String sqlName = "SELECT NAME FROM HOTEL WHERE HOTELID = ?";
        String sqlDel = "DELETE FROM HOTEL WHERE HOTELID = ?";

        try {
            conn = Common.getConnection();
            int hotelID;
            while (true) {
                System.out.print("삭제할 호텔의 고유번호를 입력해 주십시오 : ");
                hotelID = sc.nextInt();
                pstmt = conn.prepareStatement(sqlName);
                pstmt.setInt(1, hotelID);
                rs = pstmt.executeQuery();

                System.out.println();
                System.out.print(rs + "가 리스트에서 삭제하고 싶은 호텔의 이름이 맞습니까? 맞으면 숫자 1을 입력해 주십시오");
                int check = sc.nextInt();

                if (check == 1) break;
            }
            pstmt = conn.prepareStatement(sqlDel);
            pstmt.setInt(1, hotelID);
            pstmt.executeUpdate();
            System.out.println(rs + "가 호텔 리스트에서 삭제되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
        Common.close(rs);
    }
    public void hotelUpdate() {
        System.out.print("변경할 호텔의 고유번호를 입력해 주세요.");
        int hotelID = sc.nextInt();
        System.out.print("호텔 이름 : ");
        String hotelName = sc.next();
        System.out.print("호텔 지역 : ");
        String hotelRegion = sc.next();
        System.out.print("호텔 전화 번호 : ");
        String hotelPhone = sc.next();
        System.out.print("호텔 상세 정보 : ");
        String  hotelExpl = sc.next();

        String sql = "UPDATE HOTEL SET HOTELNAME = ?, REGION = ?, PHONE = ?, HOTELEXPL = ? WHERE HOTELID = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, hotelName);
            pstmt.setString(2, hotelRegion);
            pstmt.setString(3, hotelPhone);
            pstmt.setString(4, hotelExpl);
            pstmt.setInt(5, hotelID);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }

    public void hotelSelectRst(List<HotelVO> list){
        for(HotelVO e : list) {
            System.out.print(e.getHotelID() + " ");
            System.out.print(e.getHotelName() + " ");
            System.out.print(e.getRegion() + " ");
            System.out.print(e.getPhone() + " ");
            System.out.print(e.getHotelExpl() + " ");
        }
    }

}





















