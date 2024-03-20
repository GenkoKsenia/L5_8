package L5_8.data;

public class Village extends Place { //класс деревня, потомок place
    protected String chief; //старейшина
    public Village(String name, double area, int population, String chief) {
        super(name, area, population);
        this.chief = chief;
    }

    public Village() {
    }

    @Override //переопределение метода report класса place
    protected String report() {
        String info = "Деревня " + this.name + ": площадь - " + this.area + " км²" + ", население - " + this.population + ", староста - " + this.chief;
        return info;
    }

    public String getChief() {
        return chief;
    }

    @Override
    public void setHeadChief(String str) {
        this.chief = str;
    }
}
