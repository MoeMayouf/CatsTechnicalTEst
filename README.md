# CatsTechnicalTest

A sample app that shows Images of cats utilising ViewModel, RxJava and Hilt in Kotlin by using Clean Code Architecture 

## Communication between layers
1. UI calls method from ViewModel.
2. ViewModel executes Use case.
3. Use case gets data from Cats Repositories.
4. Repository returns data from a Data Source (Currently remote).

## Endpoint used 
Used https://thecatapi.com/v1/ along with some queries

## Improvements
* Modify endpoint to allow more user flexibility for request
* Implement instrumentation testing
