function validate() {
    var name = document.getElementById("name");
    var surname = document.getElementById("surname");
    var login = document.getElementById("login");
    var password = document.getElementById("password");
    var password2 = document.getElementById("password2");
    var info = document.getElementById("info");

    var result = true;
    var infoText = "";

    if(login.value.length < 5) {
        login.style.background = "#0341fc";
        infoText = infoText + "Niepoprawny login (min 5 znaków)<br>";
        result = false;
    } else {
        login.style.background = null;
    }

    if(name.value.length < 3 || name.value[0].toUpperCase() != name.value[0]) {
        name.style.background = "#0341fc";
        infoText = infoText + "Niepoprawne imie (min 3 znaki, wielka litera na początku)<br>";
        result = false;
    } else {
        name.style.background = null;
    }
if(surname.value.length < 2 || surname.value[0].toUpperCase() != surname.value[0]) {
        surname.style.background = "#0341fc";
        infoText = infoText + "Niepoprawne nazwisko (min 2 znaki, wielka litera na początku)<br>";
        result = false;
    } else {
        surname.style.background = null;
    }

    if(password.value.length < 5) {
        password.style.background = "#0341fc";
        infoText = infoText + "Niepoprawne haslo (min 5 znaków)<br>";
        result = false;
    } else {
        password.style.background = null;
    }

    if(password.value != password2.value) {
        password2.style.background = "#0341fc";
        infoText = infoText + "Niepoprawnie powtórzone hasło<br>";
        result = false;
    } else {
        password2.style.background = null;
    }

    info.innerHTML = infoText;

    return result;
}
