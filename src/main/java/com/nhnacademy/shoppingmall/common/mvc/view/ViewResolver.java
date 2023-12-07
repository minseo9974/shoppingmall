package com.nhnacademy.shoppingmall.common.mvc.view;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ViewResolver {

    public static final String DEFAULT_PREFIX="/WEB-INF/views/";
    public static final String DEFAULT_POSTFIX=".jsp";
    public static final String REDIRECT_PREFIX="redirect:";
    public static final String DEFAULT_SHOP_LAYOUT="/WEB-INF/views/layout/shop.jsp";
    public static final String DEFAULT_ADMIN_LAYOUT="/WEB-INF/views/layout/admin.jsp";
    public static final String LAYOUT_CONTENT_HOLDER = "layout_content_holder";

    private final String prefix;
    private final String postfix;

    public ViewResolver(){
        this(DEFAULT_PREFIX,DEFAULT_POSTFIX);
    }
    public ViewResolver(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public  String getPath(String viewName){
        //todo#6-1  postfix+viewNAme+postfix 반환 합니다.
        StringBuilder sb = new StringBuilder();
        sb.append(DEFAULT_PREFIX);
        if (viewName.startsWith("/")) {
            sb.append(viewName.substring(1));
        }
        else{
            sb.append(viewName);
        }
        sb.append(DEFAULT_POSTFIX);
        log.debug("view Resolver getPath :{}", sb.toString());
        return sb.toString();
    }

    public boolean isRedirect(String viewName){
        //todo#6-2 REDIRECT_PREFIX가 포함되어 있는지 체크 합니다.
        log.debug("view isRedirect :{}",viewName);
        return viewName.substring(0, REDIRECT_PREFIX.length()).equalsIgnoreCase(REDIRECT_PREFIX);
    }

    public String getRedirectUrl(String viewName){
        //todo#6-3 REDIRECT_PREFIX를 제외한 url을 반환 합니다.
        return viewName.substring(REDIRECT_PREFIX.length());
    }

    public String getLayOut(String viewName){

        /*todo#6-4 viewName에
           /admin/경로가 포함되었다면 DEFAULT_ADMIN_LAYOUT 반환 합니다.
           /admin/경로가 포함되어 있지않다면 DEFAULT_SHOP_LAYOUT 반환 합니다.
        */
        log.debug("viewResolver :{}", viewName);
        if (viewName.contains("admin")){
            return DEFAULT_ADMIN_LAYOUT;
        }

        return DEFAULT_SHOP_LAYOUT;
    }
}
