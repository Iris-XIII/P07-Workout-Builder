///////////////////////////////////////////////////////////////////////////////
// Title: Course Enrollment Program
// Course: CS 300 Fall 2023
//
// Author: Iris Xu
// Email: jxu595@wisc.edu
// Lecturer: Mark Mansi

///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//

/**
 * This class implement the tester for WorkoutBuilder class.
 * 
 * @author Iris Xu
 */

import java.util.NoSuchElementException;

public class WorkoutBuilderTester {

  // checks for the correctness of the WorkoutBuilder.clear() method
  public static boolean testClear() {
    Exercise.resetIDNumbers();

    // create new list for testing
    WorkoutBuilder list = new WorkoutBuilder();

    // add PRIMARY: exercise 1 (1)
    list.add(new Exercise(WorkoutType.PRIMARY, "exercise 1"));
    // add WARMUP: T/F questions (2)
    list.add(new Exercise(WorkoutType.WARMUP, "T/F questions"));
    // add COOLDOWN: conclusion 1 (3)
    list.add(new Exercise(WorkoutType.COOLDOWN, "conclusion 1"));
    // add COOLDOWN: conclusion 2 (4)
    list.add(new Exercise(WorkoutType.COOLDOWN, "conclusion 2"));
    // add PRIMARY: exercise 2 (5)
    list.add(new Exercise(WorkoutType.PRIMARY, "exercise 2"));

    list.clear();// call the clear method
    // check whether the method clear all the thing
    if (list.size() == 0 && list.getCooldownCount() == 0 && list.getPrimaryCount() == 0
        && list.getWarmupCount() == 0 && list.isEmpty())
      return true;
    return false;
  }

  // checks for the correctness of the WorkoutBuilder.add() method
  public static boolean testAddExercises() {
    Exercise.resetIDNumbers();

    // create new list for testing
    WorkoutBuilder list = new WorkoutBuilder();

    // add PRIMARY: exercise 1 (1)
    list.add(new Exercise(WorkoutType.PRIMARY, "exercise 1"));
    // add WARMUP: T/F questions (2)
    list.add(new Exercise(WorkoutType.WARMUP, "T/F questions"));
    // add COOLDOWN: conclusion 1 (3)
    list.add(new Exercise(WorkoutType.COOLDOWN, "conclusion 1"));
    // add COOLDOWN: conclusion 2 (4)
    list.add(new Exercise(WorkoutType.COOLDOWN, "conclusion 2"));
    // add PRIMARY: exercise 2 (5)
    list.add(new Exercise(WorkoutType.PRIMARY, "exercise 2"));

    boolean check1 = false;
    boolean check2 = false;

    // case1: counter counts the wrong number
    if (list.getPrimaryCount() == 2 && list.getCooldownCount() == 2 && list.getWarmupCount() == 1
        && list.size() == 5) {
      check1 = true;
    } else {
      System.out.println("wrong counter");
    }

    // case2: exercise point to the wrong place
    String tester =
        "WARMUP: T/F questions (2) -> PRIMARY: exercise 2 (5) -> PRIMARY: exercise 1 (1) -> COOLDOWN"
            + ": conclusion 1 (3) -> COOLDOWN: conclusion 2 (4) -> END"; // expected
                                                                         // list
    System.out.println("Run:      " + list.toString());
    System.out.println("Expected: " + tester);

    if (list.toString().equals(tester)) {
      check2 = true;
    } else {
      System.out.println("list order/format is incorrect");
    }

    return check1 && check2;
  }

  // checks for the correctness of BOTH of the WorkoutBuilder.removeExercise() methods
  public static boolean testRemoveExercises() {
    Exercise.resetIDNumbers();
    
    // create new list for testing
    WorkoutBuilder list = new WorkoutBuilder();
    Exercise e1 = new Exercise(WorkoutType.PRIMARY, "exercise 1");
    Exercise e2 = new Exercise(WorkoutType.WARMUP, "T/F questions");
    Exercise e3 = new Exercise(WorkoutType.COOLDOWN, "conclusion 1");
    Exercise e4 = new Exercise(WorkoutType.COOLDOWN, "conclusion 2");
    Exercise e5 = new Exercise(WorkoutType.PRIMARY, "exercise 2");
   
   
    //add PRIMARY: exercise 1 (1)
    list.add(e1);
    // add WARMUP: T/F questions (2)
    list.add(e2);
    // add COOLDOWN: conclusion 1 (3)
    list.add(e3);
    // add COOLDOWN: conclusion 2 (4)
    list.add(e4);
    // add PRIMARY: exercise 2 (5)
    list.add(e5);
   
    boolean check1 = false;
    boolean check4 = false;
   
    // case1: removeExercise(int exerciseID)
    // remove WARMUP: T/F questions (2)
    if (list.removeExercise(2).equals(e2) && list.size() == 4 && list.getWarmupCount() == 0 &&
    list.getPrimaryCount() == 2 && list.getCooldownCount() ==2) {
    check1 = true;
    System.out.println(list.toString());
    } else {
    System.out.println(list.toString());
    System.out.println("1Wrong, should remove " + e2.toString());
    }
    
    // case2 removeExercise(WorkoutType type)
    // remove COOLDOWN: conclusion 2 (4)
    if (list.removeExercise(WorkoutType.COOLDOWN).equals(e4) && list.size() == 3 &&
    list.getCooldownCount() == 1 && list.getPrimaryCount() == 2 && list.getWarmupCount() == 0) {
    check4 = true;
    System.out.println(list.toString());
    } else {
    System.out.println(list.toString());
    System.out.println("4Wrong, should remove " + e4.toString());
    }
   
    // return true if each is correctly removed
    return check1 && check4;
    }

