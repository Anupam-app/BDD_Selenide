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

## Set timezone properties

Launch this script in the CIP: src\test\resources\executablescripts\common\setTimezoneProperties.ps1
It will generate an offset-timezone.properties file just next to the ps1 file
Replace the file in config/offset-timezone.properties with the new file

##### Launch tests

    mvn clean test -Dneodymium.url.host=HostOfYourChoice -DtestFailureIgnoreProp=true

HostOfYourChoice, you can use an IP or DNS
testFailureIgnoreProp, true if you want to run all tests and have a report or false (by default) to make the build failed if tests are red

##### Test only one scenario

Add a tag in a feature file above one scenario, for instance @WIP
Add tags = "@Wip" in @CucumberOptions in RunAllFeaturesTest
Launch maven command mentioned above

##### Debug test in local and see driver actions

By default action in browser are not displayed to ensure that the resolution used is the good one in headless mode
This can be deactivated for debug purpose in config/browser.properties file: browserprofile.Chrome.headless = false

##### Jenkins Parameters

* CIPHostName : hostname to launch the bdd tests
* EmailTo : email to send the test report
* TargetResourcesPath : folder on the CIP where to store the scripts
* CredentialsId : credentials used to connect to the CIP with remote session
* PDP : PDP used
* IpTargetClient : ip of the client using the application to pretend as local user
* Mode : only launch the tag chosen in mode, example: SMOKE, it adds the PDP condition automatically (optional)
* PrepareEnvironmentOnly : only launch dataset insertion and prepare the env for tests automation but do not launch the tests (optional)

##### Cucumber report

For local development you can consult after mvn command launched foundationBddTests\target\cucumber-report.html with chrome browser

##### Allure report

Allure report template is present on stlbiopdv01bp01.global.sial.com server in D:\Jenkins\email-templates\bddtests-allure-report.groovy file