LVH testing task solution

To run tests:
1) CucumberRunner.java file can be run
    * This runs the tests with the desired tags (Default is @Full)
    * This generates xml files for Allure report into folder allure-results which can be designated for example Bamboo to generate report
2) To run tests through terminal and generate Allure report locally then the following can be done
    * Write "gradlew test" to run tests with tags designated in CucumberRunner.java file
        - When tests fail due to not recognizing special characters then a system environment variable for JAVA must be created for JAVA_TOOL_OPTIONS with value -Dfile.encoding=UTF8
    * Write "gradlew allureReport" to generate report locally
        - This report can be found under build -> reports -> allure-report -> index.html
3) Windows has been configured to have included Firefox, Chrome and Internet Explorer webdrivers in its PATH system variable
   * This can be checked from Environment Variables in Windows
        - Latest chromedriver: http://chromedriver.chromium.org/
        - Latest geckodriver: https://github.com/mozilla/geckodriver/releases
        - Latest iedriver: https://www.seleniumhq.org/download/  (Get the 32bit one)

For additional information you can contact:
Project contributor Alari Alev at alev.alari@gmail.com