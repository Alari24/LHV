LVH testing task solution

To run tests:
1) CucumberRunner.java file can be run
    * This runs the tests with the desired tags (Default is @Full)
    * This generates xml files for Allure report into folder allure-results which can be designated for example Bamboo to generate report
2) To run tests through terminal and generate Allure report locally then the following can be done
    * Write "gradlew test" to run tests with tags designated in CucumberRunner.java file
    * Write "gradlew allureReport" to generate report locally
        ** This report can be found under build -> reports -> allure-report -> index.html
3) Windows has been configured to have included Firefox, Chrome and Internet Explorer webdrivers in its PATH system variable
   * This can be checked from Environment Variables in Windows

For additional information you can contact:
Project contributor Alari Alev at alev.alari@gmail.com
