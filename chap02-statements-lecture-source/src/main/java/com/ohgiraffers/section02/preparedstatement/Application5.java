package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application5 {
    public static void main(String[] args) {

        // 연결 객체 만들기
        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        // 조회할 employee 의 이름의 성을 받아서 찾기

        Scanner sc = new Scanner(System.in);

        System.out.println("조회할 이름의 성을 입력하세요 : ");

        String empName = sc.nextLine();

        // concat(?, '%') => ? 로 시작하는 것
        // 위치홀더로 받을 어떤 것으로 시작하는 것이라는 조건을 줌
//        String query = "select * from employee where emp_name like concat(?, '%')";

        EmployeeDTO row = null;
        List<EmployeeDTO> empList = null;

        Properties prop = new Properties();

        try {

            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/section02/preparedstatement/employee-query.xml"));

            String query = prop.getProperty("selectEmpByFamilyName");
            // 내가 만들어놓은 xml 파일을 키를 통해 찾아가 사용하는 것.

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, empName);

            rset = pstmt.executeQuery();

            empList = new ArrayList<>();

            while (rset.next()) {

                row = new EmployeeDTO();

                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalary(rset.getDouble("SALARY"));


                empList.add(row);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
            // 열었던 순서대로 닫아주기
        }

        for (EmployeeDTO emp : empList) {
            System.out.println(emp);
        }
    }
}
