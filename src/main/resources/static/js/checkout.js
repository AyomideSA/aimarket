function checkOutError(){

    var elementCardNo = document.getElementById("cardNumber");
    var elementCVV = document.getElementById("cvv");

    if(elementCardNo.classList.contains("invalid")){
        openCheckout();
    }
    else if(elementCVV.classList.contains("invalid")){
        openCheckout();
    }
    else{
        closeCheckout();
    }
}

function closeCheckout() {
    document.getElementById("checkoutBox").style.display = "none";
    document.getElementById("enterCheckoutDetails").style.display = "none";
}

function openCheckout() {
    document.getElementById("checkoutBox").style.display = "block";
    document.getElementById("enterCheckoutDetails").style.display = "block";
}