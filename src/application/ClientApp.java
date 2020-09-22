package application;

import resources.menu.MenuItem;
import resources.menu.Drink;
import resources.menu.Plate;
import resources.menu.Wine;
import resources.requests.Request;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ClientApp {

    static String HEAD = "Cód \t Descrição \t\t\t\t\t\t\t\t\t Preço";
    static Request request = new Request();
    private static Plate plate = new Plate();
    private static Drink drink = new Drink();
    private static Wine wine = new Wine();

    public static void main(String[] args) {
        initMenu();
    }

    private static void initMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione um tipo de produto");
        System.out.println("1 - Pratos");
        System.out.println("2 - Bebidas");
        System.out.println("3 - Vinhos");
        System.out.println("");
        System.out.println("4 - Reiniciar pedido");
        System.out.println("5 - Finalizar pedido");
        Integer productType = scanner.nextInt();

        if (productType.equals(1)) {
            System.out.println(HEAD);
            addPlate();
        } else if (productType.equals(2)) {
            System.out.println(HEAD);
            addDrink();
        } else if (productType.equals(3)) {
            System.out.println(HEAD);
            addWine();
        } else if (productType.equals(4)) {
            request = new Request();
            initMenu();
        } else
            closeRequest();
    }

    private static void addPlate() {
        try {
            request.getMenuList().add(plate.selectPlate());

            System.out.println("Prato adicionado ao pedido com sucesso!\n");

            initMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addDrink() {
        try {
            HashMap<Integer, Drink> drinksMap = new HashMap<Integer, Drink>();
            Integer mapControl = 0;

            for (Drink drink : drink.loadDrinks()) {
                drinksMap.put(++mapControl, drink);
                //printItem(mapControl, drink);
            }

            Scanner scanCode = new Scanner(System.in);
            System.out.println("Digite o código da bebida desejada:");
            Integer drinkCode = scanCode.nextInt();

            request.getMenuList().add(drinksMap.get(drinkCode));

            System.out.println("Bebida adicionada ao pedido com sucesso!\n");

            initMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addWine() {
        try {
            HashMap<Integer, Wine> winesMap = new HashMap<Integer, Wine>();
            Integer mapControl = 0;

            for (Wine wine : wine.loadWines()) {
                winesMap.put(++mapControl, wine);
                //printItem(mapControl, wine);
            }

            Scanner scanCode = new Scanner(System.in);
            System.out.println("Digite o código do vinho desejado:");
            Integer wineCode = scanCode.nextInt();

            request.getMenuList().add(winesMap.get(wineCode));

            System.out.println("Vinho adicionado ao pedido com sucesso!\n");

            initMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    private static void closeRequest() {
        if (request.getMenuList().size() != 0) {
            System.out.println("Alguma observação final?");
            Scanner scanGuideline = new Scanner(System.in);
            String guideline = scanGuideline.next();
            request.setGuideline(guideline);

            writeRequestOnHistoryFile();

            System.out.println("Totais do pedido:");
            System.out.println("Total de itens: " + request.getMenuList().size());
            System.out.println("Total a pagar: " + totalCalculate(request));
        } else
            System.out.println("Pedido finalizado sem itens.");
    }

    private static Double totalCalculate(Request request) {
        Double totalPrice = 0.0;
        for (MenuItem item : request.getMenuList())
            totalPrice += item.getPrice();
        return totalPrice;
    }

    private static void writeRequestOnHistoryFile() {
        try {
            PrintWriter output = new PrintWriter(new FileWriter("historico.txt", true));
            String historyStr = "";
            for (MenuItem item : request.getMenuList()) {
                historyStr += item.getName();
                historyStr += "\t";
                historyStr += item.getPrice();
                historyStr += "\n";
            }
            historyStr += "Observações: ";
            historyStr += request.getGuideline();
            historyStr += ";\n\n";

            output.print(historyStr);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
