const main = {
    init() {
        const _this = this;
        const btnSave = document.getElementById('btn-save');
        const btnUpdate = document.getElementById('btn-update');
        const btnDelete = document.getElementById('btn-delete');

        btnSave.addEventListener('click', () => {
            _this.save();
        });

        btnUpdate.addEventListener('click', () => {
            _this.update();
        });

        btnDelete.addEventListener('click', () => {
            _this.delete();
        });
    },

    save() {
        const data = {
            title: document.getElementById('title').value,
            content: document.getElementById('content').value
        };

        axios({
            method: 'POST',
            url: '/posts',
            data: data
        }).then(function (response) {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        })
            .catch(function (error) {
                alert(JSON.stringify(error));
            });
    },

    update() {
        const data = {
            title: document.getElementById('title').value,
            content: document.getElementById('content').value
        };

        const id = document.getElementById('id').value;

        axios({
            method: 'PUT',
            url: '/posts/' + id,
            data: data
        }).then(function (response) {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        })
            .catch(function (error) {
                alert(JSON.stringify(error));
            });
    },

    delete() {
        const id = document.getElementById('id').value;

        axios({
            method: 'DELETE',
            url: '/posts/' + id
        }).then(function (response) {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        })
            .catch(function (error) {
                alert(JSON.stringify(error));
            });
    }

};
// $('.message a').click(function() {
//     $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
// });
// $(document).ready(function() {
//     $('.message a').click(function() {
//         $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
//     });
// })
main.init();