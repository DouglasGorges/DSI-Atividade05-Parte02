package resources.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class MenuItem {
    String name;
    Double price;
    static Integer CHARACTERS_QTY = 40;
    static String CODIGO_DESEJADO = "Digite o código do produto desejado:";
    static String SUCESSO = "Operação efetuada com sucesso!\n";

    public MenuItem() {
    }

    public void printItem(Integer code, MenuItem item){
        System.out.println(code + "\t\t" + prepareDescription(item.getName()) + "\t" + item.getPrice());
    }

    private static String prepareDescription(String description) {
        String plateDescription = "";
        if (description.length() >= CHARACTERS_QTY)
            plateDescription = description.substring(0, CHARACTERS_QTY);
        else {
            plateDescription = description;
            while (plateDescription.length() <= CHARACTERS_QTY)
                plateDescription += " ";
        }
        return plateDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}