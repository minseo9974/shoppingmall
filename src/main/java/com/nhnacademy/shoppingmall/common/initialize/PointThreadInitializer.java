package com.nhnacademy.shoppingmall.common.initialize;

import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.thread.worker.WorkerThread;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;
import javax.servlet.annotation.HandlesTypes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PointThreadInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        RequestChannel requestChannel = new RequestChannel(10);
        //todo#14-1 servletContext에 requestChannel을 등록합니다.
        ctx.setAttribute("requestChannel", requestChannel);
        //todo#14-2 WorkerThread 사작합니다.
        try {
            WorkerThread workerThread = new WorkerThread(requestChannel);
            workerThread.start();
        } catch (Exception e) {
            throw new ServletException("Fail WorkerThread");
        }
    }
}
