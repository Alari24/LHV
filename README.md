Data Squad Test Automation

The build will most likely fail when first cloned. The project uses Lombok and in IntelliJ Annotation processing must be enabled. To do that go File -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> Enable annotation processing. Apply settings. Project will now run tests without failing.

To run tests:
1) CucumberRunner.java file can be run
    * This runs the tests with the desired tags (Default is @Full). Currently used tags are: @SmokeTest, @Full
    * This generates xml files for Allure report into folder allure-results which can be designated for example Bamboo to generate report
2) To run tests through terminally and generate Allure report locally then the following can be done
    * Write "gradlew test" to run tests with tags designated in CucumberRunner.java file
    * Write "gradlew allureReport" to generate report locally
        ** This report can be found under build -> reports -> allure-report -> index.html

For additional information you can contact:
Project contributor Alari Alev at alari.alev@luminorgroup.com
Other Test Automation team members who are listed at https://confluence.luminorgroup.com/display/ETT/Members
