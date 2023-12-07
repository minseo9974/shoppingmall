package com.nhnacademy.shoppingmall.pointHistory.service;

import com.nhnacademy.shoppingmall.pointHistory.domain.PointHistory;
import java.util.List;

public interface PointHistoryService {
    List<PointHistory> getList(String userId);

    void save(PointHistory pointHistory);

    void delete(String userId);

    int getCount(String userId);

    List<PointHistory> getCurrentPageList(int offset, int pageSize,String userId);
}
