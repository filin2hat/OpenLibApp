# Kotlin Multiplatform Project Overview

Проект предназначен для разработки приложений на **Android**, **iOS** и **Desktop** с использованием Kotlin Multiplatform.

## Структура проекта

### `/composeApp`
Папка для кода, который будет использоваться во всех приложениях Compose Multiplatform. Включает несколько подпапок:
- **`commonMain`**: код, общий для всех платформ.
- Остальные папки содержат Kotlin-код, который компилируется только для указанной в названии папки платформы.  
  Например, если вы хотите использовать **CoreCrypto** от Apple для iOS, код нужно поместить в папку **`iosMain`**.

### `/iosApp`
Содержит iOS-приложения.  
Даже если интерфейс создан с использованием Compose Multiplatform, вам понадобится эта папка как точка входа для iOS-приложения.  
Кроме того, здесь можно добавлять код на **SwiftUI**.

## Полезные ссылки
- Узнайте больше о [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html).
- Open Library Web APIs: [ссылка](https://openlibrary.org/developers/api).
