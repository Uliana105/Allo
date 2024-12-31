# Selenium Project: Allo

## Overview
This project is built using Selenium to automate web browser interactions for the Allo web site.

---

## Prerequisites

### Software Requirements
- **Java Development Kit (JDK)**: Ensure JDK 11 or later is installed. Download from [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- **Integrated Development Environment (IDE)**: Use IntelliJ IDEA, Eclipse, or any preferred IDE.

---

## Installation and Setup

### 1. Clone the Repository
```bash
git clone https://github.com/Uliana105/Allo.git
```

### 2. Open the Project in Your IDE
- Import the project as a Maven project.

### 3. Configure Dependencies
Ensure the `pom.xml` file contains the following dependencies:
```xml
<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.27.0</version>
    </dependency>
</dependencies>
```
Run `mvn install` to download the dependencies.

### 4. Run Tests
- Ensure the test files are located in the `src/test/java` directory.
- Execute all tests using Maven:
  ```bash
  mvn clean test
  ```
- Execute single test using Maven:
  ```bash
  mvn clean test -Dtest=<testName>
  ```

---

## Project Structure
```
Allo/
|— src/
    |— main/java/
        |— <application_code>
    |— test/java/
        |— <test_code>
|— pom.xml
```
- **`main/java/`**: Contains the main application code.
- **`test/java/`**: Contains test cases.
- **`pom.xml`**: Maven configuration file.



