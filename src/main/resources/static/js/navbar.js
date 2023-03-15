function setUp() {
    var elementUsername = document.getElementById("floatingUsername");
    var elementPassword = document.getElementById("floatingPassword1");
    var elementName = document.getElementById("floatingName");
    var elementEmail = document.getElementById("floatingEmail");

    var elementLogEmail = document.getElementById("floatingInput");
    var elementLogPassword = document.getElementById("floatingPassword");

    //var elementCardNo = document.getElementById("cardNumber");
    //var elementCVV = document.getElementById("cvv");

    if(elementUsername.classList.contains("invalid")){
        openSUForm();
    }
    else if(elementPassword.classList.contains("invalid")){
        openSUForm();
    }
    else if(elementName.classList.contains("invalid")){
        openSUForm();
    }
    else if(elementEmail.classList.contains("invalid")){
        openSUForm();
    }
    else if(elementLogEmail.classList.contains("invalid")){
        openSIForm();
    }
    else if(elementLogPassword.classList.contains("invalid")){
       openSIForm();
    }
    /*else if(elementCardNo.classList.contains("invalid")){
        openSUForm
    }*/
    else{
        closeForm();
    }
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

