package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.service.UserService;
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
