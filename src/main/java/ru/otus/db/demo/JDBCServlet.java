package ru.otus.db.demo;

import ru.otus.db.demo.util.Generator;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/jdbc")
public class JDBCServlet extends HttpServlet {

    @Resource(name = "jdbc/OracleDS") // for Tomcat
//    @Resource(lookup = "jdbc/OracleDS") // for Glassfish
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from emp");
             ResultSet resultSet = ps.executeQuery()){
            StringBuilder sb = new StringBuilder();
            while(resultSet.next()){
                sb.append(
                        Stream.of(
                                resultSet.getString("ename") + " " + resultSet.getString("job")
                        ).collect(Collectors.joining("|"))
                );
            }
            response.getWriter().println(sb.toString());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private String lastName;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "insert into emp(empno, ename, job, deptno) " +
                             "values(?, ?, ?, ?)")){
            ps.setLong(1, Generator.generateDigits(4));
            ps.setString(2, lastName = Generator.generateName());
            ps.setString(3, "DEVELOPER");
            ps.setLong(4, 10L);
            ps.executeUpdate();
            response.getWriter().println("Employee '" + lastName + "' has been successfully created");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (lastName == null) return;

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "update emp " +
                             "set job = ?, deptno = ? " +
                             "where ename = ?")){
            ps.setString(1, "BOSS");
            ps.setLong(2, 20L);
            ps.setString(3, lastName);
            ps.executeUpdate();
            response.getWriter().println("Employee '" + lastName + "' has been successfully updated");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (lastName == null) return;
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "delete from emp " +
                             "where ename = ?")){
            ps.setString(1, lastName);
            ps.executeUpdate();
            response.getWriter().println("Employee '" + lastName + "' has been successfully deleted");
            lastName = null;
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

}