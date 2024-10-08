# Sandbox Android App

This repository is a sandbox app I use to collect and experiment with various Android development techniques and concepts. 
The app is a living project that reflects my current understanding and exploration of Android technologies, best practices, and development patterns.

## Key Features
- **Compose Animations:** Features a variety of animations using Jetpack Compose, including animated visibility, and state-based transitions for dynamic UI interactions and smooth user experience.
- **Compose Animation - Swipe-to-Delete:** Implementation of Jetpack Compose's gesture system to manage list item interactions.
- **Compose Screen Adaptability:** Demonstrates responsive UI layouts with Compose, ensuring optimal display and usability across different screen sizes and orientations.
- **Compose Custom Preview Annotation:** Implements custom @Preview annotations in Jetpack Compose, allowing for tailored previews with specific configurations (such as theme, device size, and UI mode), streamlining the UI development process.
- **Compose and View Interoperability:** Showcases seamless integration between Jetpack Compose and traditional Android Views, including the use of View Composition Strategy to manage the lifecycle of composables within Views, ensuring efficient resource handling and smooth UI updates in hybrid layouts.
- **Compose Paging:** Implements efficient pagination using Jetpack Compose's Paging library, enabling smooth, infinite scrolling lists with support for loading states and error handling while managing large data sets.
- **Compose Bottom Sheets:** Implements both modal and persistent bottom sheets using Jetpack Compose, providing flexible UI components for displaying additional content, actions, or options while maintaining smooth transitions and user interaction.
- **Compose Navigation:** Utilizes Jetpack Compose's Navigation component to manage in-app navigation, demonstrating navigation graphs, argument passing, deep linking, and back-stack management for a smooth user flow between screens.
- **Kotlin Coroutines and Flow:** Illustrates asynchronous programming, handling background tasks, and reactive data streams using coroutines and Flow.
- **Room Database:** A sandbox for local data persistence, showcasing best practices in database management with Room and clean architecture principles.
- **Multi-Module Architecture:** Experimentation with separating concerns into multiple modules to create a more scalable app structure.

## Technologies and Libraries Used
- **Kotlin:** Primary programming language.
- **Jetpack Compose:** Modern UI toolkit for building native Android UIs.
- **Room:** Local database persistence.
- **Coroutines and Flow:** Asynchronous and reactive programming.
- **Gradle:** Build automation and task management.
- **Other libraries:** Retrofit.

## Project Structure

The project is structured to separate concerns and experiment with best practices in Android development. This includes using MVVM, unidirectional data flow, repository patterns, and dependency injection.
Getting Started

To run this app locally:
- Clone the repository:
- git clone https://github.com/Zanerast/Sandbox.git
- Open the project in Android Studio.
- Sync Gradle and install dependencies.
- Run the app on an emulator or connected device.

## Contributions

This sandbox is primarily for personal experimentation, but feedback and suggestions are welcome! Feel free to raise an issue or fork the repository to explore new concepts.
