import java.util.ArrayList;
import java.util.Scanner;

class Stock {
    private String symbol;
    private String name;
    private double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void displayStock() {
        System.out.printf("%-10s %-20s %.2f%n",
                symbol, name, price);
    }
}

class Transaction {
    private String type;
    private String stockSymbol;
    private int quantity;
    private double price;

    public Transaction(String type, String stockSymbol,
                       int quantity, double price) {
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
    }

    public void displayTransaction() {
        System.out.printf("%-8s %-10s %-10d %.2f%n",
                type, stockSymbol, quantity, price);
    }
}

class User {
    private String name;
    private double cash;
    private ArrayList<Stock> portfolio;
    private ArrayList<Integer> quantities;
    private ArrayList<Transaction> transactions;

    public User(String name, double cash) {
        this.name = name;
        this.cash = cash;
        portfolio = new ArrayList<>();
        quantities = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;

        if (cost > cash) {
            System.out.println("Insufficient balance!");
            return;
        }

        int index = findStock(stock.getSymbol());

        if (index == -1) {
            portfolio.add(stock);
            quantities.add(quantity);
        } else {
            quantities.set(index, quantities.get(index) + quantity);
        }

        cash -= cost;

        transactions.add(new Transaction(
                "BUY",
                stock.getSymbol(),
                quantity,
                stock.getPrice()
        ));

        System.out.println("Stock purchased successfully!");
    }

    public void sellStock(Stock stock, int quantity) {
        int index = findStock(stock.getSymbol());

        if (index == -1) {
            System.out.println("You do not own this stock.");
            return;
        }

        if (quantities.get(index) < quantity) {
            System.out.println("Not enough shares to sell.");
            return;
        }

        double value = stock.getPrice() * quantity;
        cash += value;

        quantities.set(index, quantities.get(index) - quantity);

        transactions.add(new Transaction(
                "SELL",
                stock.getSymbol(),
                quantity,
                stock.getPrice()
        ));

        if (quantities.get(index) == 0) {
            portfolio.remove(index);
            quantities.remove(index);
        }

        System.out.println("Stock sold successfully!");
    }

    private int findStock(String symbol) {
        for (int i = 0; i < portfolio.size(); i++) {
            if (portfolio.get(i).getSymbol().equalsIgnoreCase(symbol)) {
                return i;
            }
        }
        return -1;
    }

    public void displayPortfolio() {
        System.out.println("\n========== PORTFOLIO ==========");
        System.out.printf("Cash Balance: %.2f%n", cash);

        double totalValue = cash;

        if (portfolio.isEmpty()) {
            System.out.println("No stocks in portfolio.");
        } else {
            System.out.printf("%-10s %-15s %-10s %-15s%n",
                    "Symbol", "Stock", "Quantity", "Value");

            for (int i = 0; i < portfolio.size(); i++) {
                Stock stock = portfolio.get(i);
                int quantity = quantities.get(i);
                double value = stock.getPrice() * quantity;

                totalValue += value;

                System.out.printf("%-10s %-15s %-10d %.2f%n",
                        stock.getSymbol(),
                        stock.getName(),
                        quantity,
                        value);
            }
        }

        System.out.printf("Total Portfolio Value: %.2f%n", totalValue);
    }

    public void displayTransactions() {
        System.out.println("\n======= TRANSACTION HISTORY =======");

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        System.out.printf("%-8s %-10s %-10s %-10s%n",
                "Type", "Symbol", "Quantity", "Price");

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }
}

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Stock> market = new ArrayList<>();

        market.add(new Stock("AAPL", "Apple", 180.00));
        market.add(new Stock("GOOGL", "Google", 140.00));
        market.add(new Stock("MSFT", "Microsoft", 420.00));
        market.add(new Stock("AMZN", "Amazon", 175.00));
        market.add(new Stock("TSLA", "Tesla", 250.00));

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        User user = new User(name, balance);

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("     STOCK TRADING PLATFORM");
            System.out.println("================================");
            System.out.println("1. Display Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\n========== MARKET DATA ==========");
                    System.out.printf("%-10s %-20s %s%n",
                            "Symbol", "Company", "Price");

                    for (Stock stock : market) {
                        stock.displayStock();
                    }
                    break;

                case 2:
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = sc.next();

                    Stock buyStock = findMarketStock(
                            market, buySymbol);

                    if (buyStock == null) {
                        System.out.println("Stock not found.");
                        break;
                    }

                    System.out.print("Enter quantity: ");
                    int buyQuantity = sc.nextInt();

                    if (buyQuantity <= 0) {
                        System.out.println("Invalid quantity.");
                        break;
                    }

                    user.buyStock(buyStock, buyQuantity);
                    break;

                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = sc.next();

                    Stock sellStock = findMarketStock(
                            market, sellSymbol);

                    if (sellStock == null) {
                        System.out.println("Stock not found.");
                        break;
                    }

                    System.out.print("Enter quantity: ");
                    int sellQuantity = sc.nextInt();

                    if (sellQuantity <= 0) {
                        System.out.println("Invalid quantity.");
                        break;
                    }

                    user.sellStock(sellStock, sellQuantity);
                    break;

                case 4:
                    user.displayPortfolio();
                    break;

                case 5:
                    user.displayTransactions();
                    break;

                case 6:
                    System.out.println("Thank you for using the platform!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }

    private static Stock findMarketStock(
            ArrayList<Stock> market, String symbol) {

        for (Stock stock : market) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                return stock;
            }
        }

        return null;
    }
}