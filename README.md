
# TODO.
---

<p align="center">
  <img src="https://i.ibb.co/SQKHL27/todo.png">
</p>

---
[![Build Status](http://img.shields.io/travis/badges/badgerbadgerbadger.svg?style=flat-square)](https://travis-ci.org/badges/badgerbadgerbadger)
![Coverage Status](http://img.shields.io/coveralls/badges/badgerbadgerbadger.svg?style=flat-square)
![Badges](http://img.shields.io/:badges-9/9-ff6799.svg?style=flat-square)
---


## Features
Register          |  Sign-IN                      | Adding ToooDooo              |  Adding ToDo List
:----------------------------:|:--------------------------------------:|:----------------------:|:-----------------
<img src = "http://g.recordit.co/FA4dI80KXq.gif" width="200" height="360">  |  <img src = "http://g.recordit.co/2BUesdd1wI.gif" width="200" height="360">        |  <img src = "http://g.recordit.co/NWyseEnKvW.gif" width="200" height="360">  | <img src = "http://g.recordit.co/9BEfyIXARp.gif" width="200" height="360">
#
SpeechToText          |  Adding Name Description                   | Marking Complete/Incomplete              |  Undo Todo
:----------------------------:|:--------------------------------------:|:----------------------:|:-----------------
 <img src = "http://g.recordit.co/LSOThTsE2i.gif" width="200" height="360"> |   <img src = "http://g.recordit.co/eo7po84uIf.gif" width="200" height="360">        | <img src = "http://g.recordit.co/0VtmQQ2dEI.gif" width="200" height="360">   | <img src = "http://g.recordit.co/Tlhc9nYTSb.gif" width="200" height="360">



---

## Downloads:

| Platform | Architecture    | Version | Link                                                                                                                         |
| -------- | ------- | ------- | ---------------------------------------------------------------------------------------------------------------------------- |
| Android  | x64     | under construction   | [Download]()  |

---

## About Installation (Installing and using the App)
#### ***1 Register/ Sign Up:***
Fill the Required Information and Sign Up.
#### ***2 Login:***
After Signing Up Login with the same credentials.
##### ***3 Todo List:***
Click on the floating Add icon and create the Todo List
#### ***4 Todo Item:*** 
After creating a Todo List ,Now click on the list and create your Todo Items.
#### ***5 Editing the Todo Items:***
Click on the Todo Item and edit it.
#### ***6 Deleting the todo items and list:***.
You can delete Todolist or a todoItem.
#### ***7 Sorting or filtering out:***
Sort the completed and incomplete Todo items.

---

### Design Architecture 
# `MODEL`
* `adapter`  consists of recyclerviews adapters.<br>
-`TaskListsAdapter` <br>
-`TaskItemsAdapter` <br>
* `InterfacePrompt` consists of dialog prompts popup & Swipe to delete<br>
-`AddNewTodoListDialog`<br>
-`SwipeLeftDelete`<br>
-`TodoItemsCallBack`<br>
-`TodoListCallbacks`<br>
-`SignoutDialog`<br>
* `Database` consists of dao(class for room database) ,UserAuthentication,db..<br>
-`DataAccessObject`<br>
-`AppDatabase`<br>
-`Dateconvert`<br>
-`EntireUserDatabase`<br>
-`RegisterUserAuthentication`<br>
-`TodoItems`<br>
-`TodoLists`<br>
-`TodoListsAndItems`<br>

# `VIEW`
* `Activity`  consists of main activity<br>
-`MainActivity` <br>
* `Fragments` consists of all fragments<br>
-`LoginFragments`<br>
-`RegisterFragments`<br>
-`SplashFragments`<br>
-`TodoListFormFragments`<br>
-`TodoListFragments`<br>

# `ViewModel`
* `authHandleHomeViewModel`  <br>
* `ParentExtendHomeViewModel` <br>
* `SignInViewModel` <br>
* `SignInviewModel` <br>
*  `SignUpViewModel` <br>
*  `TodoListViewModel`<br>
*  `TodoItemViewModel`<br>

---

# Development Stages
```bash
├── Master
|
|---->|─ InitialStep Branch
│     ├── Entity Branch
│     ├── DataAccessObject branch
│     ├── Database Branch
│     ├── TypeConverter Branch
│     ├── Thread runnable & executor Branch 
│     ├── PopulateList Branch
│     ├── DeleteList Branch
│     ├── UpdateList branch
│     ├── Live Data Branch
│     ├── MainActivityViewModel Branch
│     ├── AddEditTaskActivityViewModel Branch
│     ├── Repository Branch
│     ├── UserInterface Branch
│     ├── Updating The Icons Branch
│     ├── Updating the Task branch
│     ├── Undo Feature Branch
│     ├── SnackBarPopUPFeature after delete Branch
│     ├── New AppIcon Branch
│     ├── CustomDate Feature Brach
│     ├── SpeechToText Branch
│     ├── ConvertActivityToFragment Branch    
│     ├── Login/SignUp-toSaveTODO branch       
│     ├── RefinedUserInterface Branch
│     ├── NavigationComponent-viewPager Branch        
│<----├── WrappingInPackage-&-Apk-Export Branch        
│   
├── Final Phase(Apk Export)                       <-------- Current progress
```

---

<p align="center">
<img src="https://user-images.githubusercontent.com/37651620/82111751-fc164e00-9766-11ea-9dff-8747f423750c.png" alt="" width="700" height="700">
</p>

---

# `References`
### `Resources used to create this App`
#### List of all modules , libraries & Repo-references to create this App:

1. Room persistance library: [https://developer.android.com/topic/libraries/architecture/room]
2. Paint class holds the style and color information about how to draw geometries, text and bitmaps.: [https://developer.android.com/reference/android/graphics/Paint]
3. LayoutInflater: It Instantiates a layout XML file into its corresponding View objects[https://developer.android.com/reference/android/view/LayoutInflater]
4. RxAndroid: Reactive Extensions for Android : [https://github.com/ReactiveX/RxAndroid]
5. Appache commons hashcodebuilder: [https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/builder/HashCodeBuilder.html]
6. Data Binding: It is a is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically. : [https://developer.android.com/topic/libraries/data-binding]
7. Validation: [https://github.com/thyrlian/AwesomeValidation]
8. Handling Lifecycles with Lifecycle-Aware Components: [https://developer.android.com/topic/libraries/architecture/lifecycle]
9. Navigation Components: [https://developer.android.com/guide/navigation/navigation-getting-started]
10. Material Components: [https://material.io/develop/android/docs/getting-started/]
11. Material-components-android: [https://github.com/material-components/material-components-android]
12. Referencing complex data using Room:[https://developer.android.com/training/data-storage/room/referencing-data]
13. TypeConverter: [https://developer.android.com/reference/android/arch/persistence/room/TypeConverter]
14. android.widget:[https://developer.android.com/reference/android/widget/package-summary]
15. Uri :[https://developer.android.com/reference/android/net/Uri]
16. Creating Task Room: [https://github.com/ebbi/TaskRoom]
17. Creating Task Fragment:[https://github.com/ebbi/TaskFragment]
18. TodoViewModel:[https://github.com/ebbi/TodoViewModel]
19. Todo app Model View ViewModel architecture:[https://github.com/ebbi/TodoMVVM]

---

# Detailed Documented process of each Branch
InitialPhase Branch                                |  Entity Branch
:-------------------------------------------------:|:-------------------------------------------------:
![](http://g.recordit.co/oz8cX3el2G.gif)           |  ![](http://g.recordit.co/JQvMR5cJbj.gif)

DataAcessObject Branch                             |  Database Branch
:-------------------------------------------------:|:-------------------------------------------------:
![](http://g.recordit.co/pjNmkW9T0q.gif)           |  ![](http://g.recordit.co/HD0k1JbdKd.gif)

TypeConverter Branch                               |  ThreadRunnableExectors Branch
:-------------------------------------------------:|:-------------------------------------------------:
![](http://g.recordit.co/5IAQC2JeJr.gif)           |  ![](http://g.recordit.co/21sIloC9N2.gif)


PopulatingList Branch                              |  DeletingTask Branch
:-------------------------------------------------:|:-------------------------------------------------:
![](http://g.recordit.co/4hB24sPSYN.gif)           |  ![](https://user-images.githubusercontent.com/37651620/82155625-fb300a00-9895-11ea-891c-6aac9f21f03e.gif)

UpdatingTask Branch                                |  LiveData Branch
:-------------------------------------------------:|:-------------------------------------------------:
![](https://user-images.githubusercontent.com/37651620/82155701-8f9a6c80-9896-11ea-8c1e-a0c8454cc8d8.gif)           |  ![](http://g.recordit.co/LqSU525J4J.gif)

MainActivityViewModel Branch                       | AddEditTaskActivityViewModel Branch  
:-------------------------------------------------:|:-------------------------------------------------:
![](https://user-images.githubusercontent.com/37651620/82155735-bfe20b00-9896-11ea-82eb-c62bda5bf779.gif)           |  ![](https://user-images.githubusercontent.com/37651620/82155763-e0aa6080-9896-11ea-8939-2996e3f3dec9.gif)

Repository Branch                                  |  DeletePopUp Branch 
:-------------------------------------------------:|:-------------------------------------------------:
![](https://user-images.githubusercontent.com/37651620/82156059-c1acce00-9898-11ea-8be8-55d18a64fc93.gif)           |  ![](http://g.recordit.co/idA7vDJzaI.gif)

SppechToText Branch                                | CustomDate Branch
:-------------------------------------------------:|:-------------------------------------------------:
![XK8laCyYBo](https://user-images.githubusercontent.com/37651620/82156096-e1dc8d00-9898-11ea-85de-2bdef7f144b0.gif)         |  ![9Wte9nXKLN](https://user-images.githubusercontent.com/37651620/82156420-ae9afd80-989a-11ea-88f8-c15c399b1722.gif)


OraganizingFilesByPackage Branch                   | Activity to Fragment & Nav. component
:-------------------------------------------------:|---------------------------------------------------:
![](https://user-images.githubusercontent.com/37651620/82150888-59022900-9879-11ea-91e7-41ea0ee6e4f3.gif) |![activityTofragment](https://user-images.githubusercontent.com/37651620/83865108-6bb7a180-a745-11ea-9965-d1ee35b68cad.gif)

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
