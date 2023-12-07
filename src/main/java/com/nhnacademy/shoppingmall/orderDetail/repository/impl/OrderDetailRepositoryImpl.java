package com.nhnacademy.shoppingmall.orderDetail.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.orderDetail.domain.OrderDetail;
import com.nhnacademy.shoppingmall.orderDetail.repository.OrderDetailRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    @Override
    public List<OrderDetail> getList(int orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from OrderDetails where OrderID = ?";
        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, orderId);
            rs = psmt.executeQuery();
            List<OrderDetail> odList = new ArrayList<>();
            while (rs.next()) {
                odList.add(
                        new OrderDetail(
                                rs.getInt("OrderID"),
                                rs.getInt("ProductID"),
                                rs.getInt("Quantity"),
                                rs.getBigDecimal("UnitCost")
                        )
                );
            }
            return odList;
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
    public int save(OrderDetail orderDetail) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into OrderDetails values(?,?,?,?)";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, orderDetail.getOrderId());
            psmt.setInt(2, orderDetail.getProductId());
            psmt.setInt(3, orderDetail.getQuantity());
            psmt.setBigDecimal(4, orderDetail.getUnitCost());
            int result = psmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(int orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from OrderDetails where OrderID = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, orderId);
            int result = psmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countById(int orderId, int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from OrderDetails where OrderID = ? and ProductID = ?";

        int result = 0;
        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, orderId);
            psmt.setInt(2, productId);
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
}
