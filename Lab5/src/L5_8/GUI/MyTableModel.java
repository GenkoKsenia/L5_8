package L5_8.GUI;

import L5_8.data.City;
import L5_8.data.Place;
import L5_8.data.Region;
import L5_8.data.Village;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel{
    private Region data;
    public MyTableModel(Region region){
        this.data = region;
    }

    @Override
    public int getRowCount() {
        return data.getCount();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return data.getPlace(rowIndex).getName();
            case 1: return data.getPlace(rowIndex).getArea();
            case 2: return data.getPlace(rowIndex).getPopulation();
            case 3: {
                Place p = data.getPlace(rowIndex);
                if(p instanceof City){
                    return ((City) p).getHead();
                }else{
                    return ((Village) p).getChief();
                }
            }
            case 4: {
                Place p = data.getPlace(rowIndex);
                if(p instanceof City){
                    return "Город";
                }else{
                    return "Деревня";
                }
            }
        }
        return "default";
    }

    @Override
    public String getColumnName(int column){
        switch (column){
            case 0: return "Название";
            case 1: return "Площадь";
            case 2: return "Население";
            case 3: return "Мэр/Старейшина";
            case 4: return "Населенный пункт";
        }
        return "";
    }

    public void delete(int ind){
        this.data.remove(ind);
        this.fireTableDataChanged();
    }

    public void addCity(String name, double area, int population, String head) {
        data.add(new City(name, area, population, head));
        this.fireTableDataChanged();
    }

    public void addVillage(String name, double area, int population, String chief) {
        data.add(new Village(name, area, population, chief));
        this.fireTableDataChanged();
    }

    public String search(String str){
        return data.search(str);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        boolean f = false;
        switch (columnIndex){
            case 0: data.getPlace(rowIndex).setName((String)aValue);
                    break;
            case 1:
                try{
                    data.getPlace(rowIndex).setArea(Double.parseDouble((String)aValue));
                    break;
                }catch (NumberFormatException e) {
                    break;
                }
            case 2:
                try{
                    data.getPlace(rowIndex).setPopulation(Integer.parseInt((String)aValue));
                    break;
                }catch (NumberFormatException e) {
                    break;
                }
            case 3: {
                Place p = data.getPlace(rowIndex);
                if (p instanceof City) {
                    ((City) p).setHeadChief((String) aValue);
                } else {
                    ((Village) p).setHeadChief((String) aValue);
                }
                break;

            }
            case 4: break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return true;
            case 1: return true;
            case 2: return true;
            case 3: return true;
        }
        return false;
    }
}
