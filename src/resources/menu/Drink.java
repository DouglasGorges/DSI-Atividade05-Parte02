package resources.menu;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Drink extends MenuItem {
    StringBuilder drinkStr = new StringBuilder();

    public Drink() {
    }

    public List<Drink> loadDrinks() {
        try {
            List<Drink> drinksList = new ArrayList<>();
            Scanner scanner = new Scanner(new FileReader("bebidas-tabuladas.txt")).useDelimiter("\\n");
            scanner.nextLine();

            while (scanner.hasNext()) {
                String[] drinkLoaded = scanner.next().split("\t");
                drinkLoaded[0] = drinkLoaded[0].replace(",", ".");
                drinkLoaded[1] = drinkLoaded[1].split("\r")[0];

                Drink drink = new Drink();
                drink.setName(drinkLoaded[1]);
                drink.setPrice(Double.parseDouble(drinkLoaded[0]));
                drinksList.add(drink);
            }
            return drinksList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveDrink(Drink drink) {
        try {
            PrintWriter output = new PrintWriter(new FileWriter("bebidas-tabuladas.txt", true));
            output.print(prepareToSave(drink));
            output.close();
            System.out.println("Bebida salva com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String prepareToSave(Drink drink) {
        String drinkPriceRaw = Double.toString(drink.getPrice());
        String drinkPrice = drinkPriceRaw.replace(".", ",");
        drinkStr.append("\n");
        drinkStr.append(drinkPrice);
        drinkStr.append("\t");
        drinkStr.append(drink.getName());
        return drinkStr.toString();
    }
}
