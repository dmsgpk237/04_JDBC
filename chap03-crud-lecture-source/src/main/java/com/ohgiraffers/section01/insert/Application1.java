package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    // C : insert
    public static void main(String[] args) {

        Connection con = getConnection();



        PreparedStatement pstmt = null;

        /*
        * insert,update,delete는 반환받기 성공한 행의 개수를 반환해준다. => 정수형태
        * 0은 아무런 변화가 없을 때 반환해준다.
        *
        * */
        int result = 0;

        // 쿼리문이 저장된 xml 파일을 읽어올 Properties 객체
        Properties prop = new Properties();


        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("insertMenu");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "새우가리비봉골레");
            pstmt.setInt(2, 50000);
            pstmt.setInt(3, 1);
            pstmt.setString(4, "Y");

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("메뉴 저장 성공!!");
        } else {
            System.out.println("메뉴 저장 실패!!");
        }

        System.out.println(result);

    }

}
