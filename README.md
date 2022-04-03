# Shopping Cart API

## How to run via command line

1. Run the below command to start the app

    `gradlew :shopping-cart-api:bootRun -Pprofile=build`

## How to start via Eclipse Import:

1. Run the below command on the root folder: This helps the generated the files needd for the annotation processing by eclipse.

    `gradlew eclipseJdtApt eclipseFactorypath eclipseJdt`

2. Now Import the application into the eclipse as Gradle Project.

3. Run the shooping cart api, main class with profile as H2.

Sample Postman json file is available for testing the application.