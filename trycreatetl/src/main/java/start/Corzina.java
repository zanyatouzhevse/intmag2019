package start;

import java.util.ArrayList;

public class Corzina {
    public ArrayList <ElementCorzini> Ellist=new ArrayList<>();
    boolean NotEmptyList;

    public Corzina (){

        NotEmptyList=false;


    }
    public void addEl(ElementCorzini elem) {
        boolean novEl = true;
        for (int i = 0; i < Ellist.size(); i++) {
            if (elem.id == Ellist.get(i).id) {
                Ellist.get(i).kolvo++;
                novEl = false;
                break;
            }
        }
        if (novEl) {
            NotEmptyList = true;
            Ellist.add(elem);
        }
    }
    public void clearAll(){
        Ellist.clear();
    }

}
