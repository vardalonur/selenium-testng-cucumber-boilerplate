## Overview
This project is a Java-based web automation framework using Selenium WebDriver and Cucumber for BDD (Behavior Driven Development). The project is built using Maven.

## Project Structure
- `src/main/java/pages`: Contains page object classes for different web pages.
- `src/test/java/stepDefinitions`: Contains step definition classes for Cucumber.
- `src/test/resources/features`: Contains feature files written in Gherkin syntax.
- `src/test/resources/features/predefined.feature`: Contains predefined steps grouped and explained for easy reference.

## Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- IntelliJ IDEA or any other preferred IDE

## Setup
1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/your-repo-name.git
    ```
2. Navigate to the project directory:
    ```sh
    cd your-repo-name
    ```
3. Install the dependencies:
    ```sh
    mvn clean install
    ```

## Running Tests
To run the tests, use the following command:
```sh
mvn test
```

## Features
- **Login**: Tests for successful and unsuccessful login scenarios.
- **Browser Navigation**: Tests for basic browser actions like navigating to a URL, refreshing the page, and switching tabs.
- **Element Actions**: Tests for clicking elements, typing text, and verifying element visibility.
- **Dropdowns and Checkboxes**: Tests for interacting with dropdown menus and checkboxes.
- **File Upload**: Tests for uploading files.
- **JavaScript Executions**: Tests for executing JavaScript commands.
- **IFrame and Modal Windows**: Tests for switching to iframes and handling modal windows.

## Predefined Steps
This project includes a set of predefined steps that are grouped and explained in the `predefined.feature` file. These steps cover a wide range of common actions such as browser navigation, element interactions, text actions, visibility checks, dropdown and checkbox interactions, action class methods, file uploads, wait methods, JavaScript executions, iframe and modal window handling, and others.

### Why Predefined Steps? 
Predefined steps provide a reusable and standardized way to write test scenarios. They help in maintaining consistency across different test cases and make it easier for new contributors to understand and use the framework.

### How to Use Predefined Steps?
You can refer to the `predefined.feature` file to see the available steps and their usage. These steps can be directly used in your feature files to create new test scenarios without writing additional step definitions.

## Contributing
We encourage you to fork this repository and contribute to its development. Here are the steps to contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Open a pull request.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
