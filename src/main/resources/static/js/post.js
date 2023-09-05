$(() => {
    new Form();
});

export class Form {
    constructor() {
        this.savePost =require("templates/posts/form.html");
    }

    savePost() {
        $('#submit-button').on('click', (e) => {
            e.preventDefault(); // 기본 동작 중단
            let title = $('#title').val();
            let content = $('#content').val();

            let data = {
                title: title,
                content: content
            };
            $.ajax({
                type: 'POST',
                url: '/posts',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('글이 등록되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
            console.log(data)
        });

    }
}
