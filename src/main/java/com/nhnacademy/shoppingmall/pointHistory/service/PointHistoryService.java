package com.nhnacademy.shoppingmall.pointHistory.service;

import com.nhnacademy.shoppingmall.pointHistory.domain.PointHistory;
import java.util.List;

public interface PointHistoryService {
    List<PointHistory> getList(String userId);

    void save(PointHistory pointHistory);

    void delete(String userId);
}
