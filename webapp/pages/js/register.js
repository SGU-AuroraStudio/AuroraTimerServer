window.onload = function() {

    document.getElementById("name").addEventListener("blur", checkName, false);
    document.getElementById("id").addEventListener("blur", checkId, false);
    document.getElementById("pwd").addEventListener("blur", checkPwd, false);
    document.getElementById("conf").addEventListener("blur", checkConf, false);
}

function checkId(){
    var objElement = document.getElementById("id");
    var msgElement = document.getElementById("id" + "Msg");
    //var reg = /^[1-9][0-9]{5,13}$/;
    //if(reg.test(objElement.value)) {
    if(objElement.value != "") {
        objElement.className = "right";
        // msgElement.innerHTML = "<font color='green'>yes</font>"
        return true;
    } else {
        objElement.className = "wrong";
        // msgElement.innerHTML = "<font color='red'>no</font>";
        return false;
    }
}

function checkName(){
    var objElement = document.getElementById("name");
    var msgElement = document.getElementById("name" + "Msg");
    if (objElement.value != "") {
        // msgElement.innerHTML = "<font color='green'>yes</font>"
        objElement.className = "right";
        return true;
    } else {
        // msgElement.innerHTML = "<font color='red'>no</font>";
        objElement.className = "wrong";
        return false;
    }
}

function checkPwd(){
    var objElement = document.getElementById("pwd");
    var msgElement = document.getElementById("pwd" + "Msg");
    var reg = /^\w{6,12}$/;
    if(reg.test(objElement.value)) {
        objElement.className = "right";
        // msgElement.innerHTML = "<font color='green'>yes</font>"
        return true;
    } else {
        objElement.className = "wrong";
        // msgElement.innerHTML = "<font color='red'>no</font>";
        return false;
    }
}

function checkConf(){
    var objElement = document.getElementById("conf");
    var msgElement = document.getElementById("conf" + "Msg");
    var pwd = document.getElementById("pwd");
    if (pwd.value == objElement.value) {
        // msgElement.innerHTML = "<font color='green'>yes</font>";
        objElement.className = "right";
        return true;
    } {
        // msgElement.innerHTML = "<font color='red'>no</font>";
        objElement.className = "wrong";
        return false;
    }
}

function checkAll(){
    if (checkPwd() && checkName() && checkId() && checkConf()) {
        var nName = document.getElementById("name");
        var pwd = document.getElementById("pwd");
        var conf = document.getElementById("conf");
        conf.value = hex_md5(pwd.value);
        pwd.value = conf.value;
        return true;
    } else {
        return false;
    }
}
