import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant,restaurantSpy;

    public void createRestaurant(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

    }
    public void createRestaurantSpy(){
        LocalTime openingTime = LocalTime.of(9,00);
        LocalTime closingTime = LocalTime.of(21,00);
        restaurantSpy = Mockito.spy(new Restaurant("KFC","Bhubaneswar",openingTime,closingTime));

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        createRestaurantSpy();
        LocalTime currentTime = LocalTime.of(18,00);
        Mockito.doReturn(currentTime).when(restaurantSpy).getCurrentTime();
        boolean isRestaurantOpen = restaurantSpy.isRestaurantOpen();
        assertTrue(isRestaurantOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        createRestaurantSpy();
        LocalTime currentTime = LocalTime.of(22,00);
        Mockito.doReturn(currentTime).when(restaurantSpy).getCurrentTime();
        boolean isRestaurantOpen = restaurantSpy.isRestaurantOpen();
        assertFalse(isRestaurantOpen);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        createRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws ItemNotFoundException {
        createRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        createRestaurant();
        assertThrows(ItemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    /*TDD
    * For the method to give total amount after selecting the items
    * Failed case scenario will be when item name will be passed when it's not available in the menu*/
    @Test
    public void return_total_amount_of_added_items(){
        createRestaurant();
        int amount = restaurant.getTotalAmount("Chicken soup","Vegetable lasagne");
        assertNotNull(amount);
        assertThat(269,equalTo(amount));
    }
}