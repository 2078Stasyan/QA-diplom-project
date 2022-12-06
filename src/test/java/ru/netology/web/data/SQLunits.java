package ru.netology.web.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import java.sql.*;

public class SQLunits {

    @SneakyThrows
    private static Connection getConnection() {

        String url = System.getProperty("db.url");
        String user = System.getProperty("db.user");
        String password = System.getProperty("db.password");

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    @SneakyThrows
    public static void cleanDb() {
        var runner = new QueryRunner();
        var creditRequest = "DELETE FROM credit_request_entity";
        var order = "DELETE FROM order_entity";
        var payment = "DELETE FROM payment_entity";

        try (var conn = getConnection()) {
            runner.update(conn, creditRequest);
            runner.update(conn, order);
            runner.update(conn, payment);

        }
    }

    @SneakyThrows
    public static String getPaymentId() {
        String paymentId = "";
        var idSQL = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1;";
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(idSQL)) {
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    paymentId = rs.getString("payment_id");
                }
            }
        }
        return paymentId;
    }

    @SneakyThrows
    public static String getStatusForPaymentWithDebitCard(String paymentId) {
        String statusSQL = "SELECT status FROM payment_entity WHERE transaction_id =?; ";
        String status = "";
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(statusSQL)) {
            statusStmt.setString(1, paymentId);
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }

    @SneakyThrows
    public static String getStatusForPaymentWithCreditCard(String paymentId) {
        String statusSQL = "SELECT status FROM credit_request_entity WHERE bank_id =?; ";
        String status = "";
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(statusSQL)) {
            statusStmt.setString(1, paymentId);
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }
}

