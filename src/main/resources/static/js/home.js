$(() => {
    new home();
});

class home {
    constructor() {
        this.mypage();
        this.deleteMember();
    }
    mypage() {
        $('#submit-button').on('click', (e) => {
            e.preventDefault(); // 기본 동작 중단
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            const data = {
                token: token,
                header: header,
            };
            $.ajax({
                type: 'GET',
                url: '/mypage',
                beforeSend : function(xhr)
                {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                    xhr.setRequestHeader(header, token);
                },
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('글이 등록되었습니다.');
                location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
                console.error("ajax요청 실패")
            });
            console.log(data);

        });
    }
    // 탈퇴
    deleteMember() {
        // 삭제 이벤트 구현
        let email = $('#email').text(); // 이렇게 수정
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");
        debugger;
        $('#secession').on('click', (e) => {
            e.preventDefault(); // 기본 동작 중단
            const data = {
                email: email
            };
            fetch(`/members/${email}/delete`, {
                method: 'DELETE',
                beforeSend : function(xhr)
                {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                    xhr.setRequestHeader(header, token);
                },
                headers: {
                    'Content-Type': 'application/json',
                    [header]: token // CSRF 헤더 설정
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (response.ok) {
                        alert('탈퇴 하셨습니다.');
                        location.href = '/';
                    } else {
                        return response.json();
                    }
                })
                .then(error => {
                    alert(JSON.stringify(error));
                    console.error("ajax 요청 실패");
                })
                .catch(error => {
                    console.error("오류 발생:", error);
                });
            console.log(data);
        });

    }

}

export default postfrom;