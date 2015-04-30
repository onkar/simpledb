package main.commands.parser;

import main.commands.impl.Command;

public interface Parser {
  public Command getCommand(String cmd);
}
