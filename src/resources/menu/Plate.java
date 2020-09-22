package resources.menu;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Plate extends MenuItem {

    HashMap<Integer, Plate> platesMap = new HashMap<Integer, Plate>();

    public Plate() {
    }

    public Plate selectPlate() {
        printPlates();

        Scanner scanCode = new Scanner(System.in);
        System.out.println("Digite o código do prato desejado:");
        Integer plateCode = scanCode.nextInt();
        return platesMap.get(plateCode);
    }

    public void printPlates() {
        platesMap.clear();
        Integer mapControl = 0;
        for (Plate plate : loadPlates()) {
            platesMap.put(++mapControl, plate);
            printItem(mapControl, plate);
        }
    }

    public List<Plate> loadPlates() {
        try {
            List<Plate> platesList = new ArrayList<>();
            Scanner scanner = new Scanner(new FileReader("pratos.csv")).useDelimiter("\\n");
            scanner.nextLine();

            while (scanner.hasNext()) {
                String[] plateLoaded = scanner.next().split(";");
                Plate plate = new Plate();
                plate.setName(plateLoaded[0]);
                plate.setPrice(Double.parseDouble(plateLoaded[1]));
                platesList.add(plate);
            }
            return platesList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void savePlate(Plate plate, boolean append) {
        try {
            PrintWriter output = new PrintWriter(new FileWriter("pratos.csv", append));
            output.print(prepareToSave(plate, append));
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String prepareToSave(Plate plate, boolean append) {
        StringBuilder plateStr = new StringBuilder();

        if (!append)
            plateStr.append("PRATO;PRECO");

        String platePrice = Double.toString(plate.getPrice());
        plateStr.append("\n");
        plateStr.append(plate.getName());
        plateStr.append(";");
        plateStr.append(platePrice);
        return plateStr.toString();
    }

    public void deletePlate() {
        platesMap.remove(selectPlate());
        boolean append = false;
        for (Plate plate : platesMap.values()) {
            savePlate(plate, append);
            append = true;
        }
    }
}
