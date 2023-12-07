package com.nhnacademy.shoppingmall.order.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public Optional<Order> findById(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select OrderID,UserID,OrderDate,ShipDate,Address from Orders where UserID = ? order by OrderDate DESC LIMIT 1";

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        new Order(
                                rs.getInt("OrderID"),
                                rs.getString("UserID"),
                                Objects.nonNull(rs.getTimestamp("OrderDate")) ?
                                        rs.getTimestamp("OrderDate").toLocalDateTime() : null,
                                Objects.nonNull(rs.getTimestamp("ShipDate")) ?
                                        rs.getTimestamp("ShipDate").toLocalDateTime() : null,
                                rs.getString("Address")
                        )
                );
            }

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

        return Optional.empty();
    }

    @Override
    public int save(Order order) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into Orders(UserID,OrderDate,ShipDate,Address) values(?,?,?,?)";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, order.getUserId());
            psmt.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            psmt.setTimestamp(3, Timestamp.valueOf(order.getShipDate()));
            psmt.setString(4, order.getAddress());
            int result = psmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(int orderId, String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Orders where OrderID = ? and UserID = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, orderId);
            psmt.setString(2, userId);

            int result = psmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countById(int orderId, String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Orders where OrderID = ? and UserID = ?";

        int result = 0;
        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, orderId);
            psmt.setString(2, userId);
            rs = psmt.executeQuery();
            rs.next();
            return rs.getInt(1);
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

    @Override
    public int userCount(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Orders where UserID = ?";

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
    public List<Order> getCurrentPageList(int offset, int pageSize,String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Orders where UserID = ? order by OrderID asc limit ?,?";

        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1,userId);
            psmt.setInt(2, offset);
            psmt.setInt(3, pageSize);
            rs = psmt.executeQuery();
            List<Order> orderList = new ArrayList<>();

            while (rs.next()) {
                orderList.add(
                        new Order(
                                rs.getInt("OrderID"),
                                rs.getString("UserID"),
                                rs.getTimestamp("OrderDate").toLocalDateTime(),
                                rs.getTimestamp("ShipDate").toLocalDateTime(),
                                rs.getString("Address")
                        )
                );

            }

            return orderList;
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
