package com.cesar.school.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.cesar.school.steps",
        plugin = { "pretty", "summary" },
        monochrome = true
)
public class RunCucumberTest {
}
