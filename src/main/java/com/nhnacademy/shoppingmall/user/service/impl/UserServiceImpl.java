package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.pointHistory.repository.PointHistoryRepository;
import com.nhnacademy.shoppingmall.pointHistory.repository.impl.PointHistoryRepositoryImpl;
import com.nhnacademy.shoppingmall.pointHistory.service.PointHistoryService;
import com.nhnacademy.shoppingmall.pointHistory.service.impl.PointHistoryServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.service.UserService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final PointHistoryService pointHistoryService =
            new PointHistoryServiceImpl(new PointHistoryRepositoryImpl());
    @Override
    public User getUser(String userId) {
        //todo#4-1 회원조회
        Optional<User> userOptional = userRepository.findById(userId);

        return userOptional.orElse(null);
    }

    @Override
    public void saveUser(User user) {
        //todo#4-2 회원등록

        // 이미 회원이 등록되어있다면 throw exception
        int count = userRepository.countByUserId(user.getUserId());
        if (count > 0) {
            throw new UserAlreadyExistsException(user.getUserId());
        }

        int result = userRepository.save(user);
        log.debug("saveUser result :{}", result);
        if (result < 1) {
            throw new RuntimeException("can not saveUser");
        }
    }

    @Override
    public void updateUser(User user) {
        //todo#4-3 회원수정
        // 회원이 존재하지 않는다면
        int count = userRepository.countByUserId(user.getUserId());
        if (count < 1) {
            throw new UserNotFoundException(user.getUserId());
        }

        int result = userRepository.update(user);

        if (result < 1) {
            throw new RuntimeException("can not updateUser");
        }
    }

    @Override
    public void updateUserPoint(String userId, int updatedPoint) {
        int count = userRepository.countByUserId(userId);
        if (count < 1) {
            throw new UserNotFoundException(userId);
        }

        int result = userRepository.updateUserPoint(userId, updatedPoint);

        if (result < 1) {
            throw new RuntimeException("can not updateUser");
        }
    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제
        //회원이 존재하지 않는다면
        int count = userRepository.countByUserId(userId);
        if (count < 1) {
            throw new UserNotFoundException(userId);
        }

        int result = userRepository.deleteByUserId(userId);

        if (result < 1) {
            throw new RuntimeException("can not deleteUser");
        }
    }

    @Override
    public User doLogin(String userId, String userPassword) {
        //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회
        // 회원이 존재하지 않는다면
        Optional<User> existUser = userRepository.findByUserIdAndUserPassword(userId, userPassword);
        if (!existUser.isPresent()) {
            throw new UserNotFoundException(userId);
        }

        LocalDateTime nowDateTime = LocalDateTime.now();
        int result = userRepository.updateLatestLoginAtByUserId(userId,nowDateTime);
        if (result < 1) {
            throw new RuntimeException("LocalDateTime setting is Error");
        }

        return existUser.orElse(null);
    }

    @Override
    public void getDayPoint(String userId, String userPassword) {
        Optional<User> existUser = userRepository.findByUserIdAndUserPassword(userId, userPassword);
        if (!existUser.isPresent()) {
            throw new UserNotFoundException(userId);
        }
        User user = existUser.get();
        // 오늘 첫 로그인이거나 첫 가입시 데이포인트를 지급
        LocalDateTime lastLogin = user.getLatestLoginAt();
        LocalDateTime now = LocalDateTime.now();
        // 오늘 날짜
        LocalDate today = now.toLocalDate();

        final int POINT = 10_000;

        // 마지막 로그인 날짜가 오늘이 아니라면
        if (lastLogin == null || lastLogin.toLocalDate().isBefore(today)) {
            // 유저에게 저장할 포인트
            int beforePoint = user.getUserPoint();
            int afterPoint = Math.addExact(beforePoint, POINT);
            // 유저 포인트 업데이트
            userRepository.updateUserPoint(userId, afterPoint);
            // 포인트 내역 업데이트
            PointHistory pointHistory = new PointHistory(userId, "적립", new BigDecimal(POINT), now);
            pointHistoryService.save(pointHistory);
        }


    }

    @Override
    public int getUserCount() {
        return userRepository.userCount();
    }

    @Override
    public int getAdminCount() {
        return userRepository.adminCount();
    }

    @Override
    public List<User> getAdminPageList(int offset, int pageSize) {
        return userRepository.getAdminPageList(offset, pageSize);
    }

    @Override
    public List<User> getUserPageList(int offset, int pageSize) {
        return userRepository.getUserPageList(offset, pageSize);
    }

}
