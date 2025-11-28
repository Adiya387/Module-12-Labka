package module12Laba;
//Проверка наличия товаров на складе
import java.util.*;

public class StockChecker {
    public static CheckResult checkAvailability(
            Map<String, Integer> orderItems,
            Map<String, Integer> warehouseStock
    ) {

        List<String> missingItems = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : orderItems.entrySet()) {
            String itemId = entry.getKey();
            int requiredQty = entry.getValue();

            int availableQty = warehouseStock.getOrDefault(itemId, 0);

            if (availableQty < requiredQty) {
                missingItems.add(itemId);
            }
        }

        boolean allAvailable = missingItems.isEmpty();
        return new CheckResult(allAvailable, missingItems);
    }


    public static void main(String[] args) {

        Map<String, Integer> order = new HashMap<>();
        order.put("A001", 2);
        order.put("A002", 1);
        order.put("A003", 3);

        Map<String, Integer> stock = new HashMap<>();
        stock.put("A001", 5);
        stock.put("A002", 0);
        stock.put("A003", 10);

        CheckResult result = checkAvailability(order, stock);

        if (result.allAvailable) {
            System.out.println("Все товары в наличии. Можно подтверждать заказ.");
        } else {
            System.out.println("Недоступные товары: " + result.missingItems);
            System.out.println("Система отправляет клиенту уведомление об изменении заказа.");
        }
    }
}

class CheckResult {
    boolean allAvailable;
    List<String> missingItems;

    public CheckResult(boolean allAvailable, List<String> missingItems) {
        this.allAvailable = allAvailable;
        this.missingItems = missingItems;
    }
}
