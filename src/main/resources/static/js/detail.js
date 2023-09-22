"use strict";

$(()=>{
    new LikeButton();
})
class LikeButton {
    constructor() {
        this.likeIcon = document.getElementById("like-icon");
        this.likeButton = document.getElementById("like-button");
        this.postNo = document.getElementById("postId");
        this.liked = false; // 좋아요 상태 (기본: 좋아요하지 않음)
        this.likeCount = 0; // 좋아요 카운트
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

        let likeCountElement = document.getElementById("like-count");
    }
    toggleReplyForm(index) {

        const replyForm = document.getElementById(`replyForm${index}`);
        if (replyForm) {
            replyForm.classList.toggle("show"); // collapse 클래스를 토글하여 보이기/숨기기
        }
    }
    deleteComment(){
        function getCsrfToken() {
            return $('#csrf-token').val();
        }
        const csrfToken = getCsrfToken();
        $('#deleteComment').on('click', (e) => {
        e.preventDefault();
        let commentId = $('#parentcommentId').val();
        // 삭제 확인 다이얼로그 표시
            if (confirm("정말로 삭제하시겠습니까?")) {
                // AJAX를 사용하여 댓글 삭제 요청 보내기
                $.ajax({
                    url: `/comments/${commentId}/delete`,
                    type: "DELETE",
                    success: function () {
                        // 삭제 성공 시 화면에서 해당 댓글 제거
                        const commentElement = document.querySelector(`[data-comment-id="${commentId}"]`);
                        if (commentElement) {
                            commentElement.remove();
                        }
                    },
                    error: function (error) {
                        console.error("댓글 삭제 실패:", error);
                    }
                });
            }
        })
    }

     saveEvent() {
        function getCsrfToken() {
            return $('#csrf-token').val();
        }
        const csrfToken = getCsrfToken();

        $('#submit-button').on('click', (e) => {
            e.preventDefault(); // 기본 동작 중단
                let title = $('#title').val();
            let content = $('#content').val();
            const data = {
                title: title,
                content: content,
            };
            debugger
            $.ajax({
                type: 'POST',
                url: '/posts',
                headers:{
                    'X-CSRF-TOKEN': csrfToken, // CSRF 토큰을 헤더에 추가
                },
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('글이 등록되었습니다.');
                location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
            console.log(data);

        });
    }
}




