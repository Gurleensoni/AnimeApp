# 📺 AnimeApp — Android Anime Browser

AnimeApp is a modern Android application that allows users to browse top anime, search anime titles, and view detailed information including synopsis, genres, episodes, trailer links, and main cast.

The app is built using **MVVM architecture**, **Room database**, **Retrofit networking**, and **Dependency Injection**, following modern Android development best practices.

---

## ✨ Features

### 📃 Anime List Screen

* Displays top anime list from API
* Pagination support (loads next page while scrolling)
* Offline caching via Room database
* Fast loading from local DB
* Search anime by title

### 📄 Anime Detail Screen

* Anime poster
* Title
* Rating
* Episodes count
* Genres
* Synopsis
* Trailer link
* Main cast information

### 💾 Offline Support

* Anime list cached locally
* Details stored locally
* Faster reload without API calls

---

## 🏗 Architecture Used

The app follows **MVVM architecture**:

```
UI (Activity)
    ↓
ViewModel
    ↓
Repository
    ↓
Local DB (Room) + Remote API (Retrofit)
```

### Layer responsibilities

#### UI Layer

* Activities & adapters
* Observes LiveData from ViewModel
* Displays data to users

#### ViewModel Layer

* Holds UI data
* Handles pagination & search logic
* Requests data from repository

#### Repository Layer

* Single source of truth
* Decides when to fetch from API or DB
* Handles mapping of API response to database entities

#### Data Layer

* Room database for local caching
* Retrofit API for network calls

---

## 🛠 Technologies Used

### Language

* Kotlin

### Architecture

* MVVM

### Networking

* Retrofit
* Gson Converter

### Database

* Room Persistence Library

### Async Operations

* Kotlin Coroutines

### Image Loading

* Glide

### UI Components

* RecyclerView
* LiveData
* ViewModel

### Dependency Injection

* Hilt (or manual DI depending on your final setup)

---

## 📦 API Used

Anime data is fetched from:

```
Jikan API
https://api.jikan.moe/v4/
```

Endpoints used:

* Top anime list
* Anime details
* Characters & cast

---

## 🔁 Data Flow

Example flow when opening app:

1. ViewModel requests page 1 from repository.
2. Repository fetches from API.
3. Data is stored in Room database.
4. LiveData updates UI automatically.
5. RecyclerView displays list.

Detail screen:

1. ViewModel loads data from DB first.
2. Repository refreshes details from network.
3. DB updates → UI auto refreshes.

---

## 🚀 Pagination Logic

* List loads page-by-page
* Next page loads when user scrolls near bottom
* Prevents duplicate loading using loading flags

---

## 🔍 Search Logic

* User types in search field
* ViewModel switches DB source
* Room query filters anime by title

---

## 🧱 Database Structure

Tables:

### anime_table

Stores anime list data.

### anime_detail_table

Stores detailed anime information.

---

## 🧩 Dependency Injection

Dependency Injection ensures:

* Better separation of concerns
* Easier testing
* Cleaner architecture

Injected components:

* Database
* DAO
* Repository
* ViewModels

---

## ⚙️ Setup Instructions

1. Clone repository

```
git clone https://github.com/<username>/AnimeApp.git
```

2. Open in Android Studio

3. Sync Gradle

4. Run app on emulator or device

---

## 🧪 Future Improvements

Possible enhancements:

* Favorites / Watchlist
* Episode streaming integration
* Paging 3 library support
* Compose UI migration
* Unit testing
* UI tests
* Dark mode
* Offline first strategy
* Better error handling

---

## 📸 Screenshots 


```

and embed them.

---

## 📚 Learning Outcomes

This project demonstrates:

* MVVM architecture implementation
* Room database usage
* Retrofit networking
* Coroutines usage
* Pagination logic
* Search implementation
* Dependency injection basics
* Clean architecture layering

---

## 👩‍💻 Author

Developed by **Gurleen** as part of Android learning & portfolio work.

---

