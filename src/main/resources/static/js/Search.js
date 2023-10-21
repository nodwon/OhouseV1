$(() => {
    new Search();
});

class Search {
    constructor() {
        this.SearchEvent();

    }
    SearchEvent() {

        let searchType = $("#searchOption").val(); // 예를 들어, select 요소의 값을 가져옴

        // AJAX 요청을 통해 서버로 searchType 값 전송
        $.ajax({
            url: "/search",
            type: "GET",
            data: {
                searchType: searchType,
                searchKeyword: "원하는_키워드" // 검색어를 원하는 값으로 설정
            },
            success: function (data) {
                // 성공적으로 서버에서 데이터를 받았을 때 수행할 작업
                console.log(data);
            },
            error: function (error) {
                // 오류가 발생했을 때 수행할 작업
                console.error("오류 발생:", error);
            }
        });
    }


}

export default Search;