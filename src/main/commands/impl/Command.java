package main.commands.impl;

import main.core.Container;

public interface Command {
  public abstract void execute(Container container);
}
