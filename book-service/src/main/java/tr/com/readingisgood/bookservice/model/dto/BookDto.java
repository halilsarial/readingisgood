package tr.com.readingisgood.bookservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(name = "Book model documentation", description = "The object keeps book informations")
public class BookDto {

    @Schema(name = "ID field of book object.")
    private String id;

    @Schema(name = "Creation Time field of book object.")
    private Date createTime;

    @Schema(name = "Updating Time field of book object.")
    private Date updateTime;

    @Schema(name = "Version field of book object.")
    private Long version;

    @Schema(name = "Updating user's ID field of book object.")
    private String updatedUserId;

    @Schema(name = "Name field of book object.")
    private String name;

    @Schema(name = "Author name surname field of book object.")
    private String author;

    @Schema(name = "Type field of book object.")
    private String type;

    @Schema(name = "Price field of book object.")
    private double price;

    @Schema(name = "Stock count field of book object.")
    private long stock;

    @Schema(name = "Description field of book object.")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
