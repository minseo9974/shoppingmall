package com.nhnacademy.shoppingmall.pointHistory.repository;

import com.nhnacademy.shoppingmall.pointHistory.domain.PointHistory;
import java.util.List;

public interface PointHistoryRepository {
    List<PointHistory> getList(String userId);

    int save(PointHistory pointHistory);

    int delete(String userId);
    int userCount(String userId);

    List<PointHistory> getCurrentPageList(int offset, int pageSize,String userId);
}
