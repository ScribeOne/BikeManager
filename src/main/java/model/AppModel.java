package model;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppModel {

  private List<Bike> bikes;

  public AppModel() {
    bikes = new LinkedList<>();
  }


  public void setBikes(List<Bike> bikes) {
    this.bikes = bikes;
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
