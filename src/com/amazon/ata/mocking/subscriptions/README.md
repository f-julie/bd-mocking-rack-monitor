# Mock me!

**Class Diagram**
- We have provided [a class diagram for this activity](subscriptionsCD.puml)

---

**Instructions**

We're going to demo some mocking following "I do, We do, You do." First your instructor will demo for everyone
how they think about mocks by updating the following test for the `EmailServiceClient` class to use mocks:
- `sendNewSubscriptionEmail_emailAddressExists_returnTrue`

You can run this test from IntelliJ.

Then we will write a test together as a class in the `SubscriptionLibrary` class:
- `cancelSubscription_subscriptionDoesNotExist_throwsInvalidInputException`

Finally, you will individually write an additional test in the `SubscriptionLibrary` class:
- `addMonthlySubscription_newSubscription_sendsNewSubscriptionEmail`

To ensure your tests are all passing you can either run them from IntelliJ.
