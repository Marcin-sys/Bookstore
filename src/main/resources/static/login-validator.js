function validate() {
    var login = document.getElementById("login");
    var password = document.getElementById("password");
    var info = document.getElementById("info");

    var result = true;
    var infoText = "";

    if(login.value.length < 5) {
        login.style.background = "#fac0c0";
        infoText = infoText + "Niepoprawny login (min 5 znaków)<br>";
        result = false;
    } else {
        login.style.background = null;
    }
    if(password.value.length < 5) {
        password.style.background = "#fac0c0";
        infoText = infoText + "Niepoprawne haslo (min 5 znaków)<br>";
        result = false;
    } else {
        password.style.background = null;
    }
    info.innerHTML = infoText;

    return result;
}
