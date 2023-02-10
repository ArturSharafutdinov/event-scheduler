package ru.ivanov.evgeny.eventscheduler.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.evgeny.eventscheduler.persistence.common.DiscreteRandomValue;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.BillRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.CinemaRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.CityRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.ProductRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service
public class Generator {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BillRepository billRepository;
    // Скидки по средам на билеты
    // 35% посетителей - студенты
    // 50% посетителей - студенты
    // На детские фильмы продаётся на 50% больше еды


    private double visitorsPercentage = 0.00125/16;
    private double wednesdayPercentage = 0.00025/16;
    private double holidaysPercentage = 0.00125/16;
    private double summerPercentage = 0.0005/16;
    private double vacationPercentage = 0.0005/16;

    private DiscreteRandomValue defaultValue = new DiscreteRandomValue(
            new int[]{1, 2, 3, 4, 5},
            new double[]{0.02, 0.35, 0.35, 0.2, 0.05}
    );

    private String CHILD_TICKET = "Детский билет";
    private String ADULT_TICKET = "Взрослый билет";
    private String JUICE = "Сок";
    private String COLA = "Кола";
    private String FANTA = "Фанта";
    private String SMALL_CORN = "Маленький попкорн";
    private String MEDIUM_CORN = "Средний попкорн";
    private String LARGE_CORN = "Большой попкорн";
    private String CHIPS = "Чипсы";
    private String TOY = "Игрушка";

    // что могут покупать дети и взрослые
    private Map<Boolean, List<String>> purchaseAccess = new HashMap<>() {{
        put(Boolean.TRUE, List.of(JUICE, COLA, FANTA, SMALL_CORN, CHIPS, TOY));
        put(Boolean.FALSE, List.of(JUICE, COLA, FANTA, SMALL_CORN, MEDIUM_CORN, LARGE_CORN, CHIPS, TOY));
    }};

