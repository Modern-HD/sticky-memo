<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div id="delete_modal" class="my-modal col-9 col-md-7 col-lg-6 col-xl-5 col-xxl-4 bg-white d-none">
        <div class="my-modal-header bg-danger text-white">
            삭제 확인
        </div>
        <div>
            <span id="delete_input"></span>
            <div class="text-center fs-4 my-modal-section">
                정말로 삭제하시겠습니까?
            </div>
            <div class="text-end col-10 mx-auto my-3">
                <button type="button" id="del_commit_btn" class="btn btn-danger mx-2">삭제</button>
                <button type="button" class="btn btn-outline-secondary modal-close-btn">아니오</button>
            </div>
        </div>
    </div>
    
    <div id="file_form_modal" class="my-modal col-9 col-md-7 col-lg-6 col-xl-5 col-xxl-4 bg-white d-none">
        <div class="my-modal-header bg-success text-white">
            이미지 메모 작성
        </div>
        <div>
            <div class="text-center fs-4 my-modal-section">
                <span id="file_hidden_area"></span>
                <input type="file" name="image_file" id="image_file" accept="image/png, image/jpeg, image/jpg, image/gif">
            </div>
            <div class="text-end col-10 mx-auto my-3">
                <button type="button" id="image_reg_btn" class="btn btn-success mx-2">전송</button>
                <button type="button" id="image_mod_btn" class="btn btn-success mx-2 d-none">수정</button>
                <button type="button" class="btn btn-outline-secondary modal-close-btn">취소</button>
            </div>
        </div>
    </div>