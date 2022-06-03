<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="domain.MemoVO" %>
<%@ page import="service.Service" %>
<%
	Service svc = new Service();
	List<MemoVO> list = svc.read();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./style.css">
    <title>Sticky Memo</title>
</head>
<body class="bg-light">
    <div id="memo_nav" class="container">
        <div id="memo_nav_section" class="col-12 bg-white shadow nav-close">
            <div class="col-11 mx-auto my-3 overflow-hidden overflow-scroll">
                <div class="col-12 my-col">
                    <div class="memo-nav-items memo-yellow mx-3" data-type="sticky" data-color="yellow">
                        <div class="memo-header"></div>
                    </div>
                    <div class="memo-nav-items memo-pink mx-3" data-type="sticky" data-color="pink">
                        <div class="memo-header"></div>
                    </div>
                    <div class="memo-nav-items memo-green mx-3" data-type="sticky" data-color="green">
                        <div class="memo-header"></div>
                    </div>
                    <div class="memo-nav-items memo-blue mx-3" data-type="sticky" data-color="blue">
                        <div class="memo-header"></div>
                    </div>
                </div>
            </div>
        </div>
        <div id="memo_nav_footer" class="text-center">
            <div id="memo_nav_more" class="mx-auto bg-white">
                <i class="bi bi-chevron-compact-down fs-4"></i>
            </div>
        </div>
    </div>
    <section class="container-fluid my-5">
        <div id="memobox" class="d-flex justify-content-center flex-wrap">
			<% for(MemoVO mvo : list) { %>
			<div class="memo memo-<%=mvo.getColor()%> m-3" data-id="<%=mvo.getId()%>">
                <div class="memo-header px-2 py-1 text-end">
                    <i class="bi bi-pencil-square update-btn" data-id="<%=mvo.getId()%>"></i>
                    <i class="bi bi-x-square delete-btn" data-id="<%=mvo.getId()%>"></i>
                </div>
                <div class="memo-section p-3">
                <%=mvo.getContent()%>
                </div>
            </div>
			<% } %>
        </div>
    </section>
	<div id="delete_modal" class="my-modal col-4 bg-white d-none">
        <div class="my-modal-header bg-danger text-white">
            삭제 확인
        </div>
        <form action="remove.sm" method="post">
            <span id="delete_input"></span>
            <div class="text-center fs-4 my-modal-section">
                정말로 삭제하시겠습니까?
            </div>
            <div class="text-end col-10 mx-auto my-3">
                <button type="submit" class="btn btn-danger mx-2">삭제</button>
                <button type="button" class="btn btn-outline-secondary modal-close-btn">아니오</button>
            </div>
        </form>
    </div>
    <script src="./index.js"></script>
</body>
</html>