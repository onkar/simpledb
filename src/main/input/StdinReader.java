package main.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that reads from standard input
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
public class StdinReader implements Reader {
  private BufferedReader reader;

  public StdinReader() {
    this.reader = new BufferedReader(new InputStreamReader(System.in));
  }

  public String readCommand() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      System.err.println("Exception in reading the input stream : " + e.getMessage());
    }
    return null;
  }

}
