"use strict";

$(()=>{
    new detail();
})
class detail {
    constructor() {
        this.likeIcon = document.getElementById("like-icon");
        this.likeButton = document.getElementById("like-button");
        this.liked = false; // 좋아요 상태 (기본: 좋아요하지 않음)
        this.likeCount = 0; // 좋아요 카운트
        this.deleteEvent();
        this.toggleReplyForm();
        this.deleteComment();
        this.setupEvents();

    }

    setupEvents() {
        this.likeButton.addEventListener("click", () => this.likeEvent());
    }

    likeEvent() {
        this.liked = !this.liked; // 좋아요 상태를 토글
        let likeText = document.getElementById("like-text");

        if (this.liked) {
            this.likeIcon.classList.remove("heart-empty"); // 빈 하트 클래스 제거
            this.likeIcon.classList.add("heart-filled"); // 채워진 하트 클래스 추가
            likeText.style.display = "none"; // 텍스트 숨김 처리
            this.likeCount++;
        } else {
            this.likeIcon.classList.remove("heart-filled"); // 채워진 하트 클래스 제거
            this.likeIcon.classList.add("heart-empty"); // 빈 하트 클래스 추가
            likeText.style.display = "inline"; // 텍스트 보이기
        }

        // let likeCountElement = document.getElementById("like-count");
    }

    // reply() {
    //     $("#submit-button").on("click", (e) => {
    //         e.preventDefault();
    //         let content = $('#content').val();
    //         let nickname = $('#ParentcommentNickname').val();
    //         let token = $("meta[name='_csrf']").attr("content");
    //         let header = $("meta[name='_csrf_header']").attr("content");
    //         const data = {
    //             nickname: nickname,
    //             content: content,
    //         };
    //         $.ajax({
    //             type: 'POST',
    //             url: '/comments/new',
    //             beforeSend : function(xhr)
    //             {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
    //                 xhr.setRequestHeader(header, token);
    //             },
    //             dataType: 'json',
    //             contentType: 'application/json; charset=utf-8',
    //             data: JSON.stringify(data)
    //         }).done(function () {
    //             alert('댓글이 등록되었습니다.');
    //             location.href = '/';
    //         }).fail(function (error) {
    //             alert(JSON.stringify(error));
    //             console.error("ajax요청 실패")
    //         });
    //         console.log(data);
    //
    //     });
    // }
    toggleReplyForm() {
        $("#reply-button").on("click", (e) => {
            e.preventDefault();
            let postId = $('#post_no').val();
            let commentId = $('#comment-id').val();
            let ParentCommentId = $('#parentcommentId').val();
            let createdAt = $('#commentCreatedAt').val();
            let content = $('#commentContent').val();
            let nickname = $('#commentNickname').val();
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            let addParent = ParentCommentId + 1;
            const data = {
                id: postId,
                content: content,
                createdAt: createdAt,
                nickname: nickname,
                addParent: addParent,
                commentId: commentId

            };
            const replyForm = document.getElementById("replyForm${iterStat.index}");
            if (replyForm.classList.contains("show")) {
                // 숨기기
                replyForm.classList.add("show");
            } else {
                // 보이기
                replyForm.classList.remove("show");

            }
            $.ajax({
                type: 'POST',
                url: '/comments/new',
                beforeSend: function (xhr) {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
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

    deleteEvent() {
        // 삭제 이벤트 구현
        let postId = $('#post_no').val();
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");
        $('#deleteButton').on('click', (e) => {
            e.preventDefault(); // 기본 동작 중단
            const data = {
                postId: postId
            };
            if (confirm("정말로 삭제하시겠습니까?")) {
                // AJAX를 사용하여 댓글 삭제 요청 보내기
                fetch(`/posts/${postId}/delete`, {
                    method: 'DELETE',
                    beforeSend: function (xhr) {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
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
                            alert('글이 삭제되었습니다.');
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
            }
        });
    }


    deleteComment() {
        $('#deleteComment').on('click', (e) => {
            e.preventDefault();

            // 수정된 부분: 데이터 속성을 사용하여 commentId 가져오기
            let postCommentId = $('#ParentId').text();
            let postNo = $('#post_no').val();
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            let url = '/posts/' + postNo ;

            console.log("postNo value:", postNo);
            debugger;
            const data = {
                postCommentId: postCommentId,
                postNo: postNo
            };
            // 삭제 확인 다이얼로그 표시
            if (confirm("정말로 삭제하시겠습니까?")) {
                // AJAX를 사용하여 댓글 삭제 요청 보내기
                fetch(`/comments/${postCommentId}/delete`, {
                    method: 'DELETE',
                    beforeSend: function (xhr) {
                        /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                        xhr.setRequestHeader(header, token);
                    },
                    headers: {
                        'Content-Type': 'application/json',
                        [header]: token // CSRF 헤더 설정
                    },
                    body: JSON.stringify(data)
                })
                    .then(response => {

                        location.href = url;

                        console.log(data);

                    });

            }
        });
    }
}

export default detail;


