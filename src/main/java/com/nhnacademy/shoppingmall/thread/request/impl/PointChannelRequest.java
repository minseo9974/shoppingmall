package com.nhnacademy.shoppingmall.thread.request.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.pointHistory.repository.impl.PointHistoryRepositoryImpl;
import com.nhnacademy.shoppingmall.pointHistory.service.PointHistoryService;
import com.nhnacademy.shoppingmall.pointHistory.service.impl.PointHistoryServiceImpl;
import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PointChannelRequest extends ChannelRequest {
    private int payCost;
    private int userPoint;
    private String userId;
    private final double POINT_PERCENTAGE = 0.10; // 10% 적립

    private final PointHistoryService pointHistoryService =
            new PointHistoryServiceImpl(new PointHistoryRepositoryImpl());
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    public PointChannelRequest(int payCost,int userPoint, String userId) {
        this.payCost = payCost;
        this.userPoint = userPoint;
        this.userId = userId;
    }

    @Override
    public void execute() {
        DbConnectionThreadLocal.initialize();
        //todo#14-5 포인트 적립구현, connection은 point적립이 완료되면 반납합니다.
        int earnPoints = (int) (payCost * POINT_PERCENTAGE);
        int userUpdatedPoint = Math.addExact(userPoint, earnPoints);

        // 유저 포인트 업데이트
        userService.updateUserPoint(userId, userUpdatedPoint);

        // 적립내역 추가
        PointHistory pointHistory = new PointHistory(userId, "적립", new BigDecimal(earnPoints), LocalDateTime.now());
        pointHistoryService.save(pointHistory);

        log.debug("pointChannel execute");
        DbConnectionThreadLocal.reset();
    }
}
