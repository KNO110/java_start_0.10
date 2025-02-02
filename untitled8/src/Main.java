import java.util.*;
import java.util.stream.*;
import java.time.Year;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nВыберите задание: 1 - Числа, 2 - Продукты, 3 - Устройства, 4 - Проекторы, 0 - Выход");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Попробуйте снова.");
                continue;
            }
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    task1();
                    break;
                case 2:
                    task2();
                    break;
                case 3:
                    task3();
                    break;
                case 4:
                    task4();
                    break;
                default:
                    System.out.println("Неверный выбор");
            }
        }
        scanner.close();
    }

    // 1
    private static void task1() {
        List<Integer> numbers = new Random().ints(20, -100, 101)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("\nСгенерированные числа: " + numbers);

        long positiveCount = numbers.stream().filter(n -> n > 0).count();
        long negativeCount = numbers.stream().filter(n -> n < 0).count();
        long twoDigitCount = numbers.stream()
                .filter(n -> Math.abs(n) >= 10 && Math.abs(n) < 100)
                .count();
        long mirrorCount = numbers.stream().filter(n -> {
            String s = String.valueOf(Math.abs(n));
            return new StringBuilder(s).reverse().toString().equals(s);
        }).count();

        System.out.println("Количество положительных чисел: " + positiveCount);
        System.out.println("Количество отрицательных чисел: " + negativeCount);
        System.out.println("Количество двузначных чисел: " + twoDigitCount);
        System.out.println("Количество зеркальных чисел: " + mirrorCount);
    }

    // 2
    private static void task2() {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = Arrays.asList(
                new Product("Молоко", "Молоко"),
                new Product("Хлеб", "Выпечка"),
                new Product("Сыр", "Молоко"),
                new Product("Вода", "Напиток"),
                new Product("Йогурт", "Молоко"),
                new Product("Кефир", "Молоко"),
                new Product("Яйцо", "Продукт"),
                new Product("Молоко", "Молоко"),
                new Product("Чай", "Напиток"),
                new Product("Кофе", "Напиток")
        );

        System.out.println("\nВсе продукты:");
        products.forEach(System.out::println);

        System.out.println("\nПродукты с названием менее 5 символов:");
        products.stream()
                .filter(p -> p.getName().length() < 5)
                .forEach(System.out::println);

        System.out.println("\nВведите название продукта для подсчета:");
        final String name = scanner.nextLine();
        long count = products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .count();
        System.out.println("Продукт \"" + name + "\" встречается " + count + " раз(а)");

        System.out.println("\nВведите букву для поиска продуктов:");
        final String letter = scanner.nextLine();
        products.stream()
                .filter(p -> p.getName().toLowerCase().startsWith(letter.toLowerCase()))
                .forEach(System.out::println);

        System.out.println("\nПродукты из категории \"Молоко\":");
        products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Молоко"))
                .forEach(System.out::println);
    }

    // 3
    private static void task3() {
        Scanner scanner = new Scanner(System.in);
        List<Device> devices = Arrays.asList(
                new Device("Ведроид", 2020, 11399, "Чёрный", "Электроника"),
                new Device("Лаптоп", 2019, 29900, "Серый", "Компьютер"),
                new Device("Планшет", 2021, 24000, "Белый", "Электроника"),
                new Device("Телевизор(старый)", 2018, 600, "Чёрный", "Бытовая техника"),
                new Device("Часы(палёнка)", 2022, 200, "Синий", "Аксессуар")
        );

        System.out.println("\nВсе устройства:");
        devices.forEach(System.out::println);

        System.out.println("\nВведите цвет для фильтрации устройств:");
        final String color = scanner.nextLine();
        List<Device> devicesByColor = devices.stream()
                .filter(d -> d.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
        System.out.println("Устройства с цветом '" + color + "':");
        devicesByColor.forEach(System.out::println);

        System.out.println("\nВведите год выпуска для фильтрации устройств:");
        int inputYear;
        try {
            inputYear = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Пропускаем фильтрацию по году.");
            inputYear = -1;
        }
        final int filterYear = inputYear;
        if (filterYear != -1) {
            List<Device> devicesByYear = devices.stream()
                    .filter(d -> d.getYear() == filterYear)
                    .collect(Collectors.toList());
            System.out.println("Устройства с годом выпуска " + filterYear + ":");
            devicesByYear.forEach(System.out::println);
        }

        System.out.println("\nВведите минимальную цену для фильтрации устройств (выше указанной цены):");
        double inputPrice;
        try {
            inputPrice = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Пропускаем фильтрацию по цене.");
            inputPrice = -1;
        }
        final double filterPrice = inputPrice;
        if (filterPrice != -1) {
            List<Device> devicesByPrice = devices.stream()
                    .filter(d -> d.getPrice() > filterPrice)
                    .collect(Collectors.toList());
            System.out.println("Устройства дороже " + filterPrice + ":");
            devicesByPrice.forEach(System.out::println);
        }

        System.out.println("\nВведите тип устройства для фильтрации:");
        final String type = scanner.nextLine();
        List<Device> devicesByType = devices.stream()
                .filter(d -> d.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
        System.out.println("Устройства типа '" + type + "':");
        devicesByType.forEach(System.out::println);

        System.out.println("\nВведите начальный год для фильтрации устройств по диапазону:");
        int inputStartYear;
        try {
            inputStartYear = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Пропускаем фильтрацию по диапазону годов.");
            inputStartYear = -1;
        }
        final int startYear = inputStartYear;
        if (startYear != -1) {
            System.out.println("Введите конечный год для фильтрации устройств по диапазону:");
            int inputEndYear;
            try {
                inputEndYear = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Пропускаем фильтрацию по диапазону годов.");
                inputEndYear = -1;
            }
            final int endYear = inputEndYear;
            if (endYear != -1) {
                List<Device> devicesByYearRange = devices.stream()
                        .filter(d -> d.getYear() >= startYear && d.getYear() <= endYear)
                        .collect(Collectors.toList());
                System.out.println("Устройства с годом выпуска от " + startYear + " до " + endYear + ":");
                devicesByYearRange.forEach(System.out::println);
            }
        }
    }

    // 4
    private static void task4() {
        Scanner scanner = new Scanner(System.in);
        List<Projector> projectors = Arrays.asList(
                new Projector("Проектор A", 2020, 500, "Sony"),
                new Projector("Проектор B", Year.now().getValue(), 700, "Epson"),
                new Projector("Проектор C", 2019, 450, "Sony"),
                new Projector("Проектор D", Year.now().getValue(), 800, "BenQ"),
                new Projector("Проектор E", 2021, 650, "Epson")
        );

        System.out.println("\nВсе проекторы:");
        projectors.forEach(System.out::println);

        System.out.println("\nВведите производителя для фильтрации проекторов:");
        final String manufacturer = scanner.nextLine();
        List<Projector> projectorsByManufacturer = projectors.stream()
                .filter(p -> p.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
        System.out.println("Проекторы производителя '" + manufacturer + "':");
        projectorsByManufacturer.forEach(System.out::println);

        int currentYear = Year.now().getValue();
        List<Projector> projectorsCurrentYear = projectors.stream()
                .filter(p -> p.getYear() == currentYear)
                .collect(Collectors.toList());
        System.out.println("\nПроекторы текущего года (" + currentYear + "):");
        projectorsCurrentYear.forEach(System.out::println);

        System.out.println("\nВведите минимальную цену для фильтрации проекторов (выше указанной цены):");
        double inputPrice;
        try {
            inputPrice = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Пропускаем фильтрацию по цене.");
            inputPrice = -1;
        }
        final double filterProjectorPrice = inputPrice;
        if (filterProjectorPrice != -1) {
            List<Projector> projectorsByPrice = projectors.stream()
                    .filter(p -> p.getPrice() > filterProjectorPrice)
                    .collect(Collectors.toList());
            System.out.println("Проекторы дороже " + filterProjectorPrice + ":");
            projectorsByPrice.forEach(System.out::println);
        }

        List<Projector> sortedByPriceAsc = projectors.stream()
                .sorted(Comparator.comparingDouble(Projector::getPrice))
                .collect(Collectors.toList());
        System.out.println("\nПроекторы, отсортированные по цене (возрастание):");
        sortedByPriceAsc.forEach(System.out::println);

        List<Projector> sortedByPriceDesc = projectors.stream()
                .sorted(Comparator.comparingDouble(Projector::getPrice).reversed())
                .collect(Collectors.toList());
        System.out.println("\nПроекторы, отсортированные по цене (убывание):");
        sortedByPriceDesc.forEach(System.out::println);

        List<Projector> sortedByYearAsc = projectors.stream()
                .sorted(Comparator.comparingInt(Projector::getYear))
                .collect(Collectors.toList());
        System.out.println("\nПроекторы, отсортированные по году выпуска (возрастание):");
        sortedByYearAsc.forEach(System.out::println);

        List<Projector> sortedByYearDesc = projectors.stream()
                .sorted(Comparator.comparingInt(Projector::getYear).reversed())
                .collect(Collectors.toList());
        System.out.println("\nПроекторы, отсортированные по году выпуска (убывание):");
        sortedByYearDesc.forEach(System.out::println);
    }
}

class Product {
    private String name, category;

    public Product(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String toString() {
        return "Продукт: " + name + ", Категория: " + category;
    }
}

class Device {
    private String name, color, type;
    private int year;
    private double price;

    public Device(String name, int year, double price, String color, String type) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.color = color;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "Устройство: " + name + ", Год: " + year + ", Цена: " + price + ", Цвет: " + color + ", Тип: " + type;
    }
}

class Projector {
    private String name, manufacturer;
    private int year;
    private double price;

    public Projector(String name, int year, double price, String manufacturer) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String toString() {
        return "Проектор: " + name + ", Год: " + year + ", Цена: " + price + ", Производитель: " + manufacturer;
    }
}
