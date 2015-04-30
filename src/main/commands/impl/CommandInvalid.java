package main.commands.impl;

import main.core.Container;

/**
 * Class that handles invalid commands.
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
public class CommandInvalid implements Command {

  private String errorMessage;

  public CommandInvalid(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void execute(Container container) {
    System.err.println(errorMessage);
  }

}
