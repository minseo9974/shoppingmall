window.addEventListener("DOMContentLoaded", function () {
    "use strict";

    const check = this.document.getElementById("user_id");

    check.addEventListener("input", function (e) {
        e.preventDefault();

        const idAvailable = function (response) {
            console.log("id:" + response.userId);
            console.log("status:" + response.status);
            if (response.status === "available") {
                document.getElementById("idStatus").innerText = "사용 가능한 아이디입니다.";
                document.getElementById("idStatus").style.color = "green";
            }else{
                document.getElementById("idStatus").innerText = "이미 존재하는 아이디입니다.";
                document.getElementById("idStatus").style.color = "red";
            }
        };
        idCheck(check.value, idAvailable);
    });

    const form = this.document.getElementById("form_register");

    const validateForm = function (form){
        if (form["user_id"].value.trim() == ""){
            alert("empty id");
            form["user_id"].focus();
            return false;
        }else if (form["user_id"].value.length<4 || form["user_id"].value.length>12){
            document.getElementById("idStatus").innerText = "아이디 길이는 4~12 자리입니다.";
            document.getElementById("idStatus").style.color = "black";
        }else if (form["user_name"].value.trim() == ""){
            alert("empty name");
            form["user_name"].focus();
            return false;
        }else if (form["user_password"].value.trim() == "") {
            alert("empty password");
            form["user_password"].focus();
            return false;
        }else if (form["user_password"].value !== form["user_password2"].value ){
            alert("비밀번호가 일치하지 않습니다.");
            form["user_password"].focus();
            return false;
        } else if (form["user_birth"].value.trim() == "") {
            alert("empty birth");
            form["user_birth"].focus();
            return false;
        }
        return true;
    }

    form.addEventListener("submit", function (e) {
        e.preventDefault();
        if (!validateForm(e.target)) {
            return;
        }
    });
});

function idCheck(userId,idCheck){
    console.log("call idCheck " + userId);
    const xhr = new XMLHttpRequest();
    const url = "/json";

    const data = {
        userId: userId,
    };
    xhr.addEventListener("load", function () {
        if (this.status === 200) {
            console.log(this.response);
            idCheck(this.response);
        }
    });

    xhr.open("POST", url);
    xhr.setRequestHeader("content-type", "application/json");
    xhr.responseType = "json";
    xhr.send(JSON.stringify(data));
}