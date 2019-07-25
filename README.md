# CleanSimpleLogin

Take home exam in SpiralWorks.

Description: Create an android project with one login page and a welcome page upon login successful, user logins by username & password, data will be summited to a remote server by HTTP based API 

Requirements:  
1. Use Kotlin as programming language, RxAndroid as library for reactive programming, and feel free to use other modern libraries at your choice. 
2. Build a mock server within the app or something similar so that certain users can login to the app. 
3. Show the source code in Github 
4. Attach an runnable build scripts can be executed from console, which builds source code to a debug apk allows running through adb. 

To run the script please see the guide link to build apk and install the app directly to the device.

link: https://developer.android.com/studio/build/building-cmdline

script: 
 - ./gradlew assembleDebug
 - ./gradlew installDebug
 - adb install app/build/outputs/apk/dev/debug/app-dev-debug.apk

Tools in Development:

- [Android Studio]
- Kotlin

This project use this external libs

* [MVVM Design Pattern]
* [Clean Architecture]
* [Koin(Dependency Injection)]
* [Okhttp]
* [Retrofit 2]
* [RxAndroid]
* [Rxjava2]
* [RxKotlin]
* [AndroidX]
* [Material Design 2.0]
* [Navigation]
* [Timber]
* [Gson]
