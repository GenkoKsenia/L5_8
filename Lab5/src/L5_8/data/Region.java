package L5_8.data;

import java.util.ArrayList;
import java.util.Scanner;

public class Region {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList <Place> places = new ArrayList();

    public static void add(Place p)
    {
        places.add(p);
    }

    public static void print()
    {   for(Place p : places) {
            p.report();
        }
    }

    public static String search(String str)
    {
        String name_s = str;
        boolean one = false;
        for(Place p : places) {
            if (p.name.equals(name_s)){
                if (p instanceof City) {
                    one = true;
                    return ((City) p).report();
                } else {
                    one = true;
                    return ((Village) p).report();
                }
            }
        }
        if(!one){
            return "Место не найдено";
        }
        return "Ошибка";
    }
    public static void delete()
    {
        System.out.println("Введите название места:");
        String name_s = scanner.next();
        boolean  one = false;

        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).name.equals(name_s)) {
                places.remove(places.get(i));
                one = true;
            }
        }
        if(!one){
            System.out.println("Место не найдено");
        }
    }

    public int getCount() {
        return this.places.size();
    }

    public Place getPlace(int index) {
        return places.get(index);
    }

    public void remove(int ind) {
        this.places.remove(ind);
    }

}
