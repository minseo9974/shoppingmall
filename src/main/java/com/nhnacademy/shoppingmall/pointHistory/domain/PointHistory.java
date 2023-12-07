package com.nhnacademy.shoppingmall.pointHistory.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class PointHistory {
    private int historyId;
    private String userId;
    private String type;
    private BigDecimal amount;
    private LocalDateTime date;

    public PointHistory(int historyId, String userId, String type, BigDecimal amount, LocalDateTime date) {
        this.historyId = historyId;
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public PointHistory(String userId, String type, BigDecimal amount, LocalDateTime date) {
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PointHistory that = (PointHistory) o;
        return historyId == that.historyId && Objects.equals(userId, that.userId) &&
                Objects.equals(type, that.type) && Objects.equals(amount, that.amount) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyId, userId, type, amount, date);
    }

    @Override
    public String toString() {
        return "PointHistory{" +
                "historyId=" + historyId +
                ", userId='" + userId + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
