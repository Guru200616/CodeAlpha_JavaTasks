import java.util.*;

class Stock {
    private String symbol;
    private String name;
    private double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbol() { return symbol; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

class Portfolio {
    private Map<String, Integer> holdings = new HashMap<>();
    private double balance;

    public Portfolio(double initialBalance) {
        this.balance = initialBalance;
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (cost <= balance) {
            balance -= cost;
            holdings.put(stock.getSymbol(),
                    holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
            System.out.println("✅ Bought " + quantity + " shares of " + stock.getName());
        } else {
            System.out.println("❌ Not enough balance to buy " + quantity + " shares.");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        if (holdings.getOrDefault(stock.getSymbol(), 0) >= quantity) {
            double revenue = stock.getPrice() * quantity;
            balance += revenue;
            holdings.put(stock.getSymbol(), holdings.get(stock.getSymbol()) - quantity);
            System.out.println("✅ Sold " + quantity + " shares of " + stock.getName());
        } else {
            System.out.println("❌ Not enough shares to sell.");
        }
    }

    public void showPortfolio(Map<String, Stock> market) {
        System.out.println("\n📊 Portfolio Summary:");
        double totalValue = balance;
        for (String symbol : holdings.keySet()) {
            int qty = holdings.get(symbol);
            Stock stock = market.get(symbol);
            double value = stock.getPrice() * qty;
            totalValue += value;
            System.out.println(symbol + " - " + qty + " shares, Value: ₹" + value);
        }
        System.out.println("💰 Balance: ₹" + balance);
        System.out.println("📈 Total Portfolio Value: ₹" + totalValue);
    }
}

class StockMarket {
    private Map<String, Stock> stocks = new HashMap<>();

    public StockMarket() {
        stocks.put("TCS", new Stock("TCS", "Tata Consultancy Services", 3500));
        stocks.put("INFY", new Stock("INFY", "Infosys", 1500));
        stocks.put("RELI", new Stock("RELI", "Reliance Industries", 2500));
    }

    public Map<String, Stock> getStocks() {
        return stocks;
    }

    public void showMarket() {
        System.out.println("\n💹 Available Stocks:");
        for (Stock stock : stocks.values()) {
            System.out.println(stock.getSymbol() + " - " + stock.getName() + " @ ₹" + stock.getPrice());
        }
    }
}

public class TradingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StockMarket market = new StockMarket();
        Portfolio portfolio = new Portfolio(10000); // Start with ₹10,000

        while (true) {
            System.out.println("\n===== Stock Trading Menu =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    market.showMarket();
                    break;
                case 2:
                    market.showMarket();
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = sc.next().toUpperCase();
                    if (market.getStocks().containsKey(buySymbol)) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        portfolio.buyStock(market.getStocks().get(buySymbol), qty);
                    } else {
                        System.out.println("❌ Invalid stock symbol.");
                    }
                    break;
                case 3:
                    portfolio.showPortfolio(market.getStocks());
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = sc.next().toUpperCase();
                    if (market.getStocks().containsKey(sellSymbol)) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        portfolio.sellStock(market.getStocks().get(sellSymbol), qty);
                    } else {
                        System.out.println("❌ Invalid stock symbol.");
                    }
                    break;
                case 4:
                    portfolio.showPortfolio(market.getStocks());
                    break;
                case 5:
                    System.out.println("👋 Exiting Stock Trading App...");
                    sc.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
  }
