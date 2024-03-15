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
 * This class uses a singly-linked list data structure to maintain a list of exercises organized
 * according to their WorkoutType.
 * 
 * @author Iris Xu
 */
import java.util.NoSuchElementException;

public class WorkoutBuilder implements ListADT<Exercise> {

  // The number of exercises with WorkoutType equal to COOLDOWN in this WorkoutBuilder list
  private int cooldownCount;

  // The node containing the element at index 0 of this singly-linked list
  private LinkedExercise head;

  // The number of exercises with WorkoutType equal to PRIMARY in this WorkoutBuilder list
  private int primaryCount;

  // The total number of exercises contained in this WorkoutBuilder list
  private int size;

  // The node containing the last element of this singly-linked list
  private LinkedExercise tail;

  // The number of exercises with WorkoutType equal to WARMUP in this WorkoutBuilder list
  private int warmupCount;


  /**
   * Adds the provided Exercise to the appropriate position in the list for its WorkoutType, and
   * increments the corresponding counter fields.
   * 
   * @param newObject - new exercise added to the list
   */
  @Override
  public void add(Exercise newObject) {
    // WorkoutBuilder list = new WorkoutBuilder();

    // WARMUP: adds to the FRONT (head) of the list
    if (newObject.getType() == WorkoutType.WARMUP) {
      LinkedExercise newWarmup = new LinkedExercise(newObject, head);
      head = newWarmup;

      if (size == 0)
        tail = newWarmup;
      // update counter
      size++;
      warmupCount++;
    }

    // PRIMARY: adds after all warm-ups and before any cool-downs; if there are any existing primary
    // exercises, adds before all of those as well
    else if (newObject.getType() == WorkoutType.PRIMARY) {
      if (size == 0) {
        LinkedExercise newPrimary = new LinkedExercise(newObject);
        head = newPrimary;
        tail = newPrimary;
      }

      else {// if head is primary or cool down
        if (head.getExercise().getType() == WorkoutType.PRIMARY
            || head.getExercise().getType() == WorkoutType.COOLDOWN) {
          head = new LinkedExercise(newObject, head);
        } else {// if head is warm up
          LinkedExercise current = head;
          LinkedExercise previous = current;
          int i = 0;
          while (current != null && current.getExercise().getType() == WorkoutType.WARMUP
              && i < size()) {
            previous = current;
            current = current.getNext();
            i++;
          }
          if (current == null) {// if the tail of the list is warm up
            previous.setNext(new LinkedExercise(newObject, null));
            tail = new LinkedExercise(newObject, null);
          } else {// if primary is in the middle of the list
            LinkedExercise next = previous.getNext();
            previous.setNext(new LinkedExercise(newObject, next));
          }
        }
      }
      // update counter
      size++;
      primaryCount++;

    }

    // COOLDOWN: adds to the END (tail) of the list
    else if (newObject.getType() == WorkoutType.COOLDOWN) {
      LinkedExercise newCoolDown = new LinkedExercise(newObject);
      if (size == 0) {
        head = newCoolDown;
        tail = newCoolDown;
      } else {
        tail.setNext(newCoolDown);
        tail = newCoolDown;
      }
      // update counter
      size++;
      cooldownCount++;
    }
  }

  /**
   * Removes all elements from this list
   */
  public void clear() {
    // set head and tail to null
    head = null;
    tail = null;

    // set counter to zero
    size = 0;
    warmupCount = 0;
    primaryCount = 0;
    cooldownCount = 0;
  }

  /**
   * Returns the exercise stored at the given index of this list without removing it.
   */
  @Override
  public Exercise get(int index) {
    if (index > size - 1 || index < 0) {
      throw new IndexOutOfBoundsException("index: " + index + " is out of bound");
    }
    // start from head and index 0
    LinkedExercise current = head;
    int i = 0;
    // check until the index reach
    while (current != null && i < index) {
      current = current.getNext();
      i++;
    }
    return current.getExercise();
  }

  /**
   * Accesses the number of cool-down exercises stored in this WorkoutBuilder list
   * 
   * @return cooldownCount
   */
  public int getCooldownCount() {
    return cooldownCount;
  }

  /**
   * Accesses the number of primary exercises stored in this WorkoutBuilder list
   * 
   * @return primaryCount
   */
  public int getPrimaryCount() {
    return primaryCount;
  }

  /**
   * Accesses the number of warm-up exercises stored in this WorkoutBuilder list
   * 
   * @return warmupCount
   */
  public int getWarmupCount() {
    return warmupCount;
  }

  /**
   * Finds the index of a given exercise in this WorkoutBuilder list, if it is present.
   * 
   * @param findObject
   * @return index of the parameter exercise in the list
   */
  @Override
  public int indexOf(Exercise findObject) {
    LinkedExercise current = head;
    int i = 0;
    while (current != null) {
      if (current.getExercise().equals(findObject)) {
        return i; // return the index found
      }
      i++;
      current = current.getNext();
    }
    return -1;// return -1 if not found;
  }


