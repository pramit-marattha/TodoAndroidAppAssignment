![Underconstruction](https://i.ibb.co/RYK2Gt8/UC.png)

---

# TODO.


<p align="center">
  <img src="https://i.ibb.co/SQKHL27/todo.png">
</p>

---
[![Build Status](http://img.shields.io/travis/badges/badgerbadgerbadger.svg?style=flat-square)](https://travis-ci.org/badges/badgerbadgerbadger)
[![Coverage Status](http://img.shields.io/coveralls/badges/badgerbadgerbadger.svg?style=flat-square)][![Badges](http://img.shields.io/:badges-9/9-ff6799.svg?style=flat-square)](https://github.com/badges/badgerbadgerbadger)
---

# Development Stages
```bash
├── Master
│   ├── InitialStep
│   ├── Entity Branch
│   ├── DataAccessObject branch
│   ├── Database Branch
│   ├── TypeConverter Branch
│   ├── Thread runnable & executor Branch 
│   ├── PopulateList Branch
│   ├── DeleteList Branch
│   ├── UpdateList branch
│   ├── favicon.ico
│   ├── Live Data Branch
│   ├── MainActivityViewModel Branch
│   ├── AddEditTaskActivityViewModel Branch
│   ├── Repository Branch
│   ├── UserInterface Branch
│   ├── Updating The Icons Branch
│   ├── Updating the Task branch
│   ├── Undo Feature Branch
│   ├── SnackBarPopUPFeature after delete Branch
│   ├── New AppIcon Branch
│   ├── ...
│   ├── ...
│   ├── ...
│   └── ....
├── Final Phase(Apk Export)
```



# Full Stacked TODO app.

> .............................

> .............................

> .............................

> .............................

> .............................

---



## Features
<p align="center">
  <img src = "http://g.recordit.co/16FpSh626C.gif">
  </p>

---

## Usage 

---
## Documentation (Model–view–viewmodel architecture in a nutshell) 
Model–view–viewmodel (MVVM) is a software architectural pattern that facilitates the separation of the development of the graphical user interface (the view) be it via a markup language or GUI code from the development of the business logic or back-end logic (the model) so that the view is not dependent on any specific model platform. The view model of MVVM is a value converter,meaning the view model is responsible for exposing (converting) the data objects from the model in such a way that objects are easily managed and presented. In this respect, the view model is more model than view, and handles most if not all of the view's display logic.The view model may implement a mediator pattern, organizing access to the back-end logic around the set of use cases supported by the view.
#
![](https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/img/a7da8f5ea91bac52.png)

---

## Architecture of the App
The diagram explains the same basic architecture form as the diagram above, but in the context of an app. 
#
![](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/images/10-1-c-room-livedata-viewmodel/dg_app_architecture.png)

##

### Entity
The data for an app with a database centers around the data stored in the database. Of course, there can be other data from different sources, but for simplicity, this discussion ignores other data.Room models an SQLite database, and is implemented with an SQLite database as its backend. SQLite databases store their data in tables of rows and columns. Each row represents one entity (or record), and each column represents a value in that entity. 
#
![](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/images/10-1-c-room-livedata-viewmodel/dg_app_architecture_entity.png)

### Data Access Object
To access your app's data using the Room persistence library, you work with data access objects, or DAOs. A set of Dao objects (DAOs) forms the main component of a Room database. Each DAO includes methods that offer abstract access to your app's database.You annotate the DAO to specify SQL queries and associate them with method calls. The compiler checks the SQL for errors, then generates queries from the annotations. For common queries, the libraries provide convenience annotations, such as @Insert, @Delete, and @Update.
#
![](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/images/10-1-c-room-livedata-viewmodel/dg_app_architecture_dao.png)

### RoomDatabase
Room is a database layer on top of an SQLite database. Room takes care of mundane tasks that you used to handle with an SQLiteOpenHelper.
#
![](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/images/10-1-c-room-livedata-viewmodel/dg_app_architecture_roomo.png)

### ViewModel 
The ViewModel is a class whose role is to provide data to the UI and survive configuration changes. A ViewModel acts as a communication center between the Repository and the UI. You can also use a ViewModel to share data between fragments. The ViewModel is part of the lifecycle library.
#
![](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/images/10-1-c-room-livedata-viewmodel/dg_app_architecture_viewmodel.png)

### Repository
A Repository is a class that abstracts access to multiple data sources. The Repository is not part of the Architecture Components libraries, but is a suggested best practice for code separation and architecture. A Repository class handles data operations. It provides a clean API to the rest of the app for app data.A Repository is where you would put the code to manage query threads and use multiple backends, if appropriate. Once common use for a Repository is to implement the logic for deciding whether to fetch data from a network or use results cached in the database.
#
![](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/images/10-1-c-room-livedata-viewmodel/dg_app_architecture_repository.png)

### LiveData
When your app displays data or uses data in other ways, you usually want to take action when the data changes. This means your app has to observe the data so that when it changes, the app can react. Depending on how the data is stored, this can be tricky. Observing changes to data across multiple components of your app can create explicit, rigid dependency paths between the components. This makes testing and debugging, among other things, difficult.
#
![](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/images/10-1-c-room-livedata-viewmodel/dg_app_architecture_livedata.png)

### UI controller Activity Fragment
you can display all this interesting data to the user.Whenever the data changes, the onChanged() method of your observer is called.
#
![](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/images/10-1-c-room-livedata-viewmodel/dg_app_architecture_UI.png)
---



## FAQ

- **How do I do install this app?**
    - App is under development..

---
