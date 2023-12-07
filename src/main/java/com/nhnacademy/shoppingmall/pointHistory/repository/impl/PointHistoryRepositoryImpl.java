package com.nhnacademy.shoppingmall.pointHistory.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.orderDetail.domain.OrderDetail;
import com.nhnacademy.shoppingmall.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.pointHistory.repository.PointHistoryRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PointHistoryRepositoryImpl implements PointHistoryRepository {
    @Override
    public List<PointHistory> getList(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from PointHistory where historyId = ?";
        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            rs = psmt.executeQuery();
            List<PointHistory> list = new ArrayList<>();
            while (rs.next()) {
                list.add(
                        new PointHistory(
                                rs.getInt("historyId"),
                                rs.getString("userId"),
                                rs.getString("transactionType"),
                                rs.getBigDecimal("transactionAmount"),
                                Objects.nonNull(rs.getTimestamp("transactionDate")) ?
                                        rs.getTimestamp("transactionDate").toLocalDateTime() : null
                        )
                );
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (Objects.nonNull(rs)) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int save(PointHistory pointHistory) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into PointHistory(userId,transactionType,transactionAmount,transactionDate) values(?,?,?,?)";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, pointHistory.getUserId());
            psmt.setString(2, pointHistory.getType());
            psmt.setBigDecimal(3, pointHistory.getAmount());
            psmt.setTimestamp(4, Timestamp.valueOf(pointHistory.getDate()));
            int result = psmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from PointHistory where userId = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);

            int result = psmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int userCount(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from PointHistory where userId = ?";

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1,userId);
            rs = psmt.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PointHistory> getCurrentPageList(int offset, int pageSize,String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from PointHistory where userId = ? order by historyId asc limit ?,?";

        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1,userId);
            psmt.setInt(2, offset);
            psmt.setInt(3, pageSize);
            rs = psmt.executeQuery();
            List<PointHistory> list = new ArrayList<>();

            while (rs.next()) {
                list.add(
                        new PointHistory(
                                rs.getInt("historyId"),
                                rs.getString("userId"),
                                rs.getString("transactionType"),
                                rs.getBigDecimal("transactionAmount"),
                                rs.getTimestamp("transactionDate").toLocalDateTime()
                        )
                );

            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (Objects.nonNull(rs)) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
