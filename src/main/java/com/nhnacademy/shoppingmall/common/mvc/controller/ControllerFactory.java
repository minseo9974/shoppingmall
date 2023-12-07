package com.nhnacademy.shoppingmall.common.mvc.controller;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.exception.ControllerNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ControllerFactory {
    public static final String CONTEXT_CONTROLLER_FACTORY_NAME = "CONTEXT_CONTROLLER_FACTORY";
    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void initialize(Set<Class<?>> c, ServletContext ctx) {

        if (Objects.isNull(c)) {
            log.info("Controller not found");
            return;
        }

        /*todo#5-1 ControllerFactory 초기화, 아래 설명을 참고하여 구현합니다.
         * 1. Set<Class<?>> c 에는 com.nhnacademy.shoppingmall.common.initialize.WebAppInitializer 에서  HandlesTypes에
         * com.nhnacademy.shoppingmall.common.mvc.controller.BaseController.class인 class를 set에 담겨서 parameter로 전달 됩니다.
         * BaseController를 구현한 Controller class가 전달됩니다.
         *
         * 2.Java Reflection API를 사용하여 Controller class의 instance를 생성하고 beanMap에 등록합니다. key/value는 다음과 같습니다.
         *  ex) key= GET-/index.do , value = IndexController's instance
         *
         * 3. @RequestMapping(method = RequestMapping.Method.GET,value = {"/index.do","/main.do"}) 처럼 value는 String 배열일 수 있습니다.
         *  즉 /index.do, /main.do -> IndexController로 맵핑 됩니다.
         */

        for (Class<?> clazz : c) {
            log.debug("cSIze:{}", c.size());
            RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
            if (Objects.nonNull(annotation)) {
                Iterator<String> values = Arrays.stream(annotation.value()).iterator();
                while (values.hasNext()) {
                    String key = annotation.method().name() + "-" + values.next();
                    log.debug("initialize key:{}", key);
                    try {
                        beanMap.put(key, clazz.getConstructor().newInstance());
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        //#todo5-2 ctx(ServletContext)에  attribute를 추가합니다. -> key : CONTEXT_CONTROLLER_FACTORY_NAME, value : ControllerFactory
        ctx.setAttribute(CONTEXT_CONTROLLER_FACTORY_NAME, this);
    }

    private Object getBean(String key) {
        //todo#5-3 beanMap에서 controller 객체를 반환 합니다.
        Object obj = beanMap.get(key);

        if (Objects.isNull(obj)) {
            throw new ControllerNotFoundException(key);
        }
        return obj;
    }

    public Object getController(HttpServletRequest request) {
        //todo#5-4 request의 method, servletPath를 이용해서 Controller 객체를 반환합니다.
        String method = request.getMethod();
        String path = request.getServletPath();
        log.debug("method : {}, path : {}", method, path);
        return getController(method, path);
    }

    public Object getController(String method, String path) {
        //todo#5-5 method, path를 이용해서 Controller 객체를 반환 합니다.
        String key = getKey(method, path);
        log.debug("getController key : {}", key);

        Object obj = getBean(key);
        // controller가 없을때
        if (Objects.isNull(obj)) {
            throw new ControllerNotFoundException(key);
        }
        return obj;
    }

    private String getKey(String method, String path) {
        //todo#5-6  {method}-{key}  형식으로 Key를 반환 합니다.
        //ex GET-/index.do
        //ex POST-/loginAction.do
        StringBuilder sb = new StringBuilder();

        sb.append(method).append("-").append(path);

        return sb.toString();
    }
}
