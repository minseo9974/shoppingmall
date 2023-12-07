package com.nhnacademy.shoppingmall.shoppingCart.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.join.domain.CartProduct;
import com.nhnacademy.shoppingmall.shoppingCart.domain.ShoppingCart;
import com.nhnacademy.shoppingmall.shoppingCart.repository.ShoppingCartRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {
    @Override
    public List<ShoppingCart> getAllProductByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from ShoppingCart where UserID = ? order by ProductID asc";

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            rs = psmt.executeQuery();
            List<ShoppingCart> cartList = new ArrayList<>();

            while (rs.next()) {
                cartList.add(
                        new ShoppingCart(
                                rs.getInt("RecordID"),
                                rs.getString("UserID"),
                                rs.getInt("Quantity"),
                                rs.getInt("ProductID"),
                                Objects.nonNull(rs.getTimestamp("DateCreated")) ?
                                        rs.getTimestamp("DateCreated").toLocalDateTime() : null
                        )
                );
            }

            return cartList;
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
    public int countById(int productId, String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from ShoppingCart where ProductID = ? and UserID=?";
        log.debug("countByProductID:{}", sql);

        int result = 0;
        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, productId);
            psmt.setString(2, userId);
            rs = psmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
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

        return result;
    }

    @Override
    public ShoppingCart getCartById(int productId, String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from ShoppingCart where ProductID = ? and UserID = ?";

        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, productId);
            psmt.setString(2, userId);
            rs = psmt.executeQuery();
            rs.next();
            return new ShoppingCart(
                    rs.getInt("RecordID"),
                    rs.getString("UserID"),
                    rs.getInt("Quantity"),
                    rs.getInt("ProductID"),
                    Objects.nonNull(rs.getTimestamp("DateCreated")) ? rs.getTimestamp("DateCreated").toLocalDateTime() :
                            null
            );


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
    public int save(ShoppingCart shoppingCart) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into ShoppingCart(UserID,Quantity,ProductID) values(?,?,?)";

        log.debug("save:{}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, shoppingCart.getUserId());
            psmt.setInt(2, shoppingCart.getQuantity());
            psmt.setInt(3, shoppingCart.getProductId());

            int result = psmt.executeUpdate();
            log.debug("save result :{}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateQuantity(ShoppingCart shoppingCart) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "update ShoppingCart set Quantity = ? " +
                        "where UserID = ?and  ProductID = ? ";
        log.debug("update:{}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            int i = 0;
            psmt.setInt(++i, shoppingCart.getQuantity());
            psmt.setString(++i, shoppingCart.getUserId());
            psmt.setInt(++i, shoppingCart.getProductId());

            int result = psmt.executeUpdate();
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(String userId, int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from ShoppingCart where UserID = ? and ProductID = ?";
        log.debug("deleteByUserIDAndProductID:{}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            psmt.setInt(2, productId);

            int result = psmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteAll(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from ShoppingCart where UserID = ?";
        log.debug("deleteByUserID:{}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);

            int result = psmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CartProduct> getCPList(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select u.user_id, p.ProductID, p.ModelName,c.Quantity, p.UnitCost " +
                "from ShoppingCart c " +
                "JOIN users u ON u.user_id = c.UserID " +
                "JOIN Products p ON p.ProductID = c.ProductID " +
                "WHERE u.user_id = ?";

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            rs = psmt.executeQuery();
            List<CartProduct> cartList = new ArrayList<>();

            while (rs.next()) {
                cartList.add(
                        new CartProduct(
                                rs.getString("u.user_id"),
                                rs.getInt("p.ProductID"),
                                rs.getString("p.ModelName"),
                                rs.getInt("c.Quantity"),
                                rs.getInt("p.UnitCost")

                        )
                );
            }

            return cartList;
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
