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
        stompClient.subscribe('/topic/car', function (carStatus) {
            showCarStatus(JSON.parse(carStatus.body));
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

        carSetSpeed = carSetSpeed === 0? 1: (20*Math.log(data.distance/1.5));
        carSetSpeed = ((-90 < carSetAngle) && (carSetAngle < 90))? carSetSpeed : -(carSetSpeed);

        if(carSetSpeed <= 0){
            if(carSetAngle <= 0) {
                carSetAngle = - (carSetAngle + 180);
            } else {
                carSetAngle = - (carSetAngle - 180);
            }
        }
        console.log("carSetAngle: " + carSetAngle);
    }

    stompClient.send("/app/carControl", {}, JSON.stringify({'carId': carId, 'carSetSpeed': carSetSpeed, 'carSetAngle': carSetAngle}));
}

function recordRoute(data) {
    stompClient.send("/app/startRecRoute", {}, JSON.stringify({'carId': data}));
}

function stopRecordRoute(data) {
    stompClient.send("/app/stopRecRoute", {}, JSON.stringify({'carId': data}));
}

function replayRoute(data) {
    stompClient.send("/app/playRecRoute", {}, JSON.stringify({'carId': data}));
}

    function showCarStatus(carStatus) {
    console.log(carStatus);
    $("#speedometer").html("<p>" + "Car speed: " + carStatus.carActualSpeed + "m/s</p>");
    if (carStatus.carCollisionAvoidance) {
        showNotification("The car is avoiding a collision!");
    } else if (carStatus.carObstacleAvoidance) {
        showNotification("The car is avoiding an obstacle!");
    } else {
        showNotification("");
    }
    $("#leftDistance").css("backgroundColor", convertDistanceToColor(carStatus.leftFrontDistance));
    $("#rightDistance").css("backgroundColor", convertDistanceToColor(carStatus.rightFrontDistance));
    $("#frontDistance").css("backgroundColor", convertDistanceToColor(carStatus.frontDistance));
    $("#backDistance").css("backgroundColor", convertDistanceToColor(carStatus.rearDistance));
}

function  convertDistanceToColor(distance) {
    let green = "green";
    let red = "red";
    let orange = "orange";
    let safeDistance = 1000;
    let warningDistance = 400;

    if (distance === 0 || distance > safeDistance) {
        return green;
    } else if (distance <= safeDistance && distance >= warningDistance) {
        return orange;
    } else {
        return red;
    }
}

function showNotification(notificationText) {
    let notificationDiv = $("#notification");
    notificationDiv.html(notificationText);
    if (notificationText === "") {
        notificationDiv.css("display", "none");
    } else {
        notificationDiv.css("display", "block");
    }
}
