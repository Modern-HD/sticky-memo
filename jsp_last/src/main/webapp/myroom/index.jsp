<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>

<c:set var="ssuvo" value="${ses }" scope="session"/>
<jsp:include page="header.jsp"/>
    <section class="container-fluid my-5">
        <div id="ch_title" class="position-relative">
            <h1 class="text-muted text-center"><span id="title_ch_name">나의 메모</span>
                <i class="bi bi-search fs-3"></i>
            </h1>
            <div id="ch_info" class="position-absolute translate-middle-x start-50 pe-none opacity-0">
                <div class="my-4 mx-2 px-4">
                    <div class="spinner-border text-light"></div>
                </div>
            </div>
            <div id="ch_search" class="position-absolute start-50 text-center d-none">
                <p>채널 검색</p>
                <p><input type="text" list="channels" name="ch_search_name" id="ch_search_name"> <button id="ch_move_btn" type="button" class="btn btn-secondary mt-2 mt-md-0 mx-auto d-block d-md-inline">채널 이동</button></p>
                <datalist id="channels"></datalist>
            </div>
        </div>
        <c:if test="${ssuvo eq null }">
		<div class="col-12 mt-5 text-center">
		    <p class="fs-2 text-secondary">로그인 후 이용해주세요.</p>
		    <a href="/" class="btn btn-primary">
		        로그인 페이지
		    </a>
		</div>
		<script>
		    location.replace("/");
		</script>
		</c:if>
        <c:if test="${ssuvo ne null }">
        <div id="menus" class="col-11 col-lg-10 my-2">
            <div class="btn-group float-end">
                <div id="sort_menu" class="btn-group">
                    <button type="button" class="btn btn-outline-warning dropdown-toggle" data-bs-toggle="dropdown">
                        <i class="bi bi-list"></i> <span class="d-none d-md-inline">정렬 설정</span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><h5 class="dropdown-header">정렬 기준</h5></li>
                        <li><label class="dropdown-item"><input type="checkbox" id="sort_created" checked> 생성일</label></li>
                        <li><label class="dropdown-item"><input type="checkbox" id="sort_modified"> 수정일</label></li>
                        <li><h5 class="dropdown-header">정렬 순서</h5></li>
                        <li><label class="dropdown-item"><input type="checkbox" id="sort_desc" checked> 내림차순</label></li>
                        <li><label class="dropdown-item"><input type="checkbox" id="sort_asc"> 오름차순</label></li>
                    </ul>
                </div>
                <div id="user_menu" class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown">
                        <i class="bi bi-person-fill"></i><span class="d-none d-sm-inline"> <c:out value="${ssuvo.nick_name}"/></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li class="d-sm-none"><h5 class="dropdown-header"><c:out value="${ssuvo.nick_name}"/></h5></li>
                        <li><h5 class="dropdown-header"><c:out value="${ssuvo.email}"/></h5></li>
                        <li><span id="logout_btn" class="dropdown-item cursor-pointer">로그아웃</span></li>
                        <li><a class="dropdown-item" href="/mypage/">마이페이지</a></li>
                    </ul>
                </div>
            </div>
        </div>
        </c:if>
        <div id="memobox" class="d-flex justify-content-center flex-wrap">
        </div>
    </section>
    <jsp:include page="modals.jsp"/>
    <c:if test="${ssuvo ne null }">
    <script>
        const pk_val = '<c:out value="${ssuvo.uno}"/>';
    </script>
    </c:if>
    <script src="/resources/js/room_module.js"></script>
    <script src="/resources/js/myroom.js"></script>
</body>
</html>