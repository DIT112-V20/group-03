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

Any authorized user can access and control their car by entering the URL https://quar.online and logging in with an authorized username and password.

## Set-up: Developer

We assume that you will be building our server on a debian based OS such as Ubuntu
####Server:

#####Build instructions:
```
# install dependencies
sudo apt install -y maven postgresql openjdk-13
# clone the git repository
git clone https://github.com/DIT112-V20/group-03.git
# create a database 
sudo -u postgres psql -c 'create database quaronline;'
sudo -u postgres psql -c "create user quaronline with encrypted password '\x00\x21\x00\x23.~A_f8gLEBM5p8';"
sudo -u postgres psql -c 'grant all privileges on database quaronline to quaronline;'
cd group-03/Server/online.quar/
# launch the web server
mvn spring-boot:run
```

After running the above snippet, you will be able to connect to https://localhost:8443 and log in with demo/demo.

You will then be able to either edit the hosts file on your client - ```nano /etc/hosts``` - or point a domain to your public IP address.

We recommend that you configure your own SSL certificate in application.properties, or remove ours, to run on http. Further, we recommend that you configure internal port forwarding, to use standard ports:
```
iptables -A INPUT -i eth0 -p tcp --dport 80 -j ACCEPT
iptables -A INPUT -i eth0 -p tcp --dport 443 -j ACCEPT
iptables -A PREROUTING -t nat -i eth0 -p tcp --dport 80 -j REDIRECT --to-port 8080
iptables -A PREROUTING -t nat -i eth0 -p tcp --dport 443 -j REDIRECT --to-port 8443
```

#####Code introduction:

In ```src/main/java/online/quar/application``` you will find the source code for the java server.<br>
```src/resources``` is home to javascript, css, sql scripts other assets for the web interface.<br>
Finally ```src/test/java/online/quar/application``` contains all the JUnit tests.

####Car:
The car has two components
- SmartCar connects the car to the web application.
- CarCam provides the live video stream from the car.

#####Build instruction:

- [Microsoft's Visual Studio Code and then the PlatformIO IDE](https://platformio.org/install/ide?install=vscode)
- Open either the ```Car/TeamNull CarCam``` or ```Car/TeamNull SmartCar``` folder in Visual Studio Code.
- Install required libraries:
    - ArduinoJson by Benoit Blanchon
    - ESP32 AnalogWrite by Abdelouahed ERROUAGUY
    - ServoESP32 by Jaroslav Paral
    - Servo by Michael Margolis
    - Smartcar shield by Dimitris Platis
    - VL53L0X by Pololu
- Configure your WiFi credentials in ```main.cpp```
    - char* WiFiSSID = "WiFiSSID";
    - char* WiFiPassword = "WiFiPassword";
- Configure your host details in ```manager/WiFiManager.cpp``` including your root_ca certificate, should you not use ours.
- Upload respective code to your Car's ESP32 controller, and camera

#####Code introduction:

######SmartCar

```manager/WiFiManager.cpp``` all logic for communicating with the server.<br>
```manager/WiFiManager.cpp``` all logic for controlling car behaviour.<br>
```util/HardwareUtil.cpp``` logic for communicating with car and sensor hardware.<br>
```main.cpp``` Boot setup then repeatedly polls server, and calls Movement Manager to make car respond accordingly.

######CarCam

```main.cpp``` Connects to WiFi and serves live video stream.

## User Manual

#### The Welcome-page

- This page welcomes the user to the website. Simply click the link to move on to the login-page.

#### The Login-page

- Enter a valid username and password in their respective field and then hit the SIGN IN-button to proceed.

#### The Car Controller-page

- As long as the power switch is on for the car, the user should now see a camera feed in the middle of the screen. The user can steer the car by clicking on the camera stream. When clicking, a blue joystick appears which can be used for steering the car freely. The four colored boxes represents the cars sensors, and turns orange and then red, the closer to an obstacle the car gets.
- Record Route-button: after clicking this button, the car starts to record the route given by the user. 
- Stop Recording-button: immediately stops the recording of the route. 
- Play Recorded Route-button: by pressing this button, the car starts driving according to the last recorded route.

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
