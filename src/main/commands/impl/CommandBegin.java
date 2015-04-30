package main.commands.impl;

import main.core.Container;

/**
 * Command that handles beginning of a transaction
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
public class CommandBegin implements Command {

  public void execute(Container container) {
    container.updateToNewTransaction();
    System.out.println();
  }

}
