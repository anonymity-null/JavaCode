package dao.daoImpl;

import bean.User;
import dao.UserDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.JDBCUtil;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public User findUser(User user) throws SQLException {
        String sql="select * from user where username=? and password=?";
        return runner.query(sql,
                new BeanHandler<>(User.class),
                user.getUsername(),
                user.getPassword());
    }

    @Override
    public User insertUser(User user) throws SQLException {
        String sql="insert into user values(?,?,?)";

        return runner.insert(sql,
                new BeanHandler<>(User.class),
                user.getUsername(),
                user.getPassword(),
                user.getIdentity());
    }
}
