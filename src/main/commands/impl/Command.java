package main.commands.impl;

import main.core.Container;

/**
 * Interface which must be implemented by every command
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
public interface Command {
  public abstract void execute(Container container);
}
