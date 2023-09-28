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
    toggleReplyForm(index) {

        const replyForm = document.getElementById(`replyForm${index}`);
        if (replyForm) {
            replyForm.classList.toggle("show"); // collapse 클래스를 토글하여 보이기/숨기기
        }
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
            fetch(`/posts/${postId}/delete`, {
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
        });

    }
    deleteComment(){
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

}




