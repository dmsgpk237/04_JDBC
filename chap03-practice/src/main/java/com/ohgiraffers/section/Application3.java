/* Employee 목록 수정 */

package com.ohgiraffers.section;

import com.ohgiraffers.model.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.EmployeeTemplate.close;
import static com.ohgiraffers.common.EmployeeTemplate.getConnection;

public class Application3 {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        Scanner sc = new Scanner(System.in);

        System.out.println("변경할 사원의 번호를 입력하세요 : ");
        String empID = sc.next();

        System.out.println("사원의 변경된 이름을 입력하세요 : ");
        String empName = sc.next();

        System.out.println("사원의 변경된 월급을 입력하세요 : ");
        double salary = sc.nextDouble();

        System.out.println("사원의 변경된 직급(J) 코드를 입력하세요 : ");
        String jobCode = sc.next();

        EmployeeDTO empDTO = new EmployeeDTO();

        empDTO.setEmpId(empID);
        empDTO.setEmpName(empName);
        empDTO.setSalary(salary);
        empDTO.setJobCode(jobCode);



        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));

            String query = prop.getProperty("updateEmp");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, empDTO.getJobCode());
            pstmt.setString(2, empDTO.getEmpName());
            pstmt.setDouble(3, empDTO.getSalary());
            pstmt.setString(4, empDTO.getEmpId());

            result = pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("사원 정보 변경 성공!");
        } else {
            System.out.println("사원 정보 변경 실패!");
        }

        System.out.println(result);

    }

}
