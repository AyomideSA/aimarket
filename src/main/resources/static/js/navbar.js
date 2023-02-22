function setUp() {
    closeForm();
}

function openSIForm() {
    document.getElementById("loginBox").style.display = "block";
    document.getElementById("signUp").style.display = "none";
    document.getElementById("signIn").style.display = "block";
}

function openSUForm() {
document.getElementById("loginBox").style.display = "block";
    document.getElementById("signIn").style.display = "none";
    document.getElementById("signUp").style.display = "block";
}

function closeForm() {
    document.getElementById("loginBox").style.display = "none";
    document.getElementById("signIn").style.display = "none";
    document.getElementById("signUp").style.display = "none";
}


