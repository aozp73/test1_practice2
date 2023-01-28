<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

        <div class="align_center">
        <ul>
            <c:choose>
               <c:when test="${principal == null}">
                <li><a href="/loginForm">로그인</a></li>
                <li><a href="/joinForm">회원가입</a></li>
               </c:when>
            
               <c:otherwise>
                <li><a href="/board/list">나의 게시글 목록</a></li>
                <li><a href="/user/updateForm">회원정보</a></li>
                <li><a href="/logout">로그아웃</a></li>
               </c:otherwise>
            </c:choose>
        </ul>
        </div>