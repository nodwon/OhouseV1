$(() => {
    new postfrom();
});

class postfrom {
    constructor() {
        this.saveEvent();

    }
    saveEvent() {

        $('#submit-button').on('click', (e) => {
            e.preventDefault(); // 기본 동작 중단
            let title = $('#title').val();
            let content = $('#content').val();
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            const data = {
                title: title,
                content: content,
            };
            $.ajax({
                type: 'POST',
                url: '/posts',
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
    home(){
        $('#cancel-button').on('click', (e) => {
            e.preventDefault(); // 기본 동작 중단
            location.href = '/';
        });
    }

}

export default postfrom;