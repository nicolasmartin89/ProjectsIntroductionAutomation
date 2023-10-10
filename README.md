# ProjectsIntroductionAutomation
QA Automation Globant Course Projects

Practice

Create a Java – Maven application for banking, in which a bank contains multiple user accounts. Each account has a user and a password to log in and a balance, which should be $0 at the creation of the account.

The application should have the following features:

    The app should start with a login and there should be an admin user (it may be hard-coded) that can add new user accounts (but not admin accounts)

    The user accounts should be able to deposit, withdraw, or transfer money to another account.

    When withdrawing or transferring money, the application should verify if the respective account has enough funds to make the transaction.

    Each transaction, successful or failed, should print a message to the user indicating the result.

    The interactions with the user should be done via command line.

    Use Lombok dependency for managing getters and setters

    Use JUnit dependency to develop unit tests for the withdrawing, transferring and depositing methods

Lastly, configure the project to execute the unit tests right after the building stage.
