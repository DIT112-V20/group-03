# group-03 - TeamNull [![Build Status](https://travis-ci.com/DIT112-V20/group-03.svg?branch=master)](https://travis-ci.com/DIT112-V20/group-03)

## What are we making?

We decided to build a remote control system for driving the car with the ability to detect and avoid hitting the obstacles on its way.
The system utilizes a web application to let the user drive the car through the streaming video which the system gets from a camera attached to the car.
Once the car detects any obstacles it stops and chooses a new free direction.


## Why are we making it? What problems does it solve?

It is often the case that we must be somewhere, but it is not safe for humans. Examples such as land mine detection, exploring mars, or, in 2020, for the elderly to go to the supermarket.
We believe that these situations could be improved if an affordable remotely controlled vehicle can carry out a task, in the place of a human. 
We would, therefore, like to develop an accessible and affordable remotely controlled vehicle platform, which can be adapted to meet all of these requirements.
Further inspired by the lastest coronavirus situation, we are not being able to meet in person as a team to work on the car, and team members have different mobile and desktop OS,
and software preferences, therefore, we have decided to build a web application, to ensure that our platform is accessible to as many as possible. 
This platform for remotely driving a car can be further developed in future projects to be applied on larger cars or robots.



## How are we going make it?

We will develop a java based web application, with a Spring based GUI in IntelliJ, to stand as the user-facing application. 
We will further use the Arduino IDE to develop the application that runs on the ESP32 on the car, as this is a platform that can be easily expanded for future development.
In order for the application running on the car, and the user-facing application to communicate with one another, we will develop the java application to be run on a server, 
which the car can reach, so long as it has a WiFi connection.
 
## Set-up: User

Any authorized user can access and control the car by entering the URL https://quar.online and log in with an authorized username and password.

## Set-up: Developer

## User Manual

#### The Welcome-page
- This page welcomes the user to the website. Simply click the link to move on to the login-page.

#### The Login-page
- Enter a valid username and password in their respective field and then hit the SIGN IN-button to proceed.

#### The Car Controller-page
- As long as the power switch is on for the car, the user should now see a camera feed in the middle of the screen. The user can steer the car by clicking on the camera stream. When clicking, a blue joystick appears which can be used for steering the car freely. The four colored boxes represents the cars sensors, and turns orange and then red, the closer to an obstacle the car gets.
- Record Route-button: after clicking this button, the car starts to record the route given by the user. 
- Stop Recording-button: immediately stops the recording of the route. 
- Play Recorded Route-button: by pressing this button, the car starts driving acording to the last recorded route.

## Technical Summary

### Hardware:

- [ESP32](https://www.espressif.com/en/products/devkits/esp32-devkitc/overview)
- [Smart Car](https://www.hackster.io/platisd/getting-started-with-the-smartcar-platform-1648ad)
- [Camera](https://www.electrokit.com/produkt/esp32-cam-utvecklingskort-med-wifi-bluetooth-och-kamera/)
- [Server](https://www.digitalocean.com/products/droplets/)

### Software:

- [Arduino IDE](https://www.arduino.cc/)
- [IntelliJ](https://www.jetbrains.com/idea/)
- [Spring](https://spring.io/)
- [Java](https://www.java.com)
- [JavaScript](https://developer.oracle.com/javascript/)
- [HTML](https://whatwg.org/)
- [CSS](https://www.w3.org/Style/CSS/)
- [jQuery](https://jquery.com)

### Developers:

- [Altug Altetmek](https://github.com/altetmek)
- [Dia Istanbuly](https://github.com/istanbuly)
- [Emil Gustafsson](https://github.com/Gustmill)
- [Filip Lewenhagen](https://github.com/filiplew)
- [Leith Hobson](https://github.com/leithhobson)
- [Martin Esfahani](https://github.com/ma-esf)
- [Max Zimmer](https://github.com/gusmaxzi)
