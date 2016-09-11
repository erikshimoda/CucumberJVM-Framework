package com.testframeworkcucumberjvm.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features"
//		, plugin = { "pretty", "html:target/cucumber-html-report", "json:cucumber.json" }
		, monochrome = true
		, tags = { "@Web" }
		, glue = { "com.testframeworkcucumberjvm.web" })
public class RunGUIWebTest {

}
