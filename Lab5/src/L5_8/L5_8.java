package L5_8;

import L5_8.data.City;
import L5_8.data.Region;
import L5_8.data.Village;
import L5_8.GUI.MainWindow;

public class L5_8 {
    public static void main(String[] args) {
        new MainWindow();

        //Начальные города и деревни
        Region.add(new City("Иркутск", 280, 611215, "Руслан Николаевич Болотов"));
        Region.add(new City("Ангарск", 294, 221296, "Сергей Анатольевич Петров"));
        Region.add(new City("Тулун", 128, 37842, "Нижегородцев Андрей Артурович"));
        Region.add(new Village("Алзамай", 43, 1242, "Н.Е.Р"));
        Region.add(new Village("Илир", 55, 3032, "Ц.Р.Л"));
        Region.add(new Village("Еланцы", 67, 2532, "Л.П.П"));





    }
}