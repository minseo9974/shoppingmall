package com.nhnacademy.shoppingmall.address.repository.impl;

import com.mysql.cj.exceptions.ConnectionIsClosedException;
import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AddressRepositoryImpl implements AddressRepository {
    @Override
    public Optional<Address> findById(int addressId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Address where Address_ID = ?";

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, addressId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        new Address(
                                rs.getInt("Address_ID"),
                                rs.getString("User_ID"),
                                rs.getString("State"),
                                rs.getString("City"),
                                rs.getString("Address_line"),
                                rs.getString("Postal_code")
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
    public int save(Address address) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into Address(User_ID,State,City,Address_line,Postal_code) values(?,?,?,?,?)";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, address.getUserId());
            psmt.setString(2, address.getState());
            psmt.setString(3, address.getCity());
            psmt.setString(4, address.getAddressLine());
            psmt.setString(5, address.getPostalCode());
            int result = psmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Address address) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update Address set State = ?,City=?,Address_line=?,Postal_code=? where Address_ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, address.getState());
            psmt.setString(2, address.getCity());
            psmt.setString(3, address.getAddressLine());
            psmt.setString(4, address.getPostalCode());
            psmt.setInt(5, address.getAddressId());
            int result = psmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int deleteByAddressId(int addressId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Address where Address_ID = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, addressId);
            int result = psmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByAddressId(int addressId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Address where Address_ID = ?";

        int result = 0;
        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, addressId);
            rs = psmt.executeQuery();
            rs.next();
            result = rs.getInt(1);
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
    public List<Address> getList(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Address where User_ID = ?";

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            rs = psmt.executeQuery();
            List<Address> list = new ArrayList<>();
            while (rs.next()) {
                list.add(
                        new Address(
                                rs.getInt("Address_ID"),
                                rs.getString("User_ID"),
                                rs.getString("State"),
                                rs.getString("City"),
                                rs.getString("Address_line"),
                                rs.getString("Postal_code")
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
