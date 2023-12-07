package com.nhnacademy.shoppingmall.categories.repository.impl;

import com.nhnacademy.shoppingmall.categories.domain.Category;
import com.nhnacademy.shoppingmall.categories.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
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
public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public Optional<Category> findByCategoryId(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Categories where CategoryID = ?";
        log.debug("findByCategoryID:{}", sql);

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, categoryId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        new Category(
                                rs.getInt("CategoryID"),
                                rs.getString("CategoryName")
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
    public int save(Category category) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into Categories values(?,?)";
        log.debug("category save:{}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, category.getCategoryId());
            psmt.setString(2, category.getCategoryName());
            int result = psmt.executeUpdate();
            log.debug("save result :{}", result);

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateNameByCategoryId(String categoryName, int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update Categories set CategoryName = ? where CategoryID = ?";
        log.debug("updateNameByCategoryId :{}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, categoryName);
            psmt.setInt(2, categoryId);
            int result = psmt.executeUpdate();
            log.debug("update result :{}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByCategoryId(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Categories where CategoryID = ?";
        log.debug("countByCategoryID:{}", sql);

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, categoryId);
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
    public int count() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Categories";

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
    public List<Category> getList() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Categories order by CategoryID asc";

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            rs = psmt.executeQuery();
            List<Category> categoryList = new ArrayList<>();

            while (rs.next()) {
                categoryList.add(
                        new Category(
                                rs.getInt("CategoryID"),
                                rs.getString("CategoryName")
                        )
                );
            }

            return categoryList;
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
