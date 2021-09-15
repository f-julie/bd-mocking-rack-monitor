# RackMonitor Coding GILA PART A - Test Data? In My Service?

|A	|Start time:	|
|---	|---	|
|(30 minutes) Test Data? In My Service?	|	|

## Instructions

The `mocking-classroom-rackmonitor-incidents` workflow currently passes,
but if you look at the tests in `RackMonitorIncidentTest`, you'll see that it's
using a lot of pre-defined test data. 

Open the `RackMonitorIncidentTest` and answer these questions:

1. `RackMonitorIncidentTest` defines servers that will be unhealthy (health
   factor less than 0.8) and shaky (health factor less than 0.9). Since
   `RackMonitor` doesn't actually calculate the health factor on its own, some
   other code must be treating these servers specially to guarantee they always
   return an appropriate health factor. Which class does the special handling?
   * <div class="answer-space essay"/>
2. Data that exists solely to make a situation where a service will exhibit a
   known, consistent behavior is called "test data". Why might we need a
   service to exhibit a known, consistent behavior?
   * <div class="answer-space essay"/>
3. Why should we avoid test data in production servers?
   * <div class="answer-space essay"/>
4. Given the server IDs for the `unhealthyServer` and `noWarrantyServer`, how
   many test servers does `Rack` probably need to handle? 
   * <div class="answer-space essay"/>

    If we change `RackMonitorIncidentTest` to use mocks, our dependencies won't
    need to provide test data!

5.  Trace through the code in `RackMonitor` that's used by the
    `getIncidents_withOneUnhealthyServer_createsOneReplaceIncident` test method.
    Right now we're only focused on the code path used by this test (there may be
    lines of `RackMonitor` that we're not testing in this specific test method).
    You shouldn't need to look at any of the code in any other classes,
    particularly not `WarrantyClient` or `WingnutClient`; just make a reasonable
    guess about how they work by reading their Javadoc.

   For each method that `RackMonitor` calls on its dependencies, decide if it
   should be mocked. Classes we don't normally mock include: POJOs, core pieces
   of the Java library like `Collection`s, and side effect code that doesn't
   contribute to what our service returns. Use the table below to keep track of
   your decisions; we've filled in a few cells to get you started.

    |method	|Expected input	|Return type	|Mock it?	|
    |---	|---	|---	|---	|
    |Logger.info, Logger.warn	|String, objects	|void	|No	|
    |Rack.getHealth	|none	|Map<Server, Double>	|	|
    |Rack.getUnitForServer	|Server	|int	|	|
    |WarrantyClient.getWarrantyForServer	|	|	|	|
    |	|	|	|	|
    |	|	|	|	|
    |	|	|	|	|

    If we change `RackMonitorIncidentTest` to use mocks, we can consolidate all
    those test servers into a single variable. We can also get rid of the code that
    sets up any dependency that we mocked. 

1. Why don't we need code to initialize and configure our dependencies when we
   use mocks?
   * <div class="answer-space essay"/>
   
2. Are there any dependencies we can use the default behavior of the mock for?
   * <div class="answer-space essay"/>
   
3. Individually, update your `RackMonitorIncidentTest` class to use mocks
   (don't forget to initialize them!) and update the test methods to use the
   mocks. You can validate your test method passes by running the individual
   method directly in IntelliJ or with the
   `mocking-classroom-rackmonitor-incidents` RDE workflow. When you have
   successfully run your tests, please cross off your role below.

    * Recorder
    * Presenter
    * Manager
    * Reflector

1. Did you notice that using mocks made you set up something that you *didn't*
   need to set up when using test data? What was it?
    * <div class="answer-space essay"/>
    
2. Did mocking use *more* code or *less* code than test data?
    * <div class="answer-space essay"/>
    
3. Is the mocking code *more* readable or *less* readable? Which version more
   directly specifies what the test actually does better? 
    * <div class="answer-space essay"/>

### Extension

1. The `RackMonitor` is only supposed to request an intervention if this is the
   *first* time the server's health has dipped. Can you write a test that
   checks whether a duplicate server health incident is created when the same
   server reports a problem twice in a row?
   * <div class="answer-space essay"/>
2. Add your test case to `RackMonitorIncidentTest`. You can validate your test
   method passes by running the individual method directly in IntelliJ. If you
   get stuck, use your group to help! If you have questions your group cannot
   answer, please have your presenter raise their hand in Zoom to get the
   attention of an Instructor.

    * Recorder
    * Presenter
    * Manager
    * Reflector
