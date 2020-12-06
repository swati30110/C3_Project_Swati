import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants;
    public RestaurantService(){
        restaurants = new ArrayList<>();
    }

    public Restaurant findRestaurantByName(String restaurantName) throws RestaurantNotFoundException{
        /*added changes*/
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName)){
                return restaurant;
            }
        }
        throw new RestaurantNotFoundException("Restaurant Not Found");
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws RestaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if (restaurantToBeRemoved == null)
            throw new RestaurantNotFoundException(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
