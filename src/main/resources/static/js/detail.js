$(() => {
    new Detail();
});

class Detail {
    constructor() {
        this.saveEvent();
        this.deleteEvent();
        // this.updateEvent(); // 필요한 경우 추가
    }

    saveEvent() {
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

    deleteEvent() {
        // 삭제 이벤트 구현
    }

    // 필요한 경우 updateEvent 메서드 추가
}

export default Detail;