    @Transactional
    public void generate() {

        LocalDate startDate = LocalDate.of(2020, Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(2020, Month.JANUARY, 3);

        List<City> cities = cityRepository.findAll();

        List<Product> products = productRepository.findAll();

        // Для каждого дня
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {

            double eventsFinalPercentage = visitorsPercentage;

            if (DayOfWeek.WEDNESDAY.equals(date.getDayOfWeek())) {
                eventsFinalPercentage += wednesdayPercentage;
            }

            if ((Month.JANUARY.equals(date.getMonth()) && date.getDayOfMonth() < 10) || (Month.DECEMBER.equals(date.getMonth()) && date.getDayOfMonth() > 28)) {
                eventsFinalPercentage += holidaysPercentage;
            }

            if (Month.JUNE.equals(date.getMonth()) || Month.JULY.equals(date.getMonth()) || Month.AUGUST.equals(date.getMonth())) {
                eventsFinalPercentage += summerPercentage;
            }

            if (((Month.OCTOBER.equals(date.getMonth()) && date.getDayOfMonth() > 28) || (Month.NOVEMBER.equals(date.getMonth()) && date.getDayOfMonth() < 6))
                    || ((Month.MARCH.equals(date.getMonth()) && date.getDayOfMonth() > 24) || (Month.APRIL.equals(date.getMonth()) && date.getDayOfMonth() < 2))) {
                eventsFinalPercentage += vacationPercentage;
            }

            for (City city : cities) {
                List<Cinema> cinemasInCity = cinemaRepository.findAllByCity(city);
                // Количество кинотеатров в городе
                int cinemasCount = cinemasInCity.size();

                // Среднее количество посетителей всех кинотеатров в городе в день
                int averageVisitors = (int) ((double) city.getPopulation() * eventsFinalPercentage);
                // Среднее количество посетителей в каждом кинотеатре
                int averageCinemaVisitors = (int) ((double) averageVisitors / cinemasCount);

                for (Cinema cinema : cinemasInCity) {
                    // Ещё раз рандомим, чтобы не повторялось.
                    int visitorsCount = (int) (averageCinemaVisitors * (0.9 + Math.random() % 0.4));

                    // Количество людей, которые вместе пришли на фильм (от 1 до 5 человек)
                    int visitorGroup = defaultValue.getNext();

                    do {

                        Bill bill = new Bill();
                        bill.setBillDate(java.sql.Date.valueOf(date));

                        boolean hasChild = this.hasChild(visitorGroup, eventsFinalPercentage * 4);
                        int childCount = 0;
                        if (hasChild) {
                            childCount = this.childCount(visitorGroup, eventsFinalPercentage * 4);
                        }
                        int adultCount = visitorGroup - childCount;

                        double billSum = 0;

                        List<Sale> sales = new ArrayList<>();
                        for (int i = 0; i < childCount; i++) {
                            int productsCount = 0;
                            for (Product product : products) {
                                if (productsCount >= 3) {
                                    break;
                                }

                                if (this.isPurchased(product, true)) {
                                    Sale sale = new Sale();
                                    sale.setCity(city);
                                    sale.setProduct(product);
                                    int count = this.productCountRandom();
                                    double price = product.getPrice() * count;
                                    sale.setCount(count);
                                    sale.setPrice(price);
                                    sales.add(sale);

                                    billSum+=price;

                                    productsCount++;
                                }
                            }
                        }

                        for (int i = 0; i < adultCount; i++) {
                            int productsCount = 0;
                            for (Product product : products) {
                                if (productsCount >= 3) {
                                    break;
                                }

                                if (this.isPurchased(product, false)) {
                                    Sale sale = new Sale();
                                    sale.setCity(city);
                                    sale.setProduct(product);
                                    int count = this.productCountRandom();
                                    double price = product.getPrice() * count;
                                    sale.setCount(count);
                                    sale.setPrice(product.getPrice() * count);
                                    sales.add(sale);

                                    billSum+=price;

                                    productsCount++;
                                }
                            }
                        }

                        bill.setSales(sales);
                        double discount = billSum > 2000.0 ? billSum * 0.05 : 0;
                        bill.setDiscount(discount);
                        billRepository.save(bill);


                        visitorGroup = defaultValue.getNext();
                        visitorsCount -= visitorGroup;
                    } while (visitorsCount > 0);
                }
            }
        }
        System.out.println("FINISH");
    }

    private boolean hasChild(int group, double additionalChance) {
        int random = new Random().nextInt();
        switch (group) {
            case 2 -> {
                return !(random < 0.8 + additionalChance);
            }
            case 3 -> {
                return !(random < 0.5 + additionalChance);
            }
            case 4 -> {
                return !(random < 0.3 + additionalChance);
            }
            case 5 -> {
                return !(random < 0.2 + additionalChance);
            }
            default -> {
                return false;
            }
        }
    }

    private int childCount(int group, double additionalChange) {
        int random = new Random().nextInt();
        switch (group) {
            case 3 -> {
                return random < 0.8 + additionalChange ? 1 : 2;
            }
            case 4 -> {
                return random < 0.5 + additionalChange ? 1 : 2;
            }
            case 5 -> {
                return random < 0.3 + additionalChange ? 1 : 2;
            }
            default -> {
                return 1;
            }
        }
    }

    private boolean isPurchased(Product product, boolean isChild) {
        List<String> strings = this.purchaseAccess.get(isChild);
        if (!strings.contains(product.getName())) {
            return false;
        }

        if (product.getType().getName().equals(ADULT_TICKET) && !isChild) {
            return true;
        }

        if (product.getType().getName().equals(CHILD_TICKET) && isChild) {
            return true;
        }

        double random = new Random().nextDouble();

        return random > 0.85;
    }

    private int productCountRandom() {
        double random = new Random().nextDouble();
        return random < 0.95 ? 1 : 2;
    }
}
