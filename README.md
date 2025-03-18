# Flipkart Automation Framework
## Overview
A hybrid test automation framework for Flipkart.com using POM, TestNG, Cucumber, Keyword-Driven, and Data-Driven approaches.

## Setup
1. Install Java 11 and Maven.
2. Clone the repo: `git clone <url>`.
3. Run `mvn install` to download dependencies.

## Running Tests
- TestNG: `mvn test`
- Cucumber: Run `CucumberRunner.java`

## Structure
- `pages/`: POM classes
- `tests/`: TestNG and Cucumber tests
- `utils/`: Utilities
- `keywords/`: Keyword-Driven logic