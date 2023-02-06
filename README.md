## Description
This project main idea to shows the list of pets and pets-related detailed information on tap one of them 
in the mobile application.

**Requirements**
- Write your application in Kotlin/Java.
- Ensure your application supports Android API 21.
- There are two screens. The first screen is a list of pets. Each pet item includes pet images and names. 
- Tapping on a pets list item in first screen brings up the second screen. This screen is a details screen showing the information related to the given pet which is    given in the first screen.
- The application content should only be visible during working hours and days i.e (Mon-Fri 09:00-18:00). 
- In case of non-working hours, the application should block the user from accessing the application content and show the popup message to the user.


**Bonus** 
- Sorting of data list
- Unit tests



# Sepia Pets app
<!-- to comment use such block-->
<!--[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)-->

<p align="center">
  <a href="https://android-arsenal.com/api?level=21" target="_blank" rel="noopener noreferrer">
    <img src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/>
  </a>
</p>

<p float="left">
<img src="https://user-images.githubusercontent.com/41982681/217048627-214e175b-6947-4846-8eb9-e0a0c390fe53.png" width="260" height="400"/>
<img src="https://user-images.githubusercontent.com/41982681/217048826-618aa226-3fed-4030-8d1f-eb38ba5a7172.png" width="260" height="400"/>
<img src="https://user-images.githubusercontent.com/41982681/217049525-65cb5f5a-9fdb-4bd2-b255-92af573bc080.png" width="260" height="400"/>
</p>  


https://user-images.githubusercontent.com/41982681/217054808-a94d39ee-d568-4d52-8673-25c50409e9d7.mp4


## API
I have decided to use [Mocki.io API](https://mocki.io/fake-json-api) to mock given json and access generated api by retrofit.

## Tech stack
* Minimum SDK level 21
* [Kotlin](https://kotlinlang.org/) based + Coroutines for asynchronous.
* Dagger for dependency injection.
* JetPack
  * LiveData - notify domain layer data to views.
  * Lifecycle - dispose of observing data when lifecycle state changes.
  * ViewModel - UI related data holder, lifecycle aware.
  * Navigation Component - handle everything needed for in-app navigation.
* Architecture
  * MVVM Architecture (View- ViewModel - Model)
  * Repository pattern
* [Picasso]([https://github.com/bumptech/glide](https://github.com/square/picasso)) - loading images.
* [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and fetch data from network.
* mockito-test library




