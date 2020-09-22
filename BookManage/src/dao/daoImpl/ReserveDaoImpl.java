package dao.daoImpl;

import bean.Reserve;
import dao.ReserveDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.JDBCUtil;

import java.sql.SQLException;

public class ReserveDaoImpl implements ReserveDao {
    QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public Reserve insertReserve(Reserve reserve) throws SQLException {
        String sql="insert into reserve values(?,?)";
        if (findReserve(reserve)==null){
            return runner.insert(sql,
                    new BeanHandler<>(Reserve.class),
                    reserve.getUsername(),
                    reserve.getReservenumber());
        }else return null;


    }

    @Override
    public Reserve findReserve(Reserve reserve) throws SQLException {
        String sql="select * from reserve where username=? and reservenumber=?";
        Reserve query = runner.query(sql,
                new BeanHandler<>(Reserve.class),
                reserve.getUsername(),
                reserve.getReservenumber());
        return query;
    }
}
