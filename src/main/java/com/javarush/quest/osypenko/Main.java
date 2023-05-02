package com.javarush.quest.osypenko;

import com.javarush.quest.osypenko.entity.City;
import com.javarush.quest.osypenko.redis.CityCountry;
import com.javarush.quest.osypenko.service.Service;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        List<City> allCities = service.fetchData(service);
        List<CityCountry> preparedData = service.transformData(allCities);
        service.pushToRedis(preparedData);

        //закроем текущую сессию, чтоб точно делать запрос к БД, а не вытянуть данные из кеша
        service.sessionFactory.getCurrentSession().close();

        //выбираем случайных 10 id городов
        //так как мы не делали обработку невалидных ситуаций, используй существующие в БД id
        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        service.testRedisData(ids);
        long stopRedis = System.currentTimeMillis();

        long startMySql = System.currentTimeMillis();
        service.testMysqlData(ids);
        long stopMySql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMySql - startMySql));

        service.shutdown();
    }
}
