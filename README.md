# Purpose

This app was written as assignment during the interview process for Daimler.
It displays data fetched from the Dogs api and displays a list of all available breeds as well as a detailpage for each breed,
which displays images of this breed. Images can be like and persisted in a local repository. Another screen shows all currently favored images.

# Arichtecture

*Pragmatic* clean architecture. Going some shortcuts for the sake of simplicity for this project.
usually there would be models for data und view layers as well as interface instead of the implementations directly.
The pattern used on top is MVVM:

# Third party libraries

The app uses
- Retrofit/OkHttp (Networking)
- RxJava (Asynchronous calls)
- Koin (Dependency Injection)
- Timber (Logging)
- Room (Persistence)
- Ktlint (formatting)

# TODO

If this app would be continued there are some tasks open, which needed to be resolved

- Error and loading states for network requests
- Cleaning up the FavoritesAdpater
- Investigate reflection problem with ktlint
- Unit tests for pretty much everything, there are only a few sample tests provided
