package main.commands.impl;

import main.core.Container;

public class CommandBegin implements Command {

  public void execute(Container container) {
    container.updateToNewTransaction();
    System.out.println();
  }

}
