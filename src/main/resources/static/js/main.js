var dict = {
    bicycle: "bicycle",
    motorbike: "motorbike",
    parcel_car: "parcel car",
    small_van: "small van",
    large_van: "large van"
};

function getQuote() {

    if($("#pickup_postcode").val() === "" || $("#delivery_postcode").val() === "") {
        alert("You need to fill both fields.");
        $("#quote").html("");
        return;
    }

    $("#quote").html("Waiting...");

    $.ajax({
        type: "POST",
        url: "/quote",
        data: JSON.stringify({
            "pickupPostcode": $("#pickup_postcode").val(),
            "deliveryPostcode": $("#delivery_postcode").val(),
            "vehicle": $("#vehicle").val()
        }),
        success: presentQuote,
        error: incorrectData,
        dataType: "json",
        contentType: "application/json"
    })
}

function presentQuote(quote) {
    if(quote.price == 0) {
        incorrectData()
        return;
    }

    $("#quote").html(   "A delivery from " + quote.pickupPostcode + " to " + quote.deliveryPostcode +
                        " using a " + dict[quote.vehicle] + " will cost you £" + quote.price);
}

function incorrectData() {
    alert("Incorrect data");
    $("#quote").html("");
}