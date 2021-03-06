<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="domain.MemoVO" %>
<%@ page import="domain.UserVO" %>
<%@ page import="repository.MemoDAO" %>
<%@ page import="repository.MemoDAOImpl" %>

<c:set var="ssuvo" value="${ses }" scope="session"/>
<%
    UserVO who = (UserVO)session.getAttribute("ses");
    MemoDAO mdao = new MemoDAOImpl();
    List<MemoVO> list = null;
    if (who != null) {
    	list = mdao.selectList(who.getUid());
    }
%>
<jsp:include page="header.jsp"/>
    <section class="container-fluid my-5">
        <div id="memobox" class="d-flex justify-content-center flex-wrap">
            <% if (list != null) {
            for(MemoVO mvo : list) { %>
            <div class="memo memo-<%=mvo.getColor()%> m-3" data-mno="<%=mvo.getMno()%>">
                <div class="memo-header px-2 py-1 text-end">
                    <div class="col-6 float-start px-2 text-start text-muted fst-italic">
                        <%=mvo.getUid() %>
                    </div>
                    <div class="col-6 float-end">
                        <i class="bi bi-pencil-square update-btn" data-mno="<%=mvo.getMno()%>"></i>
                        <i class="bi bi-x-square delete-btn" data-mno="<%=mvo.getMno()%>"></i>
                    </div>
                </div>
                <div class="memo-section p-3">
                <%=mvo.getContent()%>
                </div>
            </div>
            <% } %> 
            <% } %> 
        </div>
        <c:if test="${ssuvo ne null }">
        <div class="col-10 mx-auto text-end">
            <span class="text-muted">${ssuvo.uid } | </span>
            <a href="/memo/logout" class="text-muted">๋ก๊ทธ์์</a>
        </div>
        </c:if>
        <c:if test="${ssuvo eq null }">
	    <div id="login_form" class="position-fixed col-4 top-50 start-50 shadow translate-middle memo-yellow text-center form-modal">
	        <div class="memo-header"></div>
	        <div class="position-absolute top-50 start-50 translate-middle col-10">
	            <h3>๋ก๊ทธ์ธ ํ๊ณ? Sticky Memo๋ฅผ ์ด์ฉํด๋ณด์ธ์.</h3>
	            <form action="/memo/login" method="post">
	                <input type="text" name="uid" id="uid" placeholder="ํ์ ์์ด๋" class="my-1"><br>
	                <input type="password" name="pwd" id="pwd" placeholder="ํ์ ๋น๋ฐ๋ฒํธ" class="mb-2"><br>
	                <button type="submit" class="btn btn-outline-primary">๋ก๊ทธ์ธ</button>
	            </form>
	            <div class="mt-3 text-muted">์์ง ํ์์ด ์๋์?๊ฐ์?</div>
	            <div id="to_sign_up_form" class="text-muted text-decoration-underline cursor-pointer">
	                ํ์๊ฐ์
	            </div>
	        </div>
	    </div>
	    <div id="sign_up_form" class="position-fixed col-4 top-50 start-50 shadow translate-middle memo-blue text-center d-none form-modal">
	        <div class="memo-header"></div>
	        <div class="position-absolute top-50 start-50 translate-middle col-10">
	            <h3>Sticky Memo ๊ฐ์์ ํ์ํฉ๋๋ค.</h3>
	            <form action="/memo/sign_up" method="post" class="mb-3">
	                <input type="text" name="uid" id="uid" placeholder="ํ์ ์์ด๋" class="my-1"><br>
	                <input type="password" name="pwd" id="pwd" placeholder="ํ์ ๋น๋ฐ๋ฒํธ" class="mb-1"><br>
	                <input type="password" name="pwdchk" id="pwdchk" placeholder="๋น๋ฐ๋ฒํธ ํ์ธ" class="mb-2"><br>
	                <button type="submit" class="btn btn-outline-primary">ํ์๊ฐ์</button>
	            </form>
	            <span id="to_login_form" class="text-muted text-decoration-underline cursor-pointer">
	                ๋ก๊ทธ์ธ ํ๋ฉด์ผ๋ก
	            </span>
	        </div>
	    </div>
        </c:if>
    </section>
<jsp:include page="del_modal.jsp"/>
    <script>
    
    </script>
    <script src="/resources/index.js"></script>
</body>
</html>