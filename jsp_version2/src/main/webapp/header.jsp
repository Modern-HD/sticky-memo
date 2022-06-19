<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/style.css">
    <title>Sticky Memo</title>
</head>
<body class="bg-light">
<c:set var="ssuvo" value="${ses }" scope="session"/>
    <c:if test="${ssuvo ne null }">
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
    </c:if>