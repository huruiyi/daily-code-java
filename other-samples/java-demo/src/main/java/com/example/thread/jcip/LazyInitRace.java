package com.example.thread.jcip;

import com.example.thread.jcip.annotations.NotThreadSafe;

/**
 * LazyInitRace
 * <p>
 * Race condition in lazy initialization
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class LazyInitRace {

  private ExpensiveObject instance = null;

  public ExpensiveObject getInstance() {
    if (instance == null) {
      instance = new ExpensiveObject();
    }
    return instance;
  }
}

class ExpensiveObject {

}

