# PostApp

## How to run the app
1. Clone the repository
2. Open it on AS
3. Run/Debug the project on a real device or an emulator
4. Play with the app PostApp

## Architecture
The proposed architecture is Clean Architecture also integrating MVP. We have the data layer "data" where the models are located, then the "domain" layer that is in charge of making the remote calls and the handling of the answers, and finally the presentation layer where the data is shown to the user.

Within the presentation layer, there are the presenters who are responsible for all the logistics of processing and the views that are responsible for presenting the data in activities, fragments or dialogues.

## Third party libraries
Libraries used as Active Android for persistence management within the application.
Dagger 2 for the injection of dependencies within the classes.
Retrofit to make calls to the remote API.
Espresso and JUnit for unit testing and instrumental tests

