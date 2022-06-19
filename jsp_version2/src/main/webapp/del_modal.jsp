<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div id="delete_modal" class="my-modal col-4 bg-white d-none">
        <div class="my-modal-header bg-danger text-white">
            삭제 확인
        </div>
        <form action="/memo/remove" method="post">
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