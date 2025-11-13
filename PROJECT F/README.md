# Project F

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.

## How to run [Android Studio]
- Find 'core' folder inside Project F folder.
- Find 'src' folder.
- Open 'main' and 'java'.
- Inside java folder is 'gameF'. All related game source code lives here.
- Open the IDE and select 'Open' to access 'Project F' folder.
- In order to run the build in the IDE as a desktop application you have to find 'Edit Configurations' located just at the top before the menu of the IDE.
- Inside the window find the plus sign (+) next to the minus sign (-) and select 'Application'.
- Name it whatever you want. And under 'Build and run' section, you can find the second drop-down box, next to the first one, and then select 'PROJECT_F.lwjgl3.main'.
- Choose main class as 'DesktopLauncher'.
- And the working directory in the folder named 'assets' inside the Project F folder.
- Check out Yobowargames Youtube channel tutorial if you're lost (https://youtu.be/UwbfmAtytHc?si=OwsFaeXPS1QGgwqW). 
