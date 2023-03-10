function setUp() {
    closeForms();
}

function closeForms() {
    closePriceForm();
    closeDetailsForm();
}

function openPriceForm() {
    document.getElementById("priceBox").style.display = "block";
    document.getElementById("enterPrice").style.display = "block";
}

function openDetailsForm() {
    document.getElementById("detailsBox").style.display = "block";
    document.getElementById("enterDetails").style.display = "block";
}

function closePriceForm() {
    document.getElementById("priceBox").style.display = "none";
    document.getElementById("enterPrice").style.display = "none";
}

function closeDetailsForm() {
    document.getElementById("detailsBox").style.display = "none";
    document.getElementById("enterDetails").style.display = "none";
}