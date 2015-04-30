package main.commands.impl;

import main.core.Container;

/**
 * Command that handles end of a transaction
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
public class CommandEnd implements Command {

  public void execute(Container container) {
    System.exit(0);
  }

}
