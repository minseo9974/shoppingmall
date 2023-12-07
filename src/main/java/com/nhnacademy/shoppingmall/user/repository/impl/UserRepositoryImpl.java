package com.nhnacademy.shoppingmall.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        /*todo#3-1 회원의 아이디와 비밀번호를 이용해서 조회하는 코드 입니다.(로그인)
          해당 코드는 SQL Injection이 발생합니다. SQL Injection이 발생하지 않도록 수정하세요.
         */
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql ="select user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at from users where user_id=? and user_password = ?";

        log.debug("sql:{}",sql);

        ResultSet rs = null;
        try( PreparedStatement psmt = connection.prepareStatement(sql);
        ) {
            psmt.setString(1, userId);
            psmt.setString(2, userPassword);
            rs = psmt.executeQuery();
            if(rs.next()){
                User user = new User(
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"),
                        rs.getString("user_birth"),
                        User.Auth.valueOf(rs.getString("user_auth")),
                        rs.getInt("user_point"),
                        Objects.nonNull(rs.getTimestamp("created_at")) ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        Objects.nonNull(rs.getTimestamp("latest_login_at")) ? rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                );
                return Optional.of(user);
            }
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

        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String userId) {
        //todo#3-2 회원조회
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from users where user_id = ?";
        log.debug("findById:{}", sql);

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        new User(
                                rs.getString("user_id"),
                                rs.getString("user_name"),
                                rs.getString("user_password"),
                                rs.getString("user_birth"),
                                User.Auth.valueOf(rs.getString("user_auth")),
                                rs.getInt("user_point"),
                                Objects.nonNull(rs.getTimestamp("created_at")) ?
                                        rs.getTimestamp("created_at").toLocalDateTime() : null,
                                Objects.nonNull(rs.getTimestamp("latest_login_at")) ?
                                        rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                        )
                );
            }
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
        return Optional.empty();
    }

    @Override
    public int save(User user) {
        //todo#3-3 회원등록, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into users values(?,?,?,?,?,?,?,?)";

        log.debug("save:{}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, user.getUserId());
            psmt.setString(2, user.getUserName());
            psmt.setString(3, user.getUserPassword());
            psmt.setString(4, user.getUserBirth());
            psmt.setString(5, user.getUserAuth().toString());
            psmt.setInt(6, user.getUserPoint());
            psmt.setTimestamp(7,Timestamp.valueOf(user.getCreatedAt()));
            if (Objects.nonNull(user.getLatestLoginAt())) {
            psmt.setTimestamp(8,Timestamp.valueOf(user.getLatestLoginAt()));
            }else{
                psmt.setNull(8, Types.TIMESTAMP);
            }

            int result = psmt.executeUpdate();
            log.debug("save result :{}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByUserId(String userId) {
        //todo#3-4 회원삭제, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from users where user_id = ?";
        log.debug("deleteByUserId:{}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);

            int result = psmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        //todo#3-5 회원수정, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "update users set user_name = ?, user_password = ? , user_birth = ? , user_auth = ? , user_point = ? " +
                        "where user_id = ?";
        log.debug("update:{}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            int i = 0;
            psmt.setString(++i, user.getUserName());
            psmt.setString(++i, user.getUserPassword());
            psmt.setString(++i, user.getUserBirth());
            psmt.setString(++i, user.getUserAuth().toString());
            psmt.setInt(++i, user.getUserPoint());
            psmt.setString(++i, user.getUserId());

            int result = psmt.executeUpdate();
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt) {
        //todo#3-6, 마지막 로그인 시간 업데이트, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update users set latest_login_at = ? where user_id = ?";
        log.debug("updateLatestLoginAtByUserId:{}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            if (Objects.nonNull(latestLoginAt)) {
                psmt.setTimestamp(1,Timestamp.valueOf(latestLoginAt));
            }else{
                psmt.setNull(1, Types.TIMESTAMP);
            }
            psmt.setString(2, userId);

            int result = psmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserId(String userId) {
        //todo#3-7 userId와 일치하는 회원의 count를 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from users where user_id = ?";
        log.debug("countByUserId:{}", sql);

        int result = 0;
        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            rs = psmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
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
    public int userCount() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from users where user_auth = ?";

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1,"ROLE_USER");
            rs = psmt.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int adminCount() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from users where user_auth = ?";

        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1,"ROLE_ADMIN");
            rs = psmt.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getUserPageList(int offset, int pageSize) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from users where user_auth = ? order by created_at asc limit ?,?";
        log.debug("page userlist :{}", sql);

        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1,"ROLE_USER");
            psmt.setInt(2, offset);
            psmt.setInt(3, pageSize);
            rs = psmt.executeQuery();
            List<User> userList = new ArrayList<>();

            while (rs.next()) {
                userList.add(
                        new User(
                                rs.getString("user_id"),
                                rs.getString("user_name"),
                                rs.getString("user_password"),
                                rs.getString("user_birth"),
                                User.Auth.valueOf(rs.getString("user_auth")),
                                rs.getInt("user_point"),
                                Objects.nonNull(rs.getTimestamp("created_at")) ?
                                        rs.getTimestamp("created_at").toLocalDateTime() : null,
                                Objects.nonNull(rs.getTimestamp("latest_login_at")) ?
                                        rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                        )
                );
            }
            long total = 0;
            if (!userList.isEmpty()) {
                total = userCount();
            }
            return userList;
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
    public List<User> getAdminPageList(int offset, int pageSize) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from users where user_auth = ? order by created_at asc limit ?,?";
        log.debug("page adminlist :{}", sql);

        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1,"ROLE_ADMIN");
            psmt.setInt(2, offset);
            psmt.setInt(3, pageSize);
            rs = psmt.executeQuery();
            List<User> userList = new ArrayList<>();

            while (rs.next()) {
                userList.add(
                        new User(
                                rs.getString("user_id"),
                                rs.getString("user_name"),
                                rs.getString("user_password"),
                                rs.getString("user_birth"),
                                User.Auth.valueOf(rs.getString("user_auth")),
                                rs.getInt("user_point"),
                                Objects.nonNull(rs.getTimestamp("created_at")) ?
                                        rs.getTimestamp("created_at").toLocalDateTime() : null,
                                Objects.nonNull(rs.getTimestamp("latest_login_at")) ?
                                        rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                        )
                );
            }
            long total = 0;
            if (!userList.isEmpty()) {
                total = adminCount();
            }
            return userList;
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
