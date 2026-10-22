
import java.util.*;

class Item 
{
    private final String name;
    private final int price;

    Item(String name, int price) 
    {
        this.name = name;
        this.price = price;
    }

    public String getName() 
    {
        return name;
    }

    public int getPrice() 
    {
        return price;
    }

    static Item findItem(char code) 
    {
        return switch (code) 
        {
            case 'T' -> new Item("Tea", 10);
            case 'C' -> new Item("Coffee", 15);
            case 'S' -> new Item("Samosa", 12);
            case 'P' -> new Item("Puff", 20);
            default -> null;
        };
    }
}

class Order 
{
    private int tokenNo;
    private Item item;
    private int quantity;
    static int nextToken = 101;
    static int totalOrders = 0;
    static int totalSales = 0;

    Order(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.tokenNo = nextToken++;
        totalOrders++;
        totalSales += getAmount();
    }

    int getAmount() 
    {
        return item.getPrice() * quantity;
    }

    static int getTotalOrders() 
    {
        return totalOrders;
    }

    static int getTotalSales() 
    {
        return totalSales;
    }

    public String toString() 
    {
        return "Token " + tokenNo + ": " + item.getName()
                + " x " + quantity + " = " + getAmount();
    }
}

public class CanteenBilling 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] results = new String[n];
        for (int i = 0; i < n; i++) {
            char code = sc.next().charAt(0);
            int quantity = sc.nextInt();
            Item item = Item.findItem(code);
            if (item == null || quantity <= 0) 
            {
                results[i] = "Invalid order: " + code + " " + quantity;
            } 
            else 
            {
                Order order = new Order(item, quantity);
                results[i] = order.toString();
            }
        }
        for (int i = 0; i < n; i++) 
            {
            System.out.println(results[i]);
        }

        System.out.println("Total orders: " + Order.getTotalOrders()+ ", Total sales: " + Order.getTotalSales());
    }
}
