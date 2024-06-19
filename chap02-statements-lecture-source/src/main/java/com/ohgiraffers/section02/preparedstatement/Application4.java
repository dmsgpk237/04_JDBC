package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.DepartmentDTO;
import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application4 {
    public static void main(String[] args) {

        /*
        * Department 테이블의 부서코드(아이디)를 입력받아서
        * 부서코드(아이디), 부서명, 지역코드를 출력하시오
        * */

        // Scanner를 사용한 PreparedStatement

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        // Scanner 부서코드 입력받기

        Scanner sc = new Scanner(System.in);
        System.out.println("부서코드(아이디)를 입력하세요 : ");
        String deptId = sc.nextLine();

        String query = "select * from department where dept_id = ?";

        DepartmentDTO selectedDept = null;

        try {

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, deptId);

            rset = pstmt.executeQuery();

            if (rset.next()) {

                selectedDept = new DepartmentDTO();

                selectedDept.setDept_id(rset.getString("DEPT_ID"));
                selectedDept.setDept_title(rset.getString("DEPT_TITLE"));
                selectedDept.setLocation_id(rset.getString("LOCATION_ID"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        System.out.println("selectedDept = " + selectedDept);


    }
}