  // checks for the correctness of the WorkoutBuilder.get() method
  public static boolean testGetExercises() {
    Exercise.resetIDNumbers();

    // create new list for testing
    WorkoutBuilder list = new WorkoutBuilder();

    Exercise e1 = new Exercise(WorkoutType.PRIMARY, "exercise 1");
    Exercise e2 = new Exercise(WorkoutType.WARMUP, "T/F questions");
    Exercise e3 = new Exercise(WorkoutType.COOLDOWN, "conclusion 1");
    Exercise e4 = new Exercise(WorkoutType.COOLDOWN, "conclusion 2");
    Exercise e5 = new Exercise(WorkoutType.PRIMARY, "exercise 2");


    // add PRIMARY: exercise 1 (1)
    list.add(e1);
    // add WARMUP: T/F questions (2)
    list.add(e2);
    // add COOLDOWN: conclusion 1 (3)
    list.add(e3);
    // add COOLDOWN: conclusion 2 (4)
    list.add(e4);
    // add PRIMARY: exercise 2 (5)
    list.add(e5);

    if (list.get(1).equals(e5))
      return true;

    System.out.println("Get: " + list.get(1).toString());
    System.out.println("Wrong get");

    return false;
  }

  // a test suite method to run all your test methods
  public static boolean runAllTests() {
    Exercise.resetIDNumbers();
    boolean clear = testClear(), add = testAddExercises(), remove = testRemoveExercises(),
        get = testGetExercises();

    System.out.println("test clear: " + (clear ? "pass" : "FAIL"));
    System.out.println("test add: " + (add ? "pass" : "FAIL"));
    System.out.println("test remove: " + (remove ? "pass" : "FAIL"));
    System.out.println("test get: " + (get ? "pass" : "FAIL"));

    // TODO: add calls to any other test methods you write
    Exercise.resetIDNumbers();
    // return true if and only if all test are passed
    return clear && add && remove && get; // TODO: replace this with the correct value
  }

  public static void main(String[] args) {

    runAllTests();
    demo();
  }

  /**
   * Helper method to display the size and the count of different boxes stored in a list of boxes
   * 
   * @param list a reference to an InventoryList object
   * @throws NullPointerException if list is null
   */
  private static void displaySizeCounts(WorkoutBuilder list) {
    System.out.println(
        "  Size: " + list.size() + ", warmupCount: " + list.getWarmupCount() + ", primaryCount: "
            + list.getPrimaryCount() + ", cooldownCount: " + list.getCooldownCount());
  }

  /**
   * Demo method showing how to use the implemented classes in P07 Inventory Storage System
   * 
   * @param args input arguments
   */
  public static void demo() {
    // Create a new empty WorkoutBuilder object
    WorkoutBuilder list = new WorkoutBuilder();
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // Add a primary exercise to an empty list
    list.add(new Exercise(WorkoutType.PRIMARY, "5k run")); // adds PRIMARY: 5k run (1)
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.WARMUP, "stretch")); // adds WARMUP: stretch (2) at the head
                                                           // of the list
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.PRIMARY, "bench press")); // adds PRIMARY: bench press (3)
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.WARMUP, "upright row")); // adds WARMUP: upright row (4) at
                                                               // the head of the list
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.WARMUP, "db bench")); // adds WARMUP: db bench (5) at the head
                                                            // of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // Add more exercises to list and display its contents
    list.add(new Exercise(WorkoutType.COOLDOWN, "stretch")); // adds COOLDOWN: stretch (6) at the
                                                             // end of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.COOLDOWN, "sit-ups")); // adds COOLDOWN: sit-ups (7) at the
                                                             // end of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: sit-ups (7) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.PRIMARY, "deadlift")); // adds PRIMARY: deadlift (8)
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: stretch (6) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.WARMUP); // removes WARMUP: db bench (5)
    System.out.println(list); // prints list's content
    list.removeExercise(3); // removes PRIMARY: bench press (3) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    try {
      list.removeExercise(25); // tries to remove box #25
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // remove all warm-ups
    while (list.getWarmupCount() != 0) {
      list.removeExercise(WorkoutType.WARMUP);
    }
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(1); // removes PRIMARY: 5k run (1) from the list -> empty list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.COOLDOWN, "walk")); // adds COOLDOWN: walk (9) to the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(8); // removes PRIMARY: deadlift (8) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: walk (9) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.WARMUP, "pull-up")); // adds WARMUP: pull-up (10) to the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(10); // removes WARMUP: pull-up (10) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
  }

}
