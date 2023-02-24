
# Project

List in console Properties requested from API.



## Authors

- [@AlejandroAlessi](https://www.github.com/alejandroalessi)


## Documentation

NOTE: 
1- Keep in mind this was over engineered just to show skills.
2- No valid key is available at the console. In fact 1 unit test is was left failing intentionally because of that.


#### Design notes

1 - There are three main interfaces: Service, Printer, Executor. Executor uses Service and Printer implementations. Executor itself, being an interface, has one implementation. 

2 - Services are responsible for getting data from the API or wherever it is. Services support paging.

3 - Printers are responsible for printing

4 - Excutors orchestrate Service and Printers

5 - All three interfaces have their own package implementations. Each interface-implementation is in different java packages.

6 - For services it's provided and HttpClient implementation

7 - For printers it's provided a SimplePrinter that just prints using System.out

8 - For Executors it's provided ConcurrentNonBlockingExerciseExecutor  implementation that makes async calls to the service for each page. When the result is got fires a printing in a Printer and, concurrently, keeps iterating for getting more pages. 

In the ConcurrentNonBlockingExerciseExecutor, Service is executed one page at a time but Printers are fired in its own Excecutor (ThreadPool).

9 - Services exposes a CompletableFuture, nothing blocks till get(). Printers are threadsafe and run on its own threapool.

#### How to run

Unit Test: Test gradle tasks.
There are three tests classes (1 for deserialization, 1 for Http api, 1 for the Concurrent Executor)


App: via gradle taks or from command line execute class easyb.App

#### Configuration
/resources/app.properties is the properties files with BASE_URL and API_KEY

Note: There's no valid in the console so just unit tests were ran.

#### Dependencies
junit
jackson-databind

Http requests-response are implemented via java's sre HttpClient.
No logging, depency injection and mock libs are used because it wasn't considered necessary.

