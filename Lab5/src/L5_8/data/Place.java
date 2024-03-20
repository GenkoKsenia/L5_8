package L5_8.data;

public abstract class Place { //супер класс место
    protected String name; //название
    protected double area; //площадь
    protected int population; //население


    public Place(String name, double area, int population) {
        this.name = name;
        this.area = area;
        this.population = population;
    }

    public Place() {
    }

    protected abstract String report(); //метод для вывода инфо

    protected abstract void setHeadChief(String str);

    public String getName(){
        return name;
    }
    public void setName(String n) {
        this.name = n;
    }

    public double getArea(){
        return area;
    }
    public void setArea(double a) {
        this.area = a;
    }

    public int getPopulation(){
        return population;
    }
    public void setPopulation(int p) {
        this.population = p;
    }
}

