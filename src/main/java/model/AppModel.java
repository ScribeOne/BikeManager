package model;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppModel {

  private List<Bike> bikes;

  public AppModel() {
    createTestData();
  }

  private void createTestData() {
    bikes = Arrays.asList(
        new Bike("Rotwild", "X2", "erasmus", 26),
        new Bike("Trek", "Fuel 2.0", "private", 26),
        new Bike("Peugeot", "Milano", "erasmus", 28),
        new Bike("Specialized", "Stumpjumper", "own", 26),
        new Bike("Diamondback","Tour","erasmus",28)
    );
  }

  public List<String> getCategories() {
    return bikes.stream()
        .filter(distinctByKey(p -> p.getCategory()))
        .map(m -> m.getCategory())
        .collect(Collectors.toList());
  }

  private <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
    Map<Object, Boolean> map = new ConcurrentHashMap<>();
    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

  public List<Bike> getBikes() {
    return bikes;
  }
}
