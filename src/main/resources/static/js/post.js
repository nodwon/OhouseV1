import axios from 'axios';

// Form 클래스를 export 합니다.
export class Form {
    constructor() {
        // 폼 데이터를 가져오는 메서드로 변경합니다.
        this.savePost = this.getFormData;

        // 삭제 버튼 클릭 이벤트 처리 메서드로 변경합니다.
        this.deletePost = this.handleDeleteButtonClick;
    }

    // 폼 데이터를 가져오는 메서드
    savePost() {
        // title과 content 필드의 값을 가져옵니다.
        let title = $("#title").val();
        let content = $("#content").val();

        // 데이터 객체 생성
        let data = {
            title: title,
            content: content
            // 다른 필드도 추가해야 할 수 있습니다.
        };

        // 게시물 저장 요청을 보냅니다.
        axios.post('/posts', JSON.stringify(data), {
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            }
        }).then((res) => {
            if (res.status === 201) {
                // 게시물 저장 성공 시 메인 페이지로 이동합니다.
                window.location.href = '/';
            }
        }).catch((error) => {
            console.error(error);
        });
    }

    // 삭제 버튼 클릭 이벤트 처리 메서드
    handleDeleteButtonClick() {
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
                    window.location.href = "/";
                },
                error: function() {
                    alert("게시물 삭제에 실패했습니다.");
                }
            });
        }
    }
}

// 문서가 준비되면 Form 클래스를 인스턴스화합니다.
$(document).ready(function() {
    new Form();
});
