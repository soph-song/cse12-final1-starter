
public interface Inventory {
	boolean makeNewOrder(Order o);
	int trackOrder(String id);
	boolean fulfillNextOrder();
}

interface Computer {
    // Returns the model name of the computer as a string
    String getModel();
}

class Order {
	String id;
	int status;
	Computer model;

	public Order(String id, int status, Computer model) {
		this.id = id;
		this.status = status;
		this.model = model;
	}

    // Returns the Id of this order
    String getId() {
		return id;
	}

    // Returns the status code of this order
    int getStatus() {
		return status;
	}

    // Sets the status code of this order
    void setStatus(int status) {
		this.status = status;
	}

    // Returns the Computer of this order
    Computer getProduct() {
		return model;
	}
}
