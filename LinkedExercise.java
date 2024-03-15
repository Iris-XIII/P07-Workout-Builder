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
 * This class models a node for use in a singly-linked list. This node can ONLY contain elements of
 * type Exercise.
 * 
 * @author Iris Xu
 */
public class LinkedExercise {

  // The Exercise contained in this linked node, which cannot be replaced after this node is created
  private Exercise exercise;

  // The next linked node in this list
  private LinkedExercise next;

  /**
   * Creates a new node containing the provided exercise data with no following node
   * 
   * @param data - data of exercise
   */
  public LinkedExercise(Exercise data) {
    exercise = data;
    next = null; // set next to null if no following node
  }

  /**
   * Creates a new node containing the provided exercise data and next node
   * 
   * @param data - data of exercise
   * @param next - reference to the next node
   */
  public LinkedExercise(Exercise data, LinkedExercise next) {
    exercise = data;
    this.next = next;
  }

  /**
   * Accesses the exercise stored in this linked node
   * 
   * @return exercise
   */
  public Exercise getExercise() {
    return exercise;
  }

  /**
   * Accesses the next linked node in the list, which may be null
   * 
   * @return next
   */
  public LinkedExercise getNext() {
    return next;
  }

  /**
   * Changes the node which follows this one to be the provided value, which may be null
   * 
   * @param node - the new node that follows
   */
  public void setNext(LinkedExercise node) {
    next = node;
  }

  /**
   * Returns a String representation of this linked exercise.
   */
  public String toString() {
    String linked = "";
    if (getNext() == null) {
      linked = exercise.toString() + " -> END"; // if next field is null
    } else {
      linked = exercise.toString() + " -> "; // if next field is Not null
    }
    return linked;
  }
}
