package ru.netology.web.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLunits {

    @SneakyThrows
    private static Connection getConnection() {

        String url = System.getProperty("db.url");
        String user = System.getProperty("db.user");
        String password = System.getProperty("db.password");

        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    @SneakyThrows
    public static void cleanDb() {
        var runner = new QueryRunner();
        var creditRequest = "DELETE FROM credit_request_entity";
        var order = "DELETE FROM order_entity";
        var payment = "DELETE FROM payment_entity";

        var conn = getConnection();

        runner.update(conn, creditRequest);
        runner.update(conn, order);
        runner.update(conn, payment);
    }

    @SneakyThrows
    public static String getDebitPaymentStatus() {
        QueryRunner runner = new QueryRunner();
        String SqlStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConnection();
            return runner.query(conn, SqlStatus, new ScalarHandler<>());

    }

    @SneakyThrows
    public static String getCreditPaymentStatus() {
        QueryRunner runner = new QueryRunner();
        String SqlStatus = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var connection = getConnection();
            return  runner.query(connection, SqlStatus, new ScalarHandler<>());

    }
}

