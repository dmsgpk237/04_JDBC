/* Employee 목록 추가 */

package com.ohgiraffers.section;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.EmployeeTemplate.close;
import static com.ohgiraffers.common.EmployeeTemplate.getConnection;

public class Application2 {

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
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));

            String query = prop.getProperty("insertEmp");

            pstmt = con.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.println("사원의 번호를 입력하세요 : ");
            String empID = sc.next();

            System.out.println("사원의 이름을 입력하세요 : ");
            String empName = sc.next();

            System.out.println("사원의 주민번호를 입력하세요 : ");
            String empNo = sc.next();

            System.out.println("사원의 'job_code'를 입력하세요 : ");
            String jobCode = sc.next();

            System.out.println("사원의 급여(S) 레벨을 입력하세요 : ");
            String salLevel = sc.next();

            System.out.println("사원의 월급을 입력하세요 : ");
            double salary = sc.nextDouble();


            pstmt.setString(1, empID);
            pstmt.setString(2, empName);
            pstmt.setString(3, empNo);
            pstmt.setString(4, jobCode);
            pstmt.setString(5, salLevel);
            pstmt.setDouble(6, salary);

            result = pstmt.executeUpdate();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        if(result > 0) {
            System.out.println("사원 추가 성공!!!");
        } else {
            System.out.println("사원 추가 실패!!!");
        }
    }
}
