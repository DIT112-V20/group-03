var stompClient = null;

function setConnected(connected) {
    // $("#connect").prop("disabled", connected);
    // $("#disconnect").prop("disabled", !connected);
    // if (connected) {
    //     $("#conversation").show();
    // }
    // else {
    //     $("#conversation").hide();
    // }
    // $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/carWebControl');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        //TODO: Implement car control object
        stompClient.subscribe('/topic/car', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

// function sendName() {
//     stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
// }
//
// function showGreeting(message) {
//     $("#greetings").append("<tr><td>" + message + "</td></tr>");
// }

// $(function () {
//     $("form").on('submit', function (e) {
//         e.preventDefault();
//     });
//     $( "#connect" ).click(function() { connect(); });
//     $( "#disconnect" ).click(function() { disconnect(); });
//     $( "#send" ).click(function() { sendName(); });
// });

function sendJoystickInput(data) {

    //TODO: Real car ID
    var carId = 1;
    var carSetSpeed = 0;
    var carSetAngle = 0;

    if(data != null) {
        carId = 1;
        carSetAngle = data.angle.degree > 270? ( - data.angle.degree + 450) : (- data.angle.degree + 90);

        carSetSpeed = ((-90 < carSetAngle) && (carSetAngle < 90))? data.distance * 2 : -(data.distance * 2);

        if(carSetSpeed <= 0){
            if(carSetAngle <= 0) {
                carSetAngle += 180;
            } else {
                carSetAngle -= 180;
            }
        }
        console.log("carSetAngle: " + carSetAngle);
    }

    stompClient.send("/app/carControl", {}, JSON.stringify({'carId': carId, 'carSetSpeed': carSetSpeed, 'carSetAngle': carSetAngle}));
}
