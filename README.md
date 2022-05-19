OOSE Solutions
======
This project will have different solutions on different branches
* [WS1-Addressbook](https://github.com/shyam3001/oose-solutions/tree/ws1-addressbook) 
* [WS2-Strategy](https://github.com/shyam3001/oose-solutions/tree/ws2-strategy)
* [Mouse-in-a-Maze](https://github.com/shyam3001/oose-solutions/tree/mouse)
* [WS1-ImageViewer](https://github.com/shyam3001/oose-solutions/tree/ws1-imageviewer)
* [WS3-Decorator](https://github.com/shyam3001/oose-solutions/tree/ws3-decorator)
* [WS5-Packages](https://github.com/shyam3001/oose-solutions/tree/ws5-packages)
* [WS6-Observer](https://github.com/shyam3001/oose-solutions/tree/ws6-observer)
* [Assignment-1 (David Cooper)](https://github.com/shyam3001/oose-solutions/tree/a1-david)
* [WS7-Refactoring](https://github.com/shyam3001/oose-solutions/tree/ws7-refactoring)
* [L8-State](https://github.com/shyam3001/oose-solutions/tree/l8-state)
* [L8-Microwave](https://github.com/shyam3001/oose-solutions/tree/l8-microwave)
* [WS8-Airlock](https://github.com/shyam3001/oose-solutions/tree/ws8-airlock)
* [WS9-SpaceProbe](https://github.com/shyam3001/oose-solutions/tree/ws9-spaceprobe)

OOSE Starter Code
------

This is a Gradle project structure, so you can get started on the assignment without messing around with Gradle too much.

Basically: 

* Put all your source code in `src/main/java` (or in further subdirectories inside `src/main/java`)
  * The subdirectory structure should match the package declaration statements otherwise IDEs may complain!

* Type `./gradlew run` to run. 

* Type `./gradlew check` to verify PMD rules
  * On Windows, drop the `./` from start of course; i.e., `gradlew run` or `gradlew check`.

* Gradle currently considers `edu.curtin.app.App` to be the name (and package) of the main class. If you change this, you must also edit [build.gradle](https://github.com/shyam3001/oose-starter-code/blob/main/build.gradle), and change ```mainClass = 'edu.curtin.app.App'``` accordingly. 

Credits: [Dr David Cooper](https://bitbucket.org/cooperdja/)

Additional Notes
------

* This project structure has been made IDE friendly by moving `App.java` to `src/main/java/edu/curtin/app` to match its package statement

* The project also has an `.idea` folder and can be opened directly using JetBrains IntelliJ IDEA, with Gradle targets `clean`, `run`, `check` configured within the IDE project.
