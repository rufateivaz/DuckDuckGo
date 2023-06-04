# Fetcing data from a RESTful Web API

The app fetches and displays data from a DuckDuckGo RESTful Web API. The app is comprised of two parts, a list and a detail. It supports both portrait and landscape orientations on both phones and tablets.

On Phones, the list and detail are separate screens, but on Tablets, list and detail appear on the same screen.
* For the list view, data is displayed as a text only, vertically scrollable list of character names.
* The app offers search functionality that filters the character list according to characters whose titles or descriptions contain the query text.
* Clicking on an item loads the detail view of that character, including the character’s image, title, and description. 
* For the image in the detail view, the app uses the URL in the "Icon" field of the API JSON response.
* Using productFlavors, two variants of the app is created, using a single shared codebase. Each variant has a different name, package-name, and url that it pulls data from.

## Simpsons
<img src="GIF/simpsons_phone.gif" width="200" height="400"/> <img src="GIF/simpsons_tablet.gif" width="600" height="400"/>

## Ware
<img src="GIF/wire_phone.gif" width="200" height="400"/> <img src="GIF/wire_tablet.gif" width="600" height="400"/>

## Approach
* Implemented in 100% <b>Kotlin</b> and <b>Unit-tested</b>.
* <b>MVVM</b> clean architecture.
* Multi-module: app, data and domain (viewmodel connects to repository through use-cases but can also connect directly).
* Dependency injection: <b>Koin</b>
* Api calls: <b>RxJava3</b> (can be replaced with Kotlin coroutines).
* Network configuration: <b>Retrofit, OkHttp, Gson, GsonConverter</b>.
* Database operation: <b>Room</b> (this can be improved by adding database migration features).
* UI: <b>Glide, RecyclerView, CardView, Adapter, Activity, Fragment, FragmenContainer, LiveData, ViewBinding, SlidingPaneLayout</b> (inspired from [here](https://learn.microsoft.com/en-us/dual-screen/android/jetpack/window-manager/slidingpanelayout)).
