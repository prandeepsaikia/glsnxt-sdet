# GLSNXT SDET - API Testing Project

![Java](https://img.shields.io/badge/Java-21-blue)
![Maven](https://img.shields.io/badge/Maven-3.9.11-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen)
![TestNG](https://img.shields.io/badge/TestNG-7.11.0-brightgreen)
![Allure](https://img.shields.io/badge/Allure-2.29.1-red)
![RestAssured](https://img.shields.io/badge/RestAssured-5.5.6-lightgrey)
![WireMock](https://img.shields.io/badge/WireMock-3.13.1-lightblue)

This repository contains automated API tests for the **JSONPlaceholder API**. Tests cover functional, performance, and basic security aspects, with detailed reporting using **Allure**. **WireMock** is used for negative testing and simulating API failures.

---

## 📄 Test Documentation

<details>
<summary>Test Plan Outline</summary>

- **Scope & API Endpoints to Test:**  
  [`src/test/resources/docs/Test-Plan.pdf`](src/test/resources/docs/Test-Plan.pdf)

- **Key Validations:**  
  Status codes, response format, and other validations are listed here:  
  [`src/test/resources/docs/Test-Plan.pdf`](src/test/resources/docs/Test-Plan.pdf)
</details>

<details>
<summary>Test-Case Table</summary>

- 4-5 scenarios covering different HTTP methods can be found here:  
  [`src/test/resources/docs/Test-Cases.pdf`](src/test/resources/docs/Test-Cases.pdf)
</details>

---

## 🤖 AI Disclosure

The following AI tools were used to assist in creating test documentation and code snippets:

- ChatGPT
- Gemini

---

## ⚙️ Project Setup Instructions

1. **Install Dependencies**
   - Download Java 21 from [here](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html).
    Install the JDK and set the `JAVA_HOME` environment variable
   - Install maven from [here](https://maven.apache.org/download.cgi). Configure maven.
2. **Clone the Repository**
   ```bash
   git clone https://github.com/prandeepsaikia/glsnxt-sdet.git
   
## 🚀 How to Run the Tests

1. **Run All API Tests**
  ```bash
    mvn clean test -Dspring.profiles.active=qa
   ```
2. **Run Specific Test Groups**
   - Example: only negative tests
  ```bash
    mvn clean test -Dspring.profiles.active=qa -Dgroups=negative
  ```
3. **Generate & View Allure Reports**
    - This opens the test report in a browser.
  ```bash
    mvn allure:serve
  ```

## ⚙️ Continuous Integration (CI)

- **GitHub Actions:** This project uses GitHub Actions for continuous integration. The CI pipeline is triggered automatically on every push to the main branch.
- **Allure Report:** Allure reports are generated and uploaded to the GitHub Pages branch.
- **Allure Report URL:** https://prandeepsaikia.github.io/glsnxt-sdet/

## More Information
1. Wiremock mappings are located in `src/test/resources/mappings`.
2. Test data is generated using - Datafaker library https://github.com/datafaker-net/datafaker
3. Spring profiles used: **qa**
4. Rest Assured Config is located in `src/test/java/com/glsnxt/sdetTask/api/config/Config.java`
5. Test cases are located in `src/test/java/com/glsnxt/sdetTask/api/tests`

## Additional Information
1. **Wiremock** is used for negative testing and simulating API failures.
2. This repository is kept **public** so that GitHub pages can be used to host the Test reports.
3. Parallel execution of tests can be implemented.
4. This is a very basic API testing framework.
5. More advanced testing scenarios can be added.

