"use strict";

$(() => {
    new SignUp();
});

class SignUp {
    constructor() {
        this.GeneralSignUp();

    }
    GeneralSignUp() {

        $('#GeneralLogin').on('click', (e) => {
            e.preventDefault(); // 기본 동작 중단
            let email = $('#email').val();
            let password = $('#Password').val();
            let name = $('#register-name').val();
            let nickname = $('#nickname').val();
            let birthday = $('#birthday').val();
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            const data = {
                email: email,
                password: password,
                name: name,
                nickname: nickname,
                birthday: birthday
            };
            $.ajax({
                type: 'POST',
                url: '/members/signup',
                beforeSend : function(xhr)
                {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                    xhr.setRequestHeader(header, token);
                },
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('회원가입이 되었습니다.');
                location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
                console.error("ajax 요청 실패")
            });
            console.log(data);

        });
    }


}

export default SignUp;