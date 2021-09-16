### Mock Object Role
Either one or two people may play the role of the mock objects.
You'll execute the same code together, taking on the roles of whatever mocks are required.
Your partner will be the `ATALearningProcess` that you're testing.

Pull down the latest `ATAClassroomSnippets` revisions and examine the *test* in
`com.amazon.ata.mocking.metamocking.ActivityA`.

* Start executing the `@BeforeEach`

  * When you reach the `ATALearningProcess` constructor, you must rely on
    your partner playing the Unit Under Test Role to take the appropriate steps.
    Tell them to execute their constructor and pass them the memory references of
    their parameters so they can query you for mock responses later.
    Then wait for them to complete their initialization.
    
* Execute the test.
  * Skip over the `when()...thenReturn()` statements; you can fill those
    out on your worksheets as the Unit Under Test needs them. 

  * When you reach a `process.ask()` line, tell your partner playing the part
    of the `ATALearningProcess` to begin executing their `ask()` method with the
    'String "What should I *not* mock?"'.

  * When the `ATALearningProcess` partner asks for the result of a method that is listed on
    your worksheet, first verify that the parameters match exactly.
    * If you cannot find an exact match, respond with the return type's default value.
    * If the request exactly matches one of the methods on your worksheet,
      give them the corresponding result; make a tally mark next to the method to
      track how many times it was called.
      
* With your partner(s), execute the THEN section of the test.
