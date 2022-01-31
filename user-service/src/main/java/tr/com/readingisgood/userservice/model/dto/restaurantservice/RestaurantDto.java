package tr.com.readingisgood.userservice.model.dto.restaurantservice;

import tr.com.readingisgood.userservice.model.dto.AbstractServiceDto;

public class RestaurantDto extends AbstractServiceDto {
    private Long id;
    private String name;

    private String ownerUserName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }
}
