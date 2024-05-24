import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

class User {
    private String username;
    private String password;
    private ShoppingCart cart;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.cart = new ShoppingCart();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ShoppingCart getCart() {
        return cart;
    }
}

class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}

public class ECommercePlatform {
    private List<Product> products;
    private List<User> users;
    private User currentUser;

    public ECommercePlatform() {
        products = new ArrayList<>();
        users = new ArrayList<>();
        populateProducts();
    }

    private void populateProducts() {
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Phone", 499.99));
        products.add(new Product("Headphones", 199.99));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentUser == null) {
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    login(scanner);
                } else if (choice == 2) {
                    register(scanner);
                } else {
                    System.out.println("Invalid option");
                }
            } else {
                System.out.println("1. View Products");
                System.out.println("2. View Cart");
                System.out.println("3. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    viewProducts(scanner);
                } else if (choice == 2) {
                    viewCart(scanner);
                } else if (choice == 3) {
                    currentUser = null;
                } else {
                    System.out.println("Invalid option");
                }
            }
        }
    }

    private void login(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentUser = users.stream()
            .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
            .findFirst()
            .orElse(null);

        if (currentUser == null) {
            System.out.println("Invalid credentials!");
        } else {
            System.out.println("Login successful!");
        }
    }

    private void register(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (users.stream().anyMatch(user -> user.getUsername().equals(username))) {
            System.out.println("Username already taken!");
        } else {
            users.add(new User(username, password));
            System.out.println("Registration successful!");
        }
    }

    private void viewProducts(Scanner scanner) {
        System.out.println("Available Products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }

        System.out.print("Enter the number of the product to add to cart (or 0 to go back): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice > 0 && choice <= products.size()) {
            Product product = products.get(choice - 1);
            currentUser.getCart().addProduct(product);
            System.out.println("Product added to cart!");
        } else if (choice != 0) {
            System.out.println("Invalid option");
        }
    }

    private void viewCart(Scanner scanner) {
        System.out.println("Your Cart:");
        for (Product product : currentUser.getCart().getProducts()) {
            System.out.println(product);
        }
        System.out.println("Total: $" + currentUser.getCart().getTotal());
        System.out.print("Enter 0 to go back: ");
        scanner.nextInt();
        scanner.nextLine(); // Consume newline
    }

    public static void main(String[] args) {
        ECommercePlatform platform = new ECommercePlatform();
        platform.run();
    }
}
