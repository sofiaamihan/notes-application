# Notes Application
A simple notes application built using Kotlin and Jetpack Compose to demonstrate the modern architectural pattern in computer software.
This application allows users to create, read, update, and delete notes.

## Features
- Create new notes
- Read existing notes
- Update existing notes
- Delete notes
- Simple and intuitive user interface

## Architecture
Follows a clean MVVM (Model-View-ViewModel) Architecture consisting of:
- **Entity**: Represents the data model for the notes.
- **DAO (Data Access Object)**: Provides methods for accessing the database.
- **Database**: The Room database that holds the notes data.
- **Repository**: Acts as a mediator between the data sources (DAO) and the ViewModel.
- **ViewModel**: Manages UI-related data in a lifecycle-conscious way.
- **ViewModel Factory**: Creates instances of the ViewModel with the required parameters.

## Tech Stack
- Kotlin
- Jetpack Compose
- Room Database
- Respective Extensions and Coroutines Support
- Android Architecture Components (ViewModel, LiveData)

## Considerations
This application serves as a solid introduction to using Room for local data persistence and the structure is designed for easy scalability. 
Enhancements such as caching and remote server connectivity can be easily implemented without significant refactoring.
