class OutOfStockException extends Exception
{
    private final int shortfall;

    public OutOfStockException(int shortfall)
    {
        super("Out of stock by " + shortfall);
        this.shortfall = shortfall;
    }

    public int getShortfall()
    {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception
{
    public InvalidQuantityException()
    {
        super("Quantity must be greater than zero");
    }
}

class Warehouse
{
    private int stock;

    public Warehouse(int stock)
    {
        this.stock = stock;
    }

    public void issue(int qty)
            throws OutOfStockException, InvalidQuantityException {

        if (qty <= 0)
            throw new InvalidQuantityException();

        if (qty > stock)
            throw new OutOfStockException(qty - stock);

        stock -= qty;
        System.out.println("Issued " + qty + " units");
    }
}

public class StockProcessor{
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse(50);

        int[] requests = {10, 20, 0, 30, -5, 25};

        for (int qty : requests) {
            try {
                warehouse.issue(qty);
            } catch (OutOfStockException e) {
                System.out.println(
                    "Request " + qty + " failed: shortfall = "
                    + e.getShortfall());
            } catch (InvalidQuantityException e) {
                System.out.println(
                    "Request " + qty + " failed: " + e.getMessage());
            }
        }
    }
}
