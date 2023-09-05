// login.js

"use strict";

class Login {
    constructor() {
        this.toggleEvent();
        this.loginEvent();
        this.kakaologinEvent();
        this.naverloginEvent();
        this.joinEvent();
        this.updateEvent();
    }

    toggleEvent() {
        $('.message a').click(() => {
            $('.form').animate({ height: "toggle", opacity: "toggle" }, "slow");
        });

        const url = '/signup';
        const data = {
            // Your JSON data here
        };

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => console.log(data))
            .catch(error => console.error('Error:', error));
    }

    loginEvent() {
        $('#login-form').submit(function (e) {
            e.preventDefault();

            const email = $('#login_email').val();
            const password = $('#login_password').val();
            let userInfo = {};
            console.log('Email:', email);
            console.log('Password:', password);
            userInfo.username = email;
            userInfo.password = password;

            $.ajax({
                type: "post",
                url: "/login",
                data: userInfo,
                success: function (data) {
                    alert('성공');
                    console.log(data);
                    // location.href="/"
                },
                error: function (e) {
                    alert('error!!');
                }
            });
        });
    }

    kakaologinEvent() {
        // Add your Kakao login logic here
    }

    // Define other event handling functions as needed

}

// Instantiate the Login class when the document is ready
$(document).ready(function () {
    new Login();
});

// Export the Login class to be used as a module
export { Login };
