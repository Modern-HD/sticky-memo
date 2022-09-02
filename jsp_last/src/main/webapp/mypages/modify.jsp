<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="ssuvo" value="${ses }" scope="session"/>
<jsp:include page="../header.jsp"></jsp:include>
<c:if test="${ssuvo eq null }">
<script>
    location.replace("/");
</script>
</c:if>
    <div id="mod_windows" class="col-10 mx-auto my-5 my-xl-0">
        <div class="d-flex justify-content-center flex-wrap">
            <div id="mod_nick_window" class="memo-green col-12 col-sm-11 col-md-10 col-lg-9 col-xl-5 col-xxl-4 mb-5 mb-xl-0 shadow">
                <div class="memo-header">
                </div>
                <div class="mod-forms text-center position-relative">
                    <div class="position-absolute top-50 start-50 translate-middle">
                        <h2 class="mb-1">닉네임 변경</h2>
                        <div id="n_tooltip"></div>
                        <input type="hidden" id="uno" value="<c:out value="${ssuvo.uno }"/>">
                        <input type="hidden" id="old_nick" value="<c:out value="${ssuvo.nick_name }"/>">
                        <input type="text" name="n_nick" id="n_nick" placeholder="변경할 닉네임" class="mt-2 mb-3"><br>
                        <button id="mod_nick_btn" type="button" class="btn btn-outline-primary">변경</button>
                    </div>
                </div>
            </div>
            <div class="mx-xl-3 mx-xxl-5"></div>
            <div id="mod_pwd_window" class="memo-pink col-12 col-sm-11 col-md-10 col-lg-9 col-xl-5 col-xxl-4 shadow">
                <div class="memo-header">
                </div>
                <div class="mod-forms text-center position-relative">
                    <div class="position-absolute top-50 start-50 translate-middle">
                        <h2 class="mb-1">비밀번호 변경</h2>
                        <div id="p_tooltip"></div>
                        <input type="password" name="p_old" id="p_old" placeholder="현재 비밀번호" class="mt-2 mb-1"><br>
                        <input type="password" name="p_new" id="p_new" placeholder="새로운 비밀번호" class="mb-1"><br>
                        <input type="password" name="p_chk" id="p_chk" placeholder="새로운 비밀번호 확인" class="mb-3"><br>
                        <button id="mod_pwd_btn" type="button" class="btn btn-outline-primary">변경</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="btn btn-outline-info px-2 py-0 position-fixed bottom-0 end-0 m-3">
        <a href="/" class="text-black fs-2"><i class="bi bi-house-door-fill"></i></a>
    </div>
    <script src="/resources/js/mypage.js"></script>
</body>
</html>