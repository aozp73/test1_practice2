<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>


        <div class="align_center">
            <h1>회원수정 페이지</h1>
        </div>
        <hr>

        <%@ include file="../layout/nav.jsp" %>


            <div class="align_center">
                <form action="/login" method="post">
                    <input type="text" name="username" value="ssar" placeholder="Enter username" readonly><br>
                    <input type="password" name="password" value="1234" required><br>
                    <input type="email" name="email" value="ssar@nate.com" required><br>
                    <button type="submit">수정완료</button>
                </form>
            </div>

            <%@ include file="../layout/footer.jsp" %>