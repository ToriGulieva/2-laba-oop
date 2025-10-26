public class TransportUtils {

    //Выводит на экран названия моделей и их цены для заданного транспортного средства.

    public static void printModelNamesWithPrices(Transport transport) {
        System.out.println("================================================");
        String[] modelNames = transport.getAllModelNames();
        int[] prices = transport.getAllPrices();
        System.out.println("Модели и цены:");
        if (modelNames == null || modelNames.length == 0) {
            System.out.println("  Модели отсутствуют.");
            return;
        }
        for (int i = 0; i < modelNames.length; i++) {
            String priceStr = (prices != null && i < prices.length && prices[i] != -1) ? (prices[i] + " руб.") : "нет данных";
            System.out.printf("%d. %s — %s%n", i + 1, modelNames[i], priceStr);
        }
        System.out.println("================================================");
    }

    //Вычисляет среднее арифметическое цен моделей для заданного транспортного средства.

    public static double getAveragePrice(Transport transport) {
        if (transport == null) throw new IllegalArgumentException("Транспортное средство не может быть null");
        int[] prices = transport.getAllPrices();
        if (prices.length == 0) return 0.0;
        long sum = 0;
        int count = 0;
        for (int price : prices) {
            if (price != -1) {
                sum += price;
                count++;
            }
        }
        return count == 0 ? 0.0 : (double) sum / count;
    }
}