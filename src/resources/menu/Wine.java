package resources.menu;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Wine extends MenuItem {
    StringBuilder wineStr = new StringBuilder();

    public Wine() {
    }

    public List<Wine> loadWines() {
        try {
            List<Wine> winesList = new ArrayList<>();
            Scanner scanner = new Scanner(new FileReader("vinhos-tabulados.txt")).useDelimiter("\\n");
            scanner.nextLine();

            while (scanner.hasNext()) {
                String[] wineLoaded = scanner.next().split("\t");
                wineLoaded[0] = wineLoaded[0].replace(",", ".");
                wineLoaded[1] = wineLoaded[1].split("\r")[0];

                Wine wine = new Wine();
                wine.setName(wineLoaded[1]);
                wine.setPrice(Double.parseDouble(wineLoaded[0]));
                winesList.add(wine);
            }
            return winesList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveWine(Wine wine) {
        try {
            PrintWriter output = new PrintWriter(new FileWriter("vinhos-tabulados.txt", true));
            output.close();
            System.out.println("Vinho salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
