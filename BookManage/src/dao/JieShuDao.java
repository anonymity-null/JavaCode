package dao;

import bean.JieShu;

import java.sql.SQLException;
import java.util.List;

public interface JieShuDao {
    JieShu insertItem(JieShu jieShu) throws SQLException;
    List<JieShu> deleteItem(JieShu jieShu) throws SQLException;
    JieShu findCallnumberByJieShu(JieShu jieShu) throws SQLException;
}
