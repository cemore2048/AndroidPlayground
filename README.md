# Repl.it IDE
## Requirements

This project needs to point to your local version of the Android SDK. Go to
`local.properties` and add the path to your SDK by replacing mine.


## Overview
### Architecture

The project uses the Model View View Model pattern structured per the following

#### View (MainActivity)

- The view's whole responsibility is to render content on the screen.
- In my particular case I just used an Activity, but with more time I'd use two fragments. One for the IDE portion, and the other for the console
This would make it much easier for the users to customize the layout ie. where they want to console to show up in relation to the IDE, like we do in our usual dev setups
- Given this was a new project, I took the opportunity to use Jetpack compose *for the first time*
 to do this, and quite liked it.

#### View Model

 - The view model handles the logic and state required to accurately render the information on the screen.
 - It facilitates a uni-directional architecture where the View observes `LiveData` inside of the viewModel in order to receive updates when the state changes
 - With more time I would have implemented another layer (like in Redux or MVI) that maintains a more intricate mutable state, but also for the purposes of this one screen, the win reward ration wasn't good enough to spend the time to do it

#### Repository

 - The repository is injected into the view model and is in charge of transforming data that comes back from various sources (in this case the network, but it doesn't need to care)
 - Having this sit between the `ExecutionEndpoint` and the View Model helps us create a testable class.
 - With more time, this type of class (and more explicitly this `ExecutionRepository`) would handle pulling values like saved code state from a cache (shared prefs, in memory, or database)


#### ExecutionEndpoint

 - This Endpoint is what's actually making the network request.
 - It's injected into the repository and can be customized to include more endpoints or parameters.

### Library Callouts

#### Dagger
This makes dependency injection easier. The way it's set up could be broken out a little more, (specifying scope, adding subcomponents), but for now works

#### GSON
Deserializes the network requests

#### Retrofit
Makes network calls much easier

#### RXJava
Helps us create reactive streams and create a threading model


## Features I'd Add
- Saving code repos. This would mean that you would have a menu of items on a menu on the left that you could open
- Syntax highlighting so that it doesn't look like plain text. Would be interesting to explore ways to do this on mobile
- Like I mentioned before, I'd like to have the layout be a little bit more customizable where you
can choose where you want the console
- More languages support

## Screenshots
![Code not run](/screen_shot_1.png)

![Code after ran](/screen_shot_2.png)
