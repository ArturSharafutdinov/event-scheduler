package ru.ivanov.evgeny.eventscheduler.config;

import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.generator.Generator;
import ru.ivanov.evgeny.eventscheduler.persistence.common.DiscreteRandomValue;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.CinemaRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.CityRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Cinema;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.City;

import java.util.List;

@Component
public class StartupListener implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private Generator generator;

    private String CINEMA_NAME = "Кинотеатр ";
    private String CITY_NAME = "Город ";

    private int POP_MIN = 1000;
    private int POP_MAX = 2500;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (cinemaRepository.count() == 0 || cityRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                String cityName = CITY_NAME + i;
                Integer population = POP_MIN + (int) (Math.random() * POP_MAX);

                City city = new City();
                city.setName(cityName);
                city.setPopulation(population);
                cityRepository.saveAndFlush(city);
            }

            List<City> cities = cityRepository.findAll();
            int population = cities.stream().mapToInt(City::getPopulation).sum();
            int cinemasCount = 20;
            int index = 0;

            for (City city : cities) {
                Integer cinemasInCity = (int) (Math.ceil(cinemasCount * ((double)city.getPopulation() / population)));

                for (int i = 0; i < cinemasInCity; i++) {
                    String cinemaName = CINEMA_NAME + (i + index);
                    Cinema cinema = new Cinema();
                    cinema.setName(cinemaName);
                    cinema.setCity(city);
                    cinemaRepository.saveAndFlush(cinema);
                }
                index+=cinemasInCity;
            }
        }

        generator.generate();


    }
}
