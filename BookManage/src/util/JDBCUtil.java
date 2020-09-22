package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtil {

    private static final ComboPooledDataSource dataSource=new ComboPooledDataSource();
    /**
     * 获得连接的方法
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 获得连接池
     * @return
     */
    public static ComboPooledDataSource getDataSource(){
        return dataSource;
    }
}
