package com.zobus.dbmanager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private static final HikariDataSource dataSource;

    static {
    	
        HikariConfig config = new HikariConfig();
        
        config.setJdbcUrl("jdbc:mysql://localhost:3306/zobus");
        config.setUsername("root");
        config.setPassword("iamroot");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(20);
        
        dataSource = new HikariDataSource(config);
        
    } 

    public static Connection getConnection() throws SQLException { 
        return dataSource.getConnection();
    }
    
}
