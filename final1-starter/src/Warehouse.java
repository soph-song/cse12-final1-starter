import java.util.ArrayList;

public class Warehouse implements Inventory{
    int[] stock;
    Computer[] models;
    ArrayList<Order> orders;
    public void Warehouse(int[] stock, Computer[] models) {
        this.stock = stock;
        this.models = models;
        orders = new ArrayList<>();
    }
    @Override
    public boolean makeNewOrder(Order o) {
        if (o == null) {
        return false;
        }

        for (Order ord: orders) {
            if (ord.getId().equals(o.getId())) {
                return false;
            }
        }
        int index = getIndex(o);
        // if we didn't find requested model or Out of stock
        if (!available(index)) {
            o.setStatus(0);
            orders.add(o);
            return false;
        }
        else {
            o.setStatus(1);
            orders.add(o);
            return true;
        }
    }
    @Override
	public int trackOrder(String id) {
        if (id == null) {
            return -1;
        }
        for (Order o: orders) {
            if (o.getId().equals(id)) {
                return o.getStatus();
            }
        }
        return -1;
    }

    @Override
	public boolean fulfillNextOrder() {
        for (Order o: orders) {
            //found next idle order
            if (o.getStatus() == 1) {
                int index = getIndex(o);
                if (available(index)) {
                    o.setStatus(3);
                    stock[index] -=1;
                    return true;
                }
                else {
                    o.setStatus(2);
                    return false;
                }
            }
        }
        return false;
    }

    private int getIndex(Order o) {
        int index = 0;
        for (int i = 0; i < models.length; ++i) {
            if (models[i].equals(o.getProduct())) {
                break;
            }
            ++index;
        }
        return index;
    }

    private boolean available(int index) {
        if (index >= models.length || stock[index] <= 0) {
            return false;
        }
        return true;
    }

}
