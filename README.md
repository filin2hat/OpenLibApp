# Open Library App

A sleek, multiplatform app built with Kotlin Multiplatform and Compose Multiplatform that lets you explore the vast Open Library catalog. Browse, search, and save your favorite books on Android, iOS, and Desktop from a single codebase.

![Open Lib App](screenshot/version_1.png)

## ✨ Features

- **Discover:** Browse the Top 50 trending books of the year.
- **Search:** Find any book in the Open Library database with a powerful search.
- **Book Details:** View comprehensive information, including cover art, author, average rating, page count, synopsis, and available languages.
- **Favorites:** Save books for quick, offline access. Your favorites are stored locally on your device.
- **Seamless UI:** A clean and intuitive interface with smooth navigation between your book lists.

## 📱 Supported Platforms

- **Android**
- **iOS**
- **Desktop** (Windows, macOS, Linux)

## 🛠️ Tech Stack & Architecture

This project leverages modern technologies to deliver a consistent experience across all platforms.

- **Core Frameworks:**
  - [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform-get-started.html) for sharing business logic.
  - [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) for building the shared UI.
- **Architecture:** MVI (Model-View-Intent) to manage UI state.
- **Dependency Injection:** [Koin](https://insert-koin.io/) for managing dependencies across the shared codebase.
- **Networking:** [Ktor](https://ktor.io/) for making API calls to the Open Library API.
- **Database:** [Room](https://developer.android.com/training/data-storage/room) for local persistence of favorite books.
- **Serialization:** [KotlinX Serialization](https://github.com/Kotlin/kotlinx.serialization) for parsing JSON data.
- **Image Loading:** [Coil 3](https://coil-kt.github.io/coil/) for asynchronously loading and displaying book covers.
- **Navigation:** [Jetpack Navigation for Compose](https://developer.android.com/jetpack/compose/navigation) for navigating between screens.

## 🚀 Getting Started

### Prerequisites

- JDK 17 or higher
- Android Studio
- Xcode (for the iOS target)

### How to Run

#### Android
1. Open the project in Android Studio.
2. Select the `openLibraryApp` run configuration.
3. Build and run the app.

#### Desktop
Execute the following command in the project's root directory:
```bash
./gradlew run
```

#### iOS
1. Navigate to the `iosApp` directory.
2. Open `iosApp.xcodeproj` in Xcode.
3. Run an initial build to resolve dependencies (`Cmd+B`).
4. Run the project from Xcode.

## 🔗 Useful Links

- [Open Library APIs](https://openlibrary.org/developers/api)
- [Kotlin Multiplatform Documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Compose Multiplatform Documentation](https://www.jetbrains.com/lp/compose-multiplatform/)
- [Find KMP Libraries](https://klibs.io/)

