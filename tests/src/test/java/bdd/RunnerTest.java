package bdd;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/bdd/features",
        glue = "bdd.steps",
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true
)
public class RunnerTest {}
