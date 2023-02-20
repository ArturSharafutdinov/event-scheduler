package ru.ivanov.evgeny.eventscheduler.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.*;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@org.springframework.stereotype.Service
public class Generator {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private BillRepository billRepository;

    private Map<Month, Double> rules = new HashMap<>() {{
        put(Month.JANUARY, 34.5);
        put(Month.FEBRUARY, 28.0);
        put(Month.MARCH, 32.75);
        put(Month.APRIL, 30.0);
        put(Month.MAY, 30.0);
        put(Month.JUNE, 34.5);
        put(Month.JULY, 35.65);
        put(Month.AUGUST, 35.65);
        put(Month.SEPTEMBER, 30.0);
        put(Month.OCTOBER, 32.25);
        put(Month.NOVEMBER, 31.25);
        put(Month.DECEMBER, 34.5);
    }};

    private Map<Date, List<Game>> discounts = new HashMap<>();

    @Transactional
    public void generate(int year, int salesCount) {

        double daysByRules = rules.values().stream().mapToDouble(Double::doubleValue).sum();

        long gamesCount = gameRepository.count();
        long servicesCount = serviceRepository.count();

        int salesInYear = 0;

        for (Month month : Month.values()) {

            LocalDate startDate = LocalDate.of(year, month, 1);
            LocalDate endDate = startDate.plusDays(month.length(false));

            int salesInMonth = (int) (salesCount * rules.get(month) / daysByRules);
            int salesInMonthCount = 0;

            do {
                Bill bill = new Bill();

                Date billDate = this.between(this.convertToDateViaInstant(startDate), this.convertToDateViaInstant(endDate));
                bill.setBillDate(billDate);

                int salesInBill = (int) (Math.random() * 5) + 1;

                List<Sale> sales = new ArrayList<>();

                for (int s = 0; s < salesInBill; s++) {
                    Sale sale = new Sale();

                    Long gameId = (long) (Math.random() * (gamesCount)) + 1L;
                    Game randomGame = gameRepository.getOne(gameId);
                    sale.setGame(randomGame);

                    Long serviceId = (long) (Math.random() * (servicesCount)) + 1L;
                    Service randomService = serviceRepository.getOne(serviceId);
                    sale.setService(randomService);

                    int gamesInSale = (int) (Math.random() * 2) + 1;
                    sale.setCount(gamesInSale);
                    sale.setPrice(randomGame.getPrice());
                    sale.setBill(bill);

                    sales.add(sale);
                }

                bill.setSales(sales);

                billRepository.save(bill);

                salesInMonthCount += salesInBill;


            } while (salesInMonthCount < salesInMonth);

            salesInYear += salesInMonth;
        }

        if (salesInYear < salesCount) {

            do {
                Bill bill = new Bill();

                LocalDate startDate = LocalDate.of(year, Month.JANUARY, 1);
                LocalDate endDate = LocalDate.of(year + 1, Month.JANUARY, 1);

                Date billDate = this.between(this.convertToDateViaInstant(startDate), this.convertToDateViaInstant(endDate));
                bill.setBillDate(billDate);

                int salesInBill = (int) (Math.random() * 5) + 1;

                List<Sale> sales = new ArrayList<>();
                for (int s = 0; s < salesInBill; s++) {
                    Sale sale = new Sale();

                    Long gameId = (long) (Math.random() * (gamesCount)) + 1L;
                    Game randomGame = gameRepository.getOne(gameId);
                    sale.setGame(randomGame);

                    Long serviceId = (long) (Math.random() * (servicesCount)) + 1L;
                    Service randomService = serviceRepository.getOne(serviceId);
                    sale.setService(randomService);

                    int gamesInSale = (int) (Math.random() * 2) + 1;
                    sale.setCount(gamesInSale);
                    sale.setPrice(randomGame.getPrice());

                    sale.setBill(bill);
                    sales.add(sale);
                }

                bill.setSales(sales);

                billRepository.save(bill);

                salesInYear+=salesInBill;


            } while (salesInYear < salesCount);
        }
    }

    private Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    private Date between(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }

    @Transactional
    public void genresInit(int count) {

        if (genreRepository.count() != 0) {
            return;
        }

        for (int i = 0; i < count; i++) {
            Genre genre = new Genre();
            genre.setName("Жанр " + (i + 1));
            genreRepository.save(genre);
        }
    }

    @Transactional
    public void companiesInit(int count) {

        if (companyRepository.count() != 0) {
            return;
        }

        for (int i = 0; i < count; i++) {
            Company company = new Company();
            company.setName("Компания " + (i + 1));
            company.setBirthDay(new Date(ThreadLocalRandom.current().nextInt() * 1000L));
            companyRepository.save(company);
        }
    }

    @Transactional
    public void gamesInit(int count) {

        if (gameRepository.count() != 0) {
            return;
        }

        long genresCount = genreRepository.count();
        long companiesCount = companyRepository.count();

        for (int i = 0; i < count; i++) {
            Game game = new Game();
            game.setName("Игра " + (i + 1));

            Double price = Math.random() * 2000 + 500;
            game.setPrice(price);

            Long genreId = (long) (Math.random() * (genresCount)) + 1L;
            game.setGenre(genreRepository.getOne(genreId));

            Long companyId = (long) (Math.random() * (companiesCount)) + 1L;
            game.setCompany(companyRepository.getOne(companyId));

            gameRepository.save(game);
        }
    }

    @Transactional
    public void platformsInit(int count) {

        if (platformRepository.count() != 0) {
            return;
        }

        for (int i = 0; i < count; i++) {
            Platform platform = new Platform();
            platform.setName("Платформа " + (i + 1));

            platformRepository.save(platform);
        }
    }

    @Transactional
    public void servicesInit(int count) {

        if (serviceRepository.count() != 0) {
            return;
        }

        long platformsCount = platformRepository.count();

        for (int i = 0; i < count; i++) {
            Service service = new Service();
            service.setName("Сервис " + (i + 1));

            Long platformId = (long) (Math.random() * (platformsCount)) + 1L;
            service.setPlatform(platformRepository.getOne(platformId));

            int users = (int) (Math.random() * 10000 + 2000);
            service.setUsers(users);

            serviceRepository.save(service);
        }
    }
}
