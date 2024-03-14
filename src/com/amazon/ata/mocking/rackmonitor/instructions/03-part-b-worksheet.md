## RackMonitor Coding GILA PART B - Trust but Verify

|A	|Start time:	|
|---	|---	|
|(15 minutes) Trust but Verify	|	|

## Instructions 

Let's apply the same thinking from Part A to the tests in
`RackMonitorIncidentTest`. This class is already set up to use
mocking.

1. Run the `mocking-classroom-rackmonitor-dependencies` workflow. One of the
   tests passes! Why?
   * <div class="answer-space essay"/>
   
2. Does the order we call `when` have to match the order that the
   unit-under-test calls its methods?
   * <div class="answer-space essay"/>
   
3. What happens if we call `when` and set up a mock for a call that will never
   be used?
   * <div class="answer-space essay"/>
   
4. Trace through the `monitorRacks_withOneUnhealthyServer_replaceServer` method and fill in the table below. We already know from Part A what classes will be mocked. A new entry should be added for each method being called by this test case on an object you decided to mock. Be sure to think about what lines of code may **not** be executed by this test case. We **don't** want to include those lines in this table.

    |Method being called	|Expected input	|Return type	|Goal of this method	|
    |---	|---	|---	|---	|
    |Rack.getHealth()	|none	|Map<Server, Double>	|Find health of all servers in rack	|
    |	|	|	|	|
    |	|	|	|	|
    |	|	|	|	|
    
1. Which of the methods in question 4 do we want to verify?
   * <div class="answer-space essay"/>
   
2. Are there any mocks that we want to verify that we never interacted with?
   * <div class="answer-space essay"/>
   
3. Individually update your `monitorRacks_withOneUnhealthyServer_replaceServer`
   test method to verify that the correct dependencies were called. Then validate
   your `monitorRacks_withOneShakyServer_inspectsServer` and
   `monitorRacks_withOneHealthyServer_makesNoCalls` test methods run as expected.
   You can run the test class directly in IntelliJ or with the
   `mocking-classroom-rackmonitor-dependencies` RDE workflow. When you have
   successfully run your tests, please check the box next to your role below.

    * Recorder
    * Presenter
    * Manager
    * Reflector

### Extension

If you have extra time, feel free to explore these extensions or an earlier
extension. These are not required; they may provide you with extra, optional
information or extra practice.

#### Extension 1: Additional Optional Feature

There's another method in this class:
`monitorRacks_withMixedServers_makesMultipleCalls`.  Notice that it sets up the
`warrantyClient` mock to always return the null `Warranty`, no matter what
server gets passed to it. That's because the Mockito method `any()` matches any
`Object`.  Methods like this are called "Matchers".

You can use `any()` in `verify()` methods, too. It only matches `Object`s, but
any non-primitive in Java is an `Object`, so it works for almost anything!
There are similar methods for the primitives, like `anyInt()` and `anyFloat()`.

`verify()` can also take a second argument, `times()`, which indicates how many
times the method should have been called.

With these matchers and a second argument to `verify()`, you can check that a
dependency's method was called any expected number of times. Add a `@Test`
annotation to `monitorRacks_withMixedServers_makesMultipleCalls` and make it
pass!

#### Extension 2: Additional Practice

In Part A, we also made some calls to the service clients. We verified them
indirectly, because a HealthIncident wouldn't be created unless the request to
the client was successful. Indirect verification is fine, but direct
verification is better. 

We also couldn't tell whether a dependency had **not** been called. But with
mocking, we can!

Go back to the `RackMonitorIncidentTest` and update its test methods to verify
that dependencies are called exactly as expected.

