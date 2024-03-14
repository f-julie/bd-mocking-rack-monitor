# RackMonitor GILA PART C - You need a warrantee, no Exceptions!

|A	|Start time:	|
|---	|---	|
|(20 minutes) You need a warrantee, no Exceptions!	|	|

## Instructions

*Without looking at the test method*, answer the follow questions for the
`monitorRacks_withUnhealthyUnwarrantiedServer_throwsRackMonitorException` test.

1. Based on just the name of this test case, what method are we testing?
   * <div class="answer-space essay"/>
   
2. Based on just the test name what should be the expected outcome?
   * <div class="answer-space essay"/>
   
3. Based on just the name of this test case, what conditions should cause the
   expected outcome?
   * <div class="answer-space essay"/>
   
4. Take a look at the `RackMonitor` class and fill in the table below.
   A new entry should be added for each method you expect to be called by this
   test case on an object you decided to mock. Be sure to think about what
   lines of code may **not** be executed by this test case. We **don't** want
   to include those lines in this table.

    |Instance Variable Type	|Method being called	|Expected input	|Return type	|Throws exception in this case?	|Goal of this method	|
    |---	|---	|---	|---	|---	|---	|
    |	|	|	|	|	|	|
    |	|	|	|	|	|	|
    |	|	|	|	|	|	|
    |	|	|	|	|	|	|

1. For any methods you've identified as throwing exceptions in question 4,
   how are the exceptions handled by its caller?
   * <div class="answer-space essay"/>
   
2. Now, take a look at the
   `monitorRacks_withUnhealthyUnwarrantiedServer_throwsRackMonitorException`
   code. You will need to add implementation to the test method so that it uses
   mocking to duplicate the exception-al behavior you found in question 4. You
   can validate your test method passes by running the test class directly in
   IntelliJ or with the `mocking-classroom-rackmonitor-exceptions` RDE workflow.

   When you have successfully run your tests, please cross off your role below.

    * Recorder
    * Presenter
    * Manager
    * Reflector

1. Could you force a dependency method to throw an exception if you *didn't*
   use mocking?  What are some advantages and disadvantages of your method,
   compared to mocking?
   * <div class="answer-space essay"/>
   

### Extension

Where else could our dependencies throw an exception? Are we certain
`RackMonitor` handles those situations correctly? *Prove* that `Rackmonitor`
handles other exceptions from its dependencies by writing tests in
`RackMonitorExceptionTest` for each case.
