import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StdInReader {

  /**
   * Read double input from System's Standard input
   * @return Double value
   */
  public static Double doubleInput() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Double temp = null;
    do {
      try {
        temp = Double.parseDouble(br.readLine());
      } catch (NumberFormatException e) {
        System.out.println("Please enter integer or real value.");
        continue;
      } catch (IOException e) {
      }
      break;
    } while (true);
    return temp;
  }

  /**
   * Read integer input from System's Standard input
   * @return Integer value
   */
  public static Integer integerInput() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Integer temp = null;
    do {
      try {
        temp = Integer.parseInt(br.readLine());
      } catch (NumberFormatException e) {
        System.out.println("Please enter integer value.");
        continue;
      } catch (IOException e) {
      }
      break;
    } while (true);
    return temp;
  }

  /**
   * Read string input from System's Standard input
   * @return String value
   */
  public static String stringInput() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String temp = null;
    try {
      temp = br.readLine();
    } catch (IOException e) {
    }
    return temp;
  }


}
