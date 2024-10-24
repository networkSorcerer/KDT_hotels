package dao;

import vo.ReviewVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MasterReviewManage {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);
    ReviewDAO reviewDao;

    public void masterReviewManage(){
        while(true){
            System.out.println("<리뷰 관리>");
            System.out.println("[1] 리뷰 목록");
            System.out.println("[2] 리뷰 삭제");
            System.out.println("[3] 뒤로가기");
            int select = sc.nextInt();

            if(select == 1){
                List<ReviewVO> list = reviewDao.reviewListAll();
                reviewDao.reviewListAllResult(list);
            }else if(select == 2){
                masterReviewDelete();
            }else if(select == 3){
                break;
            }else{
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    public void masterReviewDelete(){
        System.out.println("<리뷰 삭제>");
        System.out.print("No: ");
        int no = sc.nextInt();
        if(reviewDao.reviewDelete(no)){
            System.out.println("리뷰 삭제가 완료되었습니다.");
        }else{
            System.out.println("리뷰 삭제가 실패했습니다.");
        }
    }
}
