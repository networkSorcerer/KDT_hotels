package dao;


import common.Common;
import vo.HotelVO;


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HotelDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    Scanner sc = new Scanner(System.in);

    public List<HotelVO> hotelSelectAll() {
        List<HotelVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM HOTEL";
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
        System.out.println("추가할 호텔 정보를 입력 하세요.");
        System.out.print("호텔고유번호(4자리) : ");
        int hotelNo = sc.nextInt();
        sc.nextLine();
        System.out.print("이름 : ");
        String hotelName = sc.nextLine();
        System.out.print("지역 : ");
        String hotelRegion = sc.nextLine();
        System.out.print("전화번호 : ");
        String hotelPhone = sc.nextLine();
        System.out.print("추가 설명 : ");
        String hotelExpl = sc.nextLine();

        String sql = "INSERT INTO HOTEL(HOTELID, HOTELNAME, REGION, PHONE, HOTELEXPL) VALUES(?,?,?,?,?)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, hotelNo);
            pstmt.setString(2, hotelName);
            pstmt.setString(3, hotelRegion);
            pstmt.setString(4, hotelPhone);
            pstmt.setString(5, hotelExpl);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }
    public void hotelDelete() {
        String sqlDel = "DELETE FROM HOTEL WHERE HOTELID = ?";
        List<Integer> list = new ArrayList<>();
        int hotelID = 0;
        int check =0;
        try {
            conn = Common.getConnection();
            while (true) {
                stmt = conn.createStatement();
                rs2 = stmt.executeQuery("SELECT HOTELID FROM HOTEL");
                while (rs2.next()) {
                    int HotelID = rs2.getInt("HOTELID");
                    list.add(HotelID);
                }
                System.out.print("삭제할 호텔의 고유번호를 입력해 주십시오 : ");
                hotelID = sc.nextInt();

                boolean isHotelIDIn = list.contains(hotelID);
                if (!isHotelIDIn) {
                    System.out.println("고유번호에 해당하는 호텔이 없습니다.");
                    break;
                }

                rs = stmt.executeQuery("SELECT HOTELNAME FROM HOTEL WHERE HOTELID =" + hotelID);
                while (rs.next()) {
                    String name = rs.getString("HOTELNAME");
                    System.out.print(name + "가 리스트에서 삭제하고 싶은 호텔의 이름이 맞습니까? [1]예 [2]아니오 [3]돌아가기");
                }
                check = sc.nextInt();
                if (check == 1) {
                    pstmt = conn.prepareStatement(sqlDel);
                    pstmt.setInt(1, hotelID);
                    pstmt.executeUpdate();
                    System.out.println(rs + "가 호텔 리스트에서 삭제되었습니다.");
                    break;
                } else if (check == 3) break;
                else if (check != 2) System.out.println("잘못 입력하셨습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
        Common.close(rs);
        Common.close(stmt);
    }
    public void hotelUpdate() {
        List<Integer> list = new ArrayList<>();
        int HotelID = 0;
        int check = 0;
        try {
            conn = Common.getConnection();
            while (true) {
                stmt = conn.createStatement();
                rs2 = stmt.executeQuery("SELECT HOTELID FROM HOTEL");
                while (rs2.next()) {
                    HotelID = rs2.getInt("HOTELID");
                    list.add(HotelID);
                }
                System.out.print("수정할 호텔의 고유번호를 입력해 주세요.");
                int hotelID = sc.nextInt();

                boolean isHotelIDIn = list.contains(hotelID);
                if (!isHotelIDIn) {
                    System.out.println("고유번호에 해당하는 호텔이 없습니다.");
                    break;
                }
                rs = stmt.executeQuery("SELECT HOTELNAME FROM HOTEL WHERE HOTELID =" + hotelID);
                while (rs.next()) {
                    String name = rs.getString("HOTELNAME");
                    System.out.print(name + "가 리스트에서 수정 하고 싶은 호텔의 이름이 맞습니까? [1]예 [2]아니오 [3]돌아가기");
                }
                check = sc.nextInt();

                if (check == 1) {
                    System.out.print("호텔 이름 : ");
                    String hotelName = sc.nextLine();
                    System.out.print("호텔 지역 : ");
                    String hotelRegion = sc.nextLine();
                    System.out.print("호텔 전화 번호 : ");
                    String hotelPhone = sc.nextLine();
                    System.out.print("호텔 상세 정보 : ");
                    String hotelExpl = sc.nextLine();

                    String sql = "UPDATE HOTEL SET HOTELNAME = ?, REGION = ?, PHONE = ?, HOTELEXPL = ? WHERE HOTELID = ?";

                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, hotelName);
                    pstmt.setString(2, hotelRegion);
                    pstmt.setString(3, hotelPhone);
                    pstmt.setString(4, hotelExpl);
                    pstmt.setInt(5, hotelID);
                    pstmt.executeUpdate();
                } else if (check == 3) break;
                else if (check != 2) System.out.println("잘못 입력하셨습니다.");

            }
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
            System.out.println();
        }
    }

}





















