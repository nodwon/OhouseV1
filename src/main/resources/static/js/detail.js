$(() => {
    new Detail();
});
// export default Detail;


class Detail {
    constructor() {

       // this.saveEvent();
        this.deleteEvent();
        // this.updateEvent(); // 필요한 경우 추가
        this.likeEvent() = require("@/posts/detail.html");
    }

    likeEvent() {
        let likeIcon = document.getElementById("like-icon");
        let liked = false; // 좋아요 상태 (기본: 좋아요하지 않음)
        $("#like-button").on("click", (e) => {
            liked = !liked; // 좋아요 상태를 토글
            if (liked) {
                likeIcon.classList.remove("heart-empty"); // 빈 하트 클래스 제거
                likeIcon.classList.add("heart-filled"); // 채워진 하트 클래스 추가
            } else {
                likeIcon.classList.remove("heart-filled"); // 채워진 하트 클래스 제거
                likeIcon.classList.add("heart-empty"); // 빈 하트 클래스 추가
            }

            // 좋아요 카운트 업데이트
            $("#like-count").text(likeCount);
        });
    }
    // saveEvent() {
    //     function getCsrfToken() {
    //         return $('#csrf-token').val();
    //     }
    //     const csrfToken = getCsrfToken();
    //
    //     $('#submit-button').on('click', (e) => {
    //         e.preventDefault(); // 기본 동작 중단
    //             let title = $('#title').val();
    //         let content = $('#content').val();
    //         const data = {
    //             title: title,
    //             content: content,
    //         };
    //         debugger
    //         $.ajax({
    //             type: 'POST',
    //             url: '/posts',
    //             headers:{
    //                 'X-CSRF-TOKEN': csrfToken, // CSRF 토큰을 헤더에 추가
    //             },
    //             dataType: 'json',
    //             contentType: 'application/json; charset=utf-8',
    //             data: JSON.stringify(data)
    //         }).done(function () {
    //             alert('글이 등록되었습니다.');
    //             location.href = '/';
    //         }).fail(function (error) {
    //             alert(JSON.stringify(error));
    //         });
    //         console.log(data);
    //
    //     });
    // }

}