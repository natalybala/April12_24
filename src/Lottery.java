import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public final class Lottery {
  private static PriorityQueue<Toys> sequence;

  Lottery() {
      sequence = new PriorityQueue<>((t1, t2) -> Float.compare(t1.getFreq(), t2.getFreq()));
  }

  public static void put(String input) {
      String[] enter = input.split(" ");
      int id = Integer.parseInt(enter[0]);
      String name = enter[1];
      int qty = Integer.parseInt(enter[2]);
      float freq = Float.parseFloat(enter[3]);

      Toys toy = new Toys(id, name, qty, freq);
      sequence.add(toy);

  }

  public static int getToyId() {
      Random random = new Random();
      double rand = random.nextDouble();
      if (rand <= 0.1) {
          return 1;
      } else if (rand <= 0.25) {
          return 2;
      } else if (rand <= 0.32) {
          return 3;
      } else if (rand <= 0.38) {
          return 4;
      } else if (rand <= 0.42) {
          return 5;
      } else if (rand <= 0.62) {
          return 6;
      } else if (rand <= 0.70) {
          return 7;
      } else {
          return 8;
      }
  }

  public static Toys getToyById(int id) {
      for (Toys toy : sequence) {
          if (toy.getId() == id) {
              return toy;
          }
      }
      return null;
  }

  public static boolean checkAvailable (Toys toy){
      if (toy.getQty() > 0){
          toy.qty -= 1;
          return true;
      }
      return false;
  }

  public static void saveToFile(String filename, ArrayList<Toys> winners) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
          int k = 0;
          for (Toys t: winners) {
              k += 1;
              writer.write("Round " + k + ": your prize is " + t.getName());
              writer.newLine();
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}