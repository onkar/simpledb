package main.commands.impl;

import main.core.Container;

public class CommandEnd implements Command {

  public void execute(Container container) {
    System.exit(0);
  }

}