  /**
   * Checks whether this WorkoutBuilder list is empty
   * 
   * @return true if empty and false otherwise
   */
  @Override
  public boolean isEmpty() {
    // check whether head and tail are null and counter and size are zero
    return size == 0 && cooldownCount == 0 && primaryCount == 0 && warmupCount == 0 && head == null
        && tail == null;
  }

  /**
   * decrement the corresponding counter by 1 when removed
   * 
   * @param e - the exercise that removed
   */
  private void manageCounter(Exercise e) {
    if (e.getType() == WorkoutType.PRIMARY)
      primaryCount--; // decrement primaryCount if get type PRIMARY
    else if (e.getType() == WorkoutType.WARMUP)
      warmupCount--; // decrement warmupCount if get type WARMUP
    else if (e.getType() == WorkoutType.COOLDOWN)
      cooldownCount--; // decrement cooldownCount if get type COOLDOWN
    size--; // decrement size
  }

  /**
   * Removes the exercise with the provided ID number from the list, if it is present, and adjusts
   * any corresponding counter fields as necessary.
   * 
   * @param exercisedID - exercise ID of the removed node
   * @return the removed exercise
   */
  public Exercise removeExercise(int exerciseID) {
    LinkedExercise current = head;
    LinkedExercise previous = null;
    Exercise removed = null;

    // throw exception if the list is empty
    if (head == null)
      throw new NoSuchElementException("No such exercise in the list");

    // check whether there's a match if list not empty
    while (current != null) {
      if (current.getExercise().getExerciseID() == exerciseID) {
        removed = current.getExercise();
        if (previous == null) { // Removing from the head
          head = current.getNext();
          if (head == null) {
            tail = null;
          }
        } else {
          previous.setNext(current.getNext());
          if (current.getNext() == null) { // Removing from the tail
            tail = previous;
          }
        }
        manageCounter(removed);
        return removed;
      }
      previous = current;
      current = current.getNext();
    }
    // match not found
    throw new NoSuchElementException("No such exercise in the list");
  }


  /**
   * Removes an exercise of the provided type from the list, if one exists, and decrements the
   * corresponding counter fields.
   * 
   * @param type - the work out type of the removed exercise
   * @return the removed exercise
   */
  public Exercise removeExercise(WorkoutType type) {
    LinkedExercise current = head;
    LinkedExercise previous = current;
    Exercise removed = null;

    // throw exception if the list is empty
    if (head == null)
      throw new NoSuchElementException("No such exercise in the list");

    // If Head match the removed type
    // WARMUP: removes the FIRST (head) element from the list
    if (head != null && head.getExercise().getType() != WorkoutType.COOLDOWN
        && head.getExercise().getType() == type) {
      removed = head.getExercise();
      if (size == 1) {// if there's only one element in the list and match the removed type
        head = null;
        tail = null;
      } else if (size > 1) {
        head = head.getNext();
      }
      // adjust the counter
      manageCounter(removed);
      return removed;
    }

    // PRIMARY: removes the FIRST exercise of type PRIMARY from the list
    else if (type == WorkoutType.PRIMARY) {
      while (current != null) {
        // check whether there's a matched exercise in the middle of the list
        if (current.getExercise().getType() == type) {
          // remove the exercise
          removed = current.getExercise();
          previous.setNext(current.getNext());
          // adjust the counter
          manageCounter(removed);
          break;
        }
        previous = current;
        current = current.getNext();
      }
    }
    // COOLDOWN: removes the LAST (tail) element from the list
    else if (type == WorkoutType.COOLDOWN) {
      if (tail != null && tail.getExercise().getType() == WorkoutType.COOLDOWN) {
        removed = tail.getExercise();
        // If there's only one element in the list
        if (size == 1) {
          head = null;
          tail = null;
        } else {
          // Find the previous node to the tail
          while (current != null && current.getNext() != null && current != tail) {
            previous = current;
            current = current.getNext();
          }
          previous.setNext(null);
          tail = previous;
        }
        // adjust the counter
        manageCounter(removed);
      }
    }
    // throw exception if no match found
    if (removed == null) {
      throw new NoSuchElementException("No such exercise in the list");
    }
    return removed;
  }

  /**
   * Accesses the total number of elements in this WorkoutBuilder list
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns a String representation of the contents of this list
   */
  public String toString() {
    String str = "";
    int i = 0;
    LinkedExercise current = head;
    while (i < size && current != null) {
      // concatenate the string representation of the list
      str += current.toString();
      current = current.getNext();
    }
    return str;
  }

}
