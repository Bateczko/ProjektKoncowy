package Zadanie2;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/cucumber/Features/buy-hummingbird-printed-sweater.feature",
        plugin = {"pretty","html:out"})

public class BuyHummingbirdPrintedSweaterTest {
}
