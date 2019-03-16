import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"stepdefinitions", "config"},
        features = {"src/test/resources/features"},
        plugin = {"pretty", "html:cucumber-reports", "io.qameta.allure.cucumber3jvm.AllureCucumber3Jvm"},
        tags = {"@Full"}
)
public class CucumberRunner {
}