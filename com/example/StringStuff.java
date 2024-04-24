import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Change me.
 *
 * @author Kevin Csiffary
 * @version 1.0
 * @since 2024-04-17
 */

// StringStuff class
public final class StringStuff {

  /** Private constructor to prevent instantiation. */
  private StringStuff() {
    throw new UnsupportedOperationException("Cannot instantiate");
  }

  /**
   * This is the main method.
   *
   * @param args Unused
   */
  public static void main(final String[] args) throws Exception {
    try {
      // Setup scanner on file.
      File file = new File("./Input-Output/input.txt");
      Scanner sc = new Scanner(file);
      FileWriter blowUpWriter = new FileWriter("./Input-Output/blowup.txt");
      BufferedWriter blowUpBufferedWriter = new BufferedWriter(blowUpWriter);
      FileWriter maxRunWriter = new FileWriter("./Input-Output/maxrun.txt");
      BufferedWriter maxRunBufferedWriter = new BufferedWriter(maxRunWriter);
      FileWriter shrinkWriter = new FileWriter("./Input-Output/shrink.txt");
      BufferedWriter shrinkBufferedWriter = new BufferedWriter(shrinkWriter);

      while (sc.hasNextLine()) {
        // Read the line from file.
        String line = sc.nextLine();

        // Call methods and write to file.
        blowUpBufferedWriter.write(blowUp(line));
        blowUpBufferedWriter.newLine();
        maxRunBufferedWriter.write(Integer.toString(maxRun(line)));
        maxRunBufferedWriter.newLine();
        shrinkBufferedWriter.write(shrink(line));
        shrinkBufferedWriter.newLine();
      }

      // Close all writers and scanner.
      blowUpBufferedWriter.close();
      blowUpWriter.close();
      maxRunBufferedWriter.close();
      maxRunWriter.close();
      shrinkBufferedWriter.close();
      shrinkWriter.close();
      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Blow Up method.
   *
   * @param str
   * @return
   */
  public static String blowUp(final String str) {
    // Initialize array from the string.
    char[] lineArr = str.toCharArray();

    // Initialize variable.
    String output = "";

    // Loop through every char in the array.
    for (int i = 0; i < lineArr.length; i++) {
      // Check if the char is a number.
      if (Character.isDigit(lineArr[i])) {
        // Get the value of the number.
        int numCopies = Character.getNumericValue(lineArr[i]);
        // Loop through the number of copies and add that many.
        for (int j = 0; j < numCopies; j++) {
          output += lineArr[i + 1];
        }
        // If there is no number just add the character.
      } else {
        output += lineArr[i];
        continue;
      }
    }

    return output;
  }

  /**
   * Max Run method.
   *
   * @param str
   * @return
   */
  public static int maxRun(final String str) {
    // Initialize array from the string.
    char[] lineArr = str.toCharArray();

    // Initialize variables.
    int maxRun = 0;
    int currentRun = 1;
    char lastChar = lineArr[0];

    // Loop through every char in the array.
    for (int i = 1; i < lineArr.length; i++) {
      // Check if the previous character is the same as the current.
      if (lastChar == lineArr[i]) {
        // Increment the current run length.
        currentRun += 1;
      } else {
        // Check if the current run has surpassed the previous max run.
        if (currentRun > maxRun) {
          // Set the max run to the current run.
          maxRun = currentRun;
        }
        // Reset current run and set last char to the current char.
        currentRun = 1;
        lastChar = lineArr[i];
      }
    }
    return maxRun;
  }

  /**
   * Shrink method.
   *
   * @param str
   * @return
   */
  public static String shrink(final String str) {
    // Initialize array from the string.
    char[] lineArr = str.toCharArray();

    // Initialize variables.
    String output = "";
    int currentRun = 0;
    char currentChar = lineArr[0];

    // Loop through every char in the array.
    for (int i = 1; i < lineArr.length; i++) {
      // Check if the previous character is the same as the current.
      if (currentChar == lineArr[i]) {
        // Increment the current run length.
        currentRun += 1;
      } else {
        // Ensure the current run is not 0.
        if (currentRun != 0) {
          // Add the run length and the character to the string eg. '3f'.
          output += Integer.toString(currentRun) + currentChar;
        } else {
          // Add just the current character to the string.
          output += currentChar;
        }
        // Reset current run and set the current char to the current char.
        currentRun = 0;
        currentChar = lineArr[i];
      }
    }
    // Run the if block one more time to get the last letter.
    if (currentRun != 0) {
      // Add the run length and the character to the string eg. '3f'.
      output += Integer.toString(currentRun) + currentChar;
    } else {
      // Add just the current character to the string.
      output += currentChar;
    }
    return output;
  }
}
