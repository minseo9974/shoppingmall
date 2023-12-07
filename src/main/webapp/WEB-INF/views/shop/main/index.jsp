<%--
  Created by IntelliJ IDEA.
  User: nhn
  Date: 2023/11/08
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function cart_page(cartLink) {
        window.location.href = cartLink;
    }

    let msg = "${msg}";
    if(msg=="ADD_ERR")  alert("장바구니에 존재하는 상품입니다!");
</script>

<!-- 제품 품목 -->
<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <c:forEach var="product" items="${list}">
        <div class="col">
            <div class="card shadow-sm">
                <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                     xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                     preserveAspectRatio="xMidYMid slice" focusable="false">
                    <title>Placeholder</title>
                    <rect width="100%" height="100%" fill="#55595c"></rect>
                    <image href="${product.productImage}" width="100%" height="100%"/>
                </svg>
                <div class="card-body">
                    <p class="card-text">제품명 : ${product.modelName}</p>
                    <p class="card-text">제품 설명 : ${product.description}</p>
                    <p class="card-text">제품 가격 : ${product.unitCost}원</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                            <c:url var="cart_link" value="/cart.do">
                                <c:param name="productId" value="${product.productId}"/>
                            </c:url>
                            <button onclick="cart_page('${cart_link}')" class="btn btn-sm btn-outline-secondary">장바구니 추가
                            </button>
                        </div>
                        <small class="text-muted">제품 넘버:${product.modelNumber}</small>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<!-- 페이징 -->
<div class="d-flex justify-content-center">
    <nav aria-label="Page navigation example">
        <c:if test="${ph.totalCnt == null || ph.totalCnt == 0}">
            <div> 제품이 없습니다.</div>
        </c:if>
        <c:if test="${ph.totalCnt != null && ph.totalCnt != 0}">
            <ul class="pagination">
                <c:if test="${ph.showPrev}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${ph.beginPage-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                    <c:choose>
                        <c:when test="${i eq ph.getSc().getPage()}">

                            <li class="page-item"><a class="page-link"><b>${i}</b></a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="?page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${ph.showNext}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${ph.endPage+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </c:if>
    </nav>
</div>
