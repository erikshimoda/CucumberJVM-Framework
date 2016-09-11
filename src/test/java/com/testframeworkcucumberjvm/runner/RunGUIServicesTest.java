package com.testframeworkdemo.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;




//@CucumberOptions(features = "classpath:features"
//, tags = {"@testesFuncionais"}
//, monochrome = true
//, plugin = { "pretty", "html:target/cucumber-html-report", "json:cucumber.json" }
//, glue = "com.testframeworkdemo")
//public class RunGUIServicesTest extends AbstractTestNGCucumberTests {

//}

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features"
		, plugin = { "pretty", "html:target/cucumber-html-report", "json:cucumber.json" }
		, monochrome = true
		, tags = { "@servicos" } 
		, glue = { "com.testframeworkdemo" }
)

public class RunGUIServicesTest {

}