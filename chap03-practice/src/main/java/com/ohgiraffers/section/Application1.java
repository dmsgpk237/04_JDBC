/* Employee 목록 조희 */

package com.ohgiraffers.section;

import com.ohgiraffers.model.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import static com.ohgiraffers.common.EmployeeTemplate.close;
import static com.ohgiraffers.common.EmployeeTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        // 연결 객체 커넥션 생성
        Connection con = getConnection();

        // 쿼리문을 저장하고, 실행하는 기능을 하는 용도의 인터페이스
        // 레퍼런스 변수 생성
        PreparedStatement pstmt = null;

        // 셀렉 결과 집합을 받아올 용도의 인터페이스
        // 레퍼런스 변수 생성
        ResultSet rset = null;

        EmployeeDTO row = null;
        List<EmployeeDTO> empList = null;

        Properties prop = new Properties();

        try {

            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));

            String query = prop.getProperty("selectEmp");
            // 내가 만들어놓은 xml 파일을 키를 통해 찾아가 사용하는 것.

            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();

            empList = new ArrayList<>();

            while (rset.next()) {

                row = new EmployeeDTO();

                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("EMAIL"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getDouble("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("ENT_YN"));


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
