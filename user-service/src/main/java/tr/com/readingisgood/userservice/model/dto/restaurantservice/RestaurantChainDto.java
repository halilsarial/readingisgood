package tr.com.readingisgood.userservice.model.dto.restaurantservice;

import tr.com.readingisgood.userservice.model.dto.AbstractServiceDto;

public class RestaurantChainDto extends AbstractServiceDto {
    private String restaurantName;
    private String restaurantChainName;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantChainName() {
        return restaurantChainName;
    }

    public void setRestaurantChainName(String restaurantChainName) {
        this.restaurantChainName = restaurantChainName;
    }
}
