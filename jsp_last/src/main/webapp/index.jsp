<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"/>
    <c:set var="ssuvo" value="${ses }" scope="session"/>
    <c:if test="${ssuvo ne null }">
        <script>
            location.replace("/main/me");
        </script>
    </c:if>
	<section>
	    <div id="login_form" class="position-fixed col-10 col-md-8 col-lg-6 col-xl-4 top-50 start-50 shadow translate-middle memo-yellow text-center form-modal">
	        <div class="memo-header"></div>
	        <div class="position-absolute top-50 start-50 translate-middle col-10">
	            <h3>로그인 하고 Sticky Memo를 이용해보세요.</h3>
	                <div id="l_danger" class="text-danger"></div>
	                <input type="text" name="l_email" id="l_email" placeholder="회원 이메일" class="my-1"><br>
	                <input type="password" name="l_pwd" id="l_pwd" placeholder="회원 비밀번호" class="mb-2"><br>
	                <button type="button" id="login_btn" class="btn btn-outline-primary">로그인</button>
	            <div class="mt-3 text-muted">아직 회원이 아니신가요?</div>
	            <div id="to_sign_up_form" class="text-muted text-decoration-underline cursor-pointer">
	                회원가입
	            </div>
	        </div>
	    </div>
	    <div id="sign_up_form" class="position-fixed col-10 col-md-8 col-lg-6 col-xl-4 top-50 start-50 shadow translate-middle memo-blue text-center d-none form-modal">
	        <div class="memo-header"></div>
	        <div class="position-absolute top-50 start-50 translate-middle col-10">
	            <h3>Sticky Memo 가입을 환영합니다.</h3>
	            <div id="s_danger" class="text-danger"></div>
	            <div id="s_tooltip"></div>
	                <input type="text" name="s_email" id="s_email" placeholder="회원 이메일" class="my-1"><br>
	                <input type="text" name="s_nick_name" id="s_nick_name" placeholder="회원 닉네임" class="mb-1"><br>
	                <input type="password" name="s_pwd" id="s_pwd" placeholder="회원 비밀번호" class="mb-1"><br>
	                <input type="password" name="s_pwdchk" id="s_pwd_chk" placeholder="비밀번호 확인" class="mb-2"><br>
	                <button type="button" id="sign_up_btn" class="btn btn-outline-primary mb-3">회원가입</button><br>
	            <span id="to_login_form" class="text-muted text-decoration-underline cursor-pointer">
	                로그인 화면으로
	            </span>
	        </div>
	    </div>
	</section>
    <script src="/resources/js/login.js"></script>
</body>
</html>