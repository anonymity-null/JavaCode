package dao.daoImpl;

import bean.JieShu;
import dao.JieShuDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.JDBCUtil;

import java.sql.SQLException;
import java.util.List;

public class JieShuDaoImpl implements JieShuDao {
    QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public JieShu insertItem(JieShu jieShu) throws SQLException {
        String sql="insert into jieshu values(?,?,?)";
        return runner.insert(sql,
                new BeanHandler<>(JieShu.class),
                jieShu.getUsername(),
                jieShu.getCallnumber(),
                "1");

    }

    @Override
    public List<JieShu> deleteItem(JieShu jieShu) throws SQLException {
        String sql="delete from jieshu where username=? and callnumber=?";
        return  runner.execute(sql,
                new BeanHandler<>(JieShu.class),
                jieShu.getUsername(),
                jieShu.getCallnumber());

    }

    @Override
    public JieShu findCallnumberByJieShu(JieShu jieShu) throws SQLException {
        String sql="select * from jieshu where username=? and callnumber=?";
        return runner.query(sql,
                new BeanHandler<>(JieShu.class),
                jieShu.getUsername(),
                jieShu.getCallnumber());
    }
}
