package resources.menu;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class Wine extends MenuItem {
    Map<Integer, Wine> winesMap = new HashMap<Integer, Wine>();

    public Wine() {
    }

    public Wine selectWine() {
        printWines();

        Scanner scanCode = new Scanner(System.in);
        System.out.println("Digite o código do vinho desejado:");
        Integer wineCode = scanCode.nextInt();
        return winesMap.get(wineCode);
    }

    public Integer selectWineCode() {
        printWines();

        Scanner scanCode = new Scanner(System.in);
        System.out.println("Digite o código do vinho desejado:");
        Integer wineCode = scanCode.nextInt();
        return wineCode;
    }

    public void printWines() {
        winesMap.clear();
        Integer mapControl = 0;
        for (Wine wine : loadWines()) {
            winesMap.put(++mapControl, wine);
            printItem(mapControl, wine);
        }
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

    public void saveWine(Wine wine, boolean append) {
        try {
            PrintWriter output = new PrintWriter(new FileWriter("vinhos-tabulados.txt", append));
            output.print(prepareToSave(wine, append));
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String prepareToSave(Wine wine, boolean append) {
        StringBuilder wineStr = new StringBuilder();

        if (!append)
            wineStr.append("PRECO\tVINHO");

        String winePrice = Double.toString(wine.getPrice());
        wineStr.append("\n");
        wineStr.append(winePrice);
        wineStr.append("\t");
        wineStr.append(wine.getName());
        return wineStr.toString();
    }

    public void deleteWine() {
        winesMap.remove(selectWineCode());
        boolean appendInFile = false;
        for (Wine wine : winesMap.values()) {
            saveWine(wine, appendInFile);
            appendInFile = true;
        }
        System.out.println("Operação efetuada com sucesso!\n");
    }
}