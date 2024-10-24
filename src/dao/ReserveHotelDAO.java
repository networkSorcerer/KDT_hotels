package dao;

import vo.ReservationVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReserveHotelDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner sc = null;
    public ReserveHotelDAO() {
        sc = new Scanner(System.in);
    }
    public boolean reservation(int hotelid) {
        List<ReservationVO> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("[1]베이직 (1인) [2]스위트 (2인) [3]럭셔리 (4인)");
        int roomType =sc.nextInt();
        String[] regions = {};
        String selectedRegion = regions[choice - 1]; // 선택한 지역의 이름


        System.out.println("체크인 날짜입력 (yyyy-mm-dd) : ");
        String checkin = sc.next();
        System.out.println("체크아웃 날짜입력 (yyyy-mm-dd) : ");
        String checkout = sc.next();

        return false;
    }
}
