# README 

## Purpose
This repository is a consolidated repository for all Foundation IT modules.

## Pre-requisites

In order to be able to build all the modules, the following software have to be installed and available on the PATH environment variable :
* Java Development Kit (JDK) 11 (https://cdn.azul.com/zulu/bin/zulu11.41.23-ca-jdk11.0.8-win_x64.zip)
* Maven 3.6.3 https://ftp.halifax.rwth-aachen.de/apache/maven/maven-3/3.6.3/binaries/
* Eclipse : https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-ee-developers
* Eclipse feature plugin in the marketplace : https://marketplace.eclipse.org/content/cucumber-eclipse-plugin
* Eclipse maven in the marketplace if not installed
* Eclipse install new software Lombok: https://projectlombok.org/setup/eclipse


##### Launch tests

    mvn clean test -Dneodymium.url.host=HostOfYourChoice

HostOfYourChoice, you can use an IP or DNS

##### Test only one scenario

Add a tag in a feature file above one scenario, for instance @WIP
Add tags = "@Wip" in @CucumberOptions in RunAllFeaturesTest
Launch maven command mentioned above

##### Debug test in local and see driver actions

By default action in browser are not displayed to ensure that the resolution used is the good one in headless mode
This can be deactivated for debug purpose in config/browser.properties file: browserprofile.Chrome.headless = false

##### Consult report

For local development you can consult after mvn command launched foundationBddTests\target\cucumber-report.html with chrome browser

