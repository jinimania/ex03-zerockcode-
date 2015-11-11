<%--
  @author LeeSoohoon
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../include/header.jsp" %>

<section class="content">
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">Board List</h3>
                </div>
                <div class="box-body">
                    <button id="newBtn">New Board</button>
                </div>
            </div>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">LIST PAGING</h3>
                </div>
                <div class="box-body">
                    <table class="table table-bordered">
                        <tr>
                            <th style="width: 10px;">BNO</th>
                            <th>TITLE</th>
                            <th>WRITER</th>
                            <th>REGDATE</th>
                            <th style="width: 40px;">VIEWCNT</th>
                        </tr>

                        <c:forEach items="${list}" var="boardVO">

                            <tr>
                                <td>${boardVO.bno}</td>
                                <td><a href='/board/readPage${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${boardVO.bno}'>${boardVO.title}</a></td>
                                <%--<td>${boardVO.title}</td>--%>
                                <td>${boardVO.writer}</td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                                                    value="${boardVO.regdate}"/></td>
                                <td><span class="badge bg-red">${boardVO.viewcnt}</span></td>
                            </tr>

                        </c:forEach>
                    </table>
                </div>
                <div class="box-footer">
                    <div class="text-center">
                        <ul class="pagination">
                            <c:if test="${pageMaker.prev}">
                                <li><a href="listPage${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a></li>
                            </c:if>
                            <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                                <li <c:out value="${pageMaker.cri.page == idx ? 'class=active' : ''}"/>>
                                    <a href="listPage${pageMaker.makeQuery(idx)}">${idx}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                <li><a href="listPage${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</div>

<form id="jobForm">
    <input type="hidden" name="page" value="${pageMaker.cri.page}"/>
    <input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum}"/>
</form>

<script>
    var result = '${msg}';

    if (result == 'success') {
        alert("처리가 완료되었습니다.");
    }
    
    $(".pagination li a").on("click", function (event) {
        event.preventDefault();

        var targetPage = $(this).attr("href");
        var start = targetPage.indexOf("=");
        var end = targetPage.indexOf("&");
        var page = targetPage.substring(start + 1, end);


        var jobForm = $("#jobForm");
        jobForm.find("[name='page']").val(page);
        jobForm.attr("action", "/board/listPage").attr("method", "get");
        jobForm.submit();
    });
</script>

<%@include file="../include/footer.jsp" %>
