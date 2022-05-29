var MAIL_ERROR_MESSAGE_IN = "*Enter eMail";

var PASSWORD_ERROR_MESSAGE_IN = "*Enter password";

function validateMailIn() {
    var isValid = true;

    var mail = document.getElementById("emailInput").value;

    if (mail == "") {

        document.getElementById("emailInErr").innerHTML = MAIL_ERROR_MESSAGE_IN;

        isValid = false;
    }

    return isValid;
}

function validatePasswordIn() {
    var isValid = true;
    var password = document.getElementById("passwordInput").value;

    if (password == "") {

        document.getElementById("passwordInErr").innerHTML = PASSWORD_ERROR_MESSAGE_IN;

        isValid = false;
    }

    return isValid;
}

function validateIn() {
    var isValid = true;

    document.getElementById("emailInErr").innerHTML = "<br>";
    document.getElementById("passwordInErr").innerHTML = "<br>";

    if (!validateMailIn()) {
        isValid = false;
    }

    if (!validatePasswordIn()) {
        isValid = false;
    }
    return isValid;
}
