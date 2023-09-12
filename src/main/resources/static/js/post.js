$(() => {
    new Form();
});

export class Form {
    constructor() {
        this.savePost =require("/posts/form.html");
        this.deletePost = require("/templates/posts/updatePostForm.html")
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
    deletePost() {
        $(document).ready(function() {
            // 삭제 버튼 클릭 이벤트 처리
            $("#deleteButton").on("click", function(e) {
                e.preventDefault(); // 링크 기본 동작 막기

                let postId = $(this).data("postid"); // 게시물 ID 가져오기

                // 삭제 확인 다이얼로그 표시
                let confirmDelete = confirm("게시물을 삭제하시겠습니까?");
                if (confirmDelete) {
                    // AJAX 요청으로 게시물 삭제
                    $.ajax({
                        url: "/posts/" + postId + "/delete",
                        type: "DELETE",
                        success: function(data) {
                            // 삭제 성공 시 메인 페이지로 리디렉션
                            window.location.href = "/main";
                        },
                        error: function() {
                            alert("게시물 삭제에 실패했습니다.");
                        }
                    });
                }
            });
        });
    }
}
