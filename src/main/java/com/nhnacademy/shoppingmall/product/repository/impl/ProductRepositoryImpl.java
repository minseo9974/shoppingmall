package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Optional<Product> findByProductId(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Products where ProductID = ?";
        log.debug("findByProductId:{}", sql);

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, productId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        new Product(
                                rs.getInt("ProductID"),
                                rs.getInt("CategoryID"),
                                rs.getString("ModelNumber"),
                                rs.getString("ModelName"),
                                rs.getString("ProductImage"),
                                rs.getBigDecimal("UnitCost"),
                                rs.getString("Description")
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
    public int save(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into Products values(?,?,?,?,?,?,?)";
        log.debug("Products save :{}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, product.getProductId());
            psmt.setInt(2, product.getCategoryId());
            psmt.setString(3, product.getModelNumber());
            psmt.setString(4, product.getModelName());
            psmt.setString(5, product.getProductImage());
            psmt.setBigDecimal(6, product.getUnitCost());
            psmt.setString(7, product.getDescription());
            int result = psmt.executeUpdate();
            log.debug("save result :{}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "update Products set CategoryID = ?, ModelNumber = ?, ModelName=?,ProductImage=?,UnitCost=?,Description=? where ProductID = ?";
        log.debug("Products update:{}", sql);
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, product.getCategoryId());
            psmt.setString(2, product.getModelNumber());
            psmt.setString(3, product.getModelName());
            psmt.setString(4, product.getProductImage());
            psmt.setBigDecimal(5, product.getUnitCost());
            psmt.setString(6, product.getDescription());
            psmt.setInt(7, product.getProductId());
            int result = psmt.executeUpdate();
            log.debug("update result:{}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int deleteByProductId(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Products where ProductID = ?";
        log.debug("products delete : {}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, productId);
            int result = psmt.executeUpdate();
            log.debug("delete result : {}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByProductId(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Products where ProductID = ?";
        log.debug("countByProductID:{}", sql);

        int result = 0;
        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, productId);
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
    public int count() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Products";

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            rs = psmt.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int searchCount(String keyword) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Products where ModelName like CONCAT('%', ?, '%')";

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1,keyword);
            rs = psmt.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getList(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Products where ProductID = ?";
        log.debug("getList :{}", sql);

        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, productId);
            rs = psmt.executeQuery();
            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                productList.add(
                        new Product(
                                rs.getInt("ProductID"),
                                rs.getString("ModelName"),
                                rs.getBigDecimal("UnitCost")
                        )
                );
            }
            return productList;
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
    public List<Product> getCurrentpageList(int offSet, int pageSize) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Products order by ProductID asc limit ?,?";
        log.debug("page findAll :{}", sql);

        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, offSet);
            psmt.setInt(2, pageSize);
            rs = psmt.executeQuery();
            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                productList.add(
                        new Product(
                                rs.getInt("ProductID"),
                                rs.getInt("CategoryID"),
                                rs.getString("ModelNumber"),
                                rs.getString("ModelName"),
                                rs.getString("ProductImage"),
                                rs.getBigDecimal("UnitCost"),
                                rs.getString("Description")
                        )
                );
            }
            return productList;
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
    public List<Product> getSearchpageList(int offSet, int pageSize, String keyword) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Products where ModelName like CONCAT('%', ?, '%')order by ProductID asc limit ?,?";
        log.debug("page findAll :{}", sql);

        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, keyword);
            psmt.setInt(2, offSet);
            psmt.setInt(3, pageSize);
            rs = psmt.executeQuery();
            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                productList.add(
                        new Product(
                                rs.getInt("ProductID"),
                                rs.getInt("CategoryID"),
                                rs.getString("ModelNumber"),
                                rs.getString("ModelName"),
                                rs.getString("ProductImage"),
                                rs.getBigDecimal("UnitCost"),
                                rs.getString("Description")
                        )
                );
            }
            long total = 0;
            if (!productList.isEmpty()) {
                total = count();
            }
            return productList;
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
