package dao;

import common.Common;
import vo.HotelVO;
import vo.UsersVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuListDAO {
    Scanner sc = new Scanner(System.in);
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    MasterMenuDAO masterMenuDAO = new MasterMenuDAO();
    List<UsersVO> list = new ArrayList<>();

    public void LoginMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("===== Main table =====");
            System.out.println("메뉴를 선택하세요 : ");
            System.out.println("[1]로그인 [2]회원가입 [3]종료 : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 :
                    list = signIn();
                    HotelAppMenu(list);
                    break;
                case 2 :
                    SignUp();
                    break;
                case 3 :
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }
    }

    public List<UsersVO>signIn() throws SQLException {

        System.out.println("=".repeat(10)+" L O G I N "+"=".repeat(10));
        System.out.print("id :");
        String inputID = sc.next();
        System.out.print("pw :");
        String inputPW = sc.next();
        if(inputID == "S2222" && inputPW == "2222"){
            masterMenuDAO.MasterMenu();
        }
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "select * from users where userid ='"+inputID+"'and password ='" + inputPW+"'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String userID = rs.getString("userID");
                String password = rs.getString("password");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                int grade = rs.getInt("grade");
                list.add(new UsersVO(userID , password , name , age , email , grade ));

            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
    public void SignUp() {  //회원 가입 메소드
        System.out.println("=".repeat(10) + "회원가입" + "=".repeat(10));
        System.out.print("ID 입력 (알파벳, 숫자 포함 10자 제한) : ");
        String id = sc.next();
        String pwd = null;
        while (true){
            System.out.print("PASSWORD 입력 (알파벳, 숫자, 특수기호 포함 15자 제한) : ");
            pwd = sc.next();
            System.out.println(checkPassword(pwd,id));
            if(checkPassword(pwd,id) == ""){break;}
        }
        System.out.print("성명 : ");
        String name = sc.next();
        System.out.print("나이 : ");
        int age = sc.nextInt();
        System.out.print("email : ");
        String email = sc.next();


        String sql = "INSERT INTO users(userID , password ,name, age  , email , grade ) VALUES(?,?,?,?,?,?)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            pstmt.setString(3, name);
            pstmt.setInt(4, age);
            pstmt.setString(5, email);
            pstmt.setInt(6, 0);

            int rst = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("회원가입이 완료되었습니다. 환영합니다. ");
        Common.close(pstmt);
        Common.close(conn);
    }
    public void HotelAppMenu(List<UsersVO> Hlist) throws SQLException {
        if(!Hlist.isEmpty()){
            System.out.println("반갑습니다, " + Hlist.get(0).getName() + " 회원님! 원하시는 메뉴를 선택해주세요.");
            System.out.println("=".repeat(10) + "Hotels Main 화면" + "=".repeat(10));
            System.out.print("[1]호텔 검색 [2]리뷰 등록 [3]예약 확인 [4]로그 아웃 ");
            HotelListDAO dao = new HotelListDAO();
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    List<HotelVO> hotelList = selectRegion(); // 지역 선택 및 호텔 정보 조회
                    if (!hotelList.isEmpty()) { // 리스트가 비어있지 않은 경우에만 출력
                        hotelSelectResult(hotelList); // 호텔 정보 출력
                        ReserveOrDetail();
                    } else {
                        System.out.println("선택한 지역에 호텔 정보가 없습니다.");
                    }
                    break;
                case 2:

                case 3:

                case 4:
                    System.out.println("로그아웃!");
                    list=null;
                    LoginMenu(); // 로그인화면
                    break;
            }
        }else {
            System.out.println("일치하는 정보가 없습니다.");
        }

    }
    public static List<HotelVO> selectRegion() { // 반환 타입을 List<HotelVO>로 변경
        Scanner sc = new Scanner(System.in);
        HotelListDAO dao = new HotelListDAO();

        int choice = 0; // 사용자의 선택을 저장할 변수
        while (true) {
            System.out.println("=".repeat(10) + " 지역 선택 " + "=".repeat(10));
            System.out.println("지역을 선택하세요 : ");
            System.out.println("[1] 서울\n" + "[2] 부산\n" +
                    "[3] 제주\n" +
                    "[4] 인천\n" +
                    "[5] 여수\n" +
                    "[6] 수원\n" +
                    "[7] 대전\n" +
                    "[8] 광주\n" +
                    "[9] 대구\n" +
                    "[10] 속초");
            choice = sc.nextInt();

            if (choice >= 1 && choice <= 10) {
                break; // 유효한 입력일 경우 루프 종료
            } else {
                System.out.println("유효한 선택이 아닙니다. 다시 시도하세요.");
            }
        }

        List<HotelVO> list = new ArrayList<>(); // 호텔 정보를 저장할 리스트
        String[] regions = {"서울", "부산", "제주", "인천", "여수", "수원", "대전", "광주", "대구", "속초"};
        String selectedRegion = regions[choice - 1]; // 선택한 지역의 이름

        // DB 연결 및 호텔 정보 조회
        try (Connection conn = Common.getConnection(); // DB 연결
             PreparedStatement psmt = conn.prepareStatement("SELECT * FROM hotel WHERE region = ?")) {

            psmt.setString(1, selectedRegion); // 지역 매개변수 설정
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                int hotelID = rs.getInt("hotelID");
                String hotelName = rs.getString("hotelName");
                String region = rs.getString("region");
                String phone = rs.getString("phone");
                String hotelExpl = rs.getString("hotelExpl");
                HotelVO vo = new HotelVO(hotelID, hotelName, region, phone, hotelExpl);
                list.add(vo); // 리스트에 호텔 정보 추가
            }
        } catch (Exception e) {
            System.out.println("호텔 조회 실패 !!!");
            e.printStackTrace();
        }
        return list; // 호텔 리스트 반환
    }

    public void hotelSelectResult(List<HotelVO> list) { // 호텔 정보를 출력하는 메소드
        System.out.println("---------------------------------------");
        System.out.println("             호텔 정보");
        System.out.println("---------------------------------------");

        // 리스트의 모든 호텔 정보를 출력
        for (HotelVO e : list) {
            System.out.print(e.getHotelID() + " "); // 호텔 ID 출력
            System.out.print(e.getHotelName() + " "); // 호텔 이름 출력
            System.out.print(e.getRegion() + " "); // 지역 출력
            System.out.print(e.getPhone() + " "); // 전화번호 출력
            System.out.print(e.getHotelExpl() + " "); // 호텔 설명 출력
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }
    public void chooseHotel(){

    }

    public void ReserveOrDetail(){
        ReserveHotelDAO reserveHotel = new ReserveHotelDAO();
        DetailHotelDAO detailHotel = new DetailHotelDAO();
        Scanner sc = new Scanner(System.in);
        System.out.println("메뉴를 선택하세요(숫자)");
        System.out.println("[1] 예약하기 [2] 상세보기 [3]돌아가기");
        int rod = sc.nextInt();
        if(rod ==1){
            reserveHotel.reservation();
        } else if (rod ==2) {
            detailHotel.detail();
        }else if(rod ==3) {
            selectRegion();
        }



    }



    private String checkPassword(String pwd, String id){
        // 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상)
        Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        Matcher passMatcher1 = passPattern1.matcher(pwd);

        if(!passMatcher1.find()){
            return "비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다.";
        }

        // 반복된 문자 확인
        Pattern passPattern2 = Pattern.compile("(\\w)\\1\\1\\1");
        Matcher passMatcher2 = passPattern2.matcher(pwd);

        if(passMatcher2.find()){
            return "비밀번호에 동일한 문자를 과도하게 연속해서 사용할 수 없습니다.";
        }

        // 아이디 포함 확인
        if(pwd.contains(id)){
            System.out.println("비밀번호에 ID를 포함할 수 없습니다.");
        }

        // 특수문자 확인
        Pattern passPattern3 = Pattern.compile("\\W");
        Pattern passPattern4 = Pattern.compile("[!@#$%^*+=-]");

        for(int i = 0; i < pwd.length(); i++){
            String s = String.valueOf(pwd.charAt(i));
            Matcher passMatcher3 = passPattern3.matcher(s);

            if(passMatcher3.find()){
                Matcher passMatcher4 = passPattern4.matcher(s);
                if(!passMatcher4.find()){
                    return "비밀번호에 특수문자는 !@#$^*+=-만 사용 가능합니다.";
                }
            }
        }

        //연속된 문자 확인
        int ascSeqCharCnt = 0; // 오름차순 연속 문자 카운트
        int descSeqCharCnt = 0; // 내림차순 연속 문자 카운트

        char char_0;
        char char_1;
        char char_2;
        int diff_0_1;
        int diff_1_2;

        for(int i = 0; i < pwd.length()-2; i++){
            char_0 = pwd.charAt(i);
            char_1 = pwd.charAt(i+1);
            char_2 = pwd.charAt(i+2);

            diff_0_1 = char_0 - char_1;
            diff_1_2 = char_1 - char_2;

            if(diff_0_1 == 1 && diff_1_2 == 1){
                ascSeqCharCnt += 1;
            }

            if(diff_0_1 == -1 && diff_1_2 == -1){
                descSeqCharCnt -= 1;
            }
        }

        if(ascSeqCharCnt > 1 || descSeqCharCnt > 1){
            return "비밀번호에 연속된 문자열을 사용할 수 없습니다.";
        }

        return "";
    }
}