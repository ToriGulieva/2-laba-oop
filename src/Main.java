public class Main {
    public static void main(String[] args) {
        try {
            Transport car = new Motorcycle("Toyota", 2);
            car.addModel("Camry", 2000000);
            car.addModel("Corolla", 1500000);
            car.setPriceForModel("Model_1", 10);

            System.out.println("\nМодели и цены автомобиля " + car.getBrand() + ":");
            TransportUtils.printModelNamesWithPrices(car);


// Пример переименования модели
            car.modifyModelName("Model_1", "Model_5");
            System.out.println("\nПосле удаления модели Camry у автомобиля:");
            TransportUtils.printModelNamesWithPrices(car);


// Выводим среднюю цену моделей для каждого транспортного средства
            System.out.printf("%nСредняя цена моделей автомобиля %s: %.2f%n",
                    car.getBrand(), TransportUtils.getAveragePrice(car));

// Пример удаления модели
            car.removeModel("Camry");
            System.out.println("\nПосле удаления модели Camry у автомобиля:");
            TransportUtils.printModelNamesWithPrices(car);


// Вывод даты последней модификации
          System.out.printf("Последнее изменение: %s%n", car.getLastModified());


        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}