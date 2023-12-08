package com.nhnacademy.shoppingmall.thread.request;

import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ChannelRequest {
    // 멀티쓰레드 환경에서 안전하게 고유 ID를 생성하기 위함
    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    private final long requestId;

    protected ChannelRequest(){
        requestId = ID_GENERATOR.incrementAndGet();
        log.debug("ChannelRequest ID :{}", requestId);
    }
    public abstract void execute();

    @Override
    public String toString() {
        return "ChannelRequest{" +
                "requestId=" + requestId +
                '}';
    }
}