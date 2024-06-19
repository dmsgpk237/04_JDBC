package com.ohgiraffers.section03.spqlinjction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        // Employee ID와 이름을 입력받고 두 개가 일치하는 Employee 가 있는지 확인하는 기능

        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);

        System.out.println("회원 ID와 이름 두 개가 일치하는 Employee가 있는지 확인하는 기능입니다.");
        System.out.println("ID를 입력하세요 : ");
        String empId = sc.nextLine();
        System.out.println("회원 이름을 입력하세요 : ");
        String empName = sc.nextLine();

        String query = "select * from employee where emp_id = '" + empId + "' and emp_name = '" + empName + "'";
        System.out.println(query);
        // select * from employee where emp_id '200'and emp_name = '선동일'

        try {
            stmt = con.createStatement();

            rset = stmt.executeQuery(query);
            // ' or 1=1 and emp_id = '205

            if (rset.next()) {
                System.out.println(rset.getString("emp_name") + "님 환영합니다.");
            } else {
                System.out.println("회원 정보가 없습니다. ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
