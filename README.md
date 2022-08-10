# TEST TASKS FOR XMCY

## Running tests

To run the tests with maven, enter the command in the terminal:

``` mvn clean test ```

To parameterize test run, enter the command:
```
mvn clean test -Dsuite=testng -Dbrowser=chrome -Dresolution=800x600
```
### Parameters:

* **-Dsuite=testng** - where "***testng***" is the name of the XML file to run


* **-Dbrowser=chrome** - where "***chrome***" is the browser in which the tests will open.

    The following parameters are possible:
  * chrome (default)
  * firefox
  * edge
  * opera
  * IE.

* **-Dresolution=1024x768** or **-Dresolution=800x600**  Default "max"

## Running Reports
To generate a report, enter the command:

``` mvn allure::serve ```
