package dao;

import bean.Reserve;

import java.sql.SQLException;

public interface ReserveDao {
    Reserve insertReserve(Reserve reserve) throws SQLException;
    Reserve findReserve(Reserve reserve) throws SQLException;
}