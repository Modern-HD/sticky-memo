<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp"></jsp:include>
<c:if test="${ssuvo eq null }">
<script>
    location.replace("/");
</script>
</c:if>
    <div id="mypage_login" class="position-fixed top-50 start-50 translate-middle memo-yellow shadow">
        <div class="memo-header">
        </div>
        <div class="text-center my-3">
            비밀번호를 입력해주세요<br>
            <c:if test="${mod_chk ne null && mod_chk < 1 }">
                <span class="text-danger">비밀번호가 다릅니다.</span><br>
            </c:if>
            <form action="/mypage/login" method="post">
	            <input type="password" name="l_pwd" id="l_pwd" class="my-2"><br>
	            <button id="mypage_login_btn" type="submit" class="btn btn-outline-primary">로그인</button>
            </form>
        </div>
    </div>
    <div class="btn btn-outline-info px-2 py-0 position-fixed bottom-0 end-0 m-3">
        <a href="/" class="text-black fs-2"><i class="bi bi-house-door-fill"></i></a>
    </div>
</body>
</html>