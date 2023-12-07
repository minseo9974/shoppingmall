package com.nhnacademy.shoppingmall.pointHistory.service.impl;

import com.nhnacademy.shoppingmall.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.pointHistory.repository.PointHistoryRepository;
import com.nhnacademy.shoppingmall.pointHistory.service.PointHistoryService;
import java.util.List;

public class PointHistoryServiceImpl implements PointHistoryService {
    private final PointHistoryRepository pointHistoryRepository;

    public PointHistoryServiceImpl(PointHistoryRepository pointHistoryRepository) {
        this.pointHistoryRepository = pointHistoryRepository;
    }
    @Override
    public List<PointHistory> getList(String userId) {
        return pointHistoryRepository.getList(userId);
    }

    @Override
    public void save(PointHistory pointHistory) {
        pointHistoryRepository.save(pointHistory);
    }

    @Override
    public void delete(String userId) {
        pointHistoryRepository.delete(userId);
    }
}
