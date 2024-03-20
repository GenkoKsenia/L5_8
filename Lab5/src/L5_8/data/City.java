package L5_8.data;

public class City extends Place { //класс город, потомок place
    protected String head; //мэр

    public City(String name, double area, int population, String head) {
        super(name, area, population);
        this.head = head;
    }

    public City() {
    }
    @Override //переопределение метода report класса place
    protected String report() { //переопределение метода report класса place
        String info = "Город " + this.name + ": площадь - " + this.area + " км²" + ", население - " + this.population + ", мэр - " + this.head;
        return info;
    }
    public String getHead() {
        return head;
    }

    @Override
    public void setHeadChief(String str) {
        this.head = str;
    }
}

