Project Akko
============

Android App for AR.Drone 2.0 - early days yet.

![Android phone aboard AR.Drone 2.0](https://raw.githubusercontent.com/tekkies/akko/master/onboard-android.jpg)

So far:

- Crude SMS land or emergency cut off.
   - If android is aboard AR.Drone and the drone is out of Wi-Fi range, you can send an SMS txt message to the onboard phone to land the drone.
      - txt `akko land` to make the drone land.
      - txt `akko kill` to perform emergency motor kill.

Build
=====
You will need to update git submodules to get the javadrone library.
Build using Eclipse with the Android SDK.  
You should be able to import the included Eclipse project.
