function register() {
    var username = $("input[ name='nameR']").val();
    var password = $("input[ name='passwordR']").val();
    var phone = $("input[ name='phoneR']").val();
    $.ajax({
        url: "/register",
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        data: {name: username, password: password, phone: phone},
        success: function (data) {
            console.info(data)
            if (data.code == "SUCCESS") {
                alert("注册成功");
                window.location.href = '/login';
            } else {
                alert(data.code);
            }
        },
        error: function (e) {

        }
    });
}

function login() {
    var username = $("input[ name='username']").val();
    var password = $("input[ name='password']").val();

    $.ajax({
        url: "/login",
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        data: {username: username, password: password},
        success: function (data) {
            debugger
            console.info(data)
            if (data.code == "SUCCESS") {
                window.location.href = '/user';
            } else if (data.code == "ADMIN") {
                window.location.href = '/admin';
            } else {
                alert(data.code);
            }
        },
        error: function (e) {
            console.info(e)
        }
    });
}
