package resources.menu;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class Drink extends MenuItem {

    Map<Integer, Drink> drinksMap = new HashMap<Integer, Drink>();

    public Drink() {
    }

    public Drink selectDrink() {
        printDrinks();

        Scanner scanCode = new Scanner(System.in);
        System.out.println(CODIGO_DESEJADO);
        Integer drinkCode = scanCode.nextInt();
        return drinksMap.get(drinkCode);
    }

    public Integer selectDrinkCode() {
        printDrinks();

        Scanner scanCode = new Scanner(System.in);
        System.out.println(CODIGO_DESEJADO);
        Integer drinkCode = scanCode.nextInt();
        return drinkCode;
    }

    public void printDrinks() {
        drinksMap.clear();
        Integer mapControl = 0;
        for (Drink drink : loadDrinks()) {
            drinksMap.put(++mapControl, drink);
            printItem(mapControl, drink);
        }
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

    public void saveDrink(Drink drink, boolean append) {
        try {
            PrintWriter output = new PrintWriter(new FileWriter("bebidas-tabuladas.txt", append));
            output.print(prepareToSave(drink, append));
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String prepareToSave(Drink drink, boolean append) {
        StringBuilder drinkStr = new StringBuilder();

        if (!append)
            drinkStr.append("PRECO\tBEBIDA");

        String drinkPriceRaw = Double.toString(drink.getPrice());
        String drinkPrice = drinkPriceRaw.replace(".", ",");
        drinkStr.append("\n");
        drinkStr.append(drinkPrice);
        drinkStr.append("\t");
        drinkStr.append(drink.getName());
        return drinkStr.toString();
    }

    public void deleteDrink() {
        drinksMap.remove(selectDrinkCode());
        boolean appendInFile = false;
        for (Drink drink : drinksMap.values()) {
            saveDrink(drink, appendInFile);
            appendInFile = true;
        }
        System.out.println(SUCESSO);
    }
}
