package tr.com.readingisgood.userservice.model.dto.restaurantservice;

import tr.com.readingisgood.userservice.model.dto.AbstractServiceDto;

public class MenuDto extends AbstractServiceDto {
    private Long id;
    private String name;

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
}
