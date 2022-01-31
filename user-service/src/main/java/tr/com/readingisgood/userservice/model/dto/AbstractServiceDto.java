package tr.com.readingisgood.userservice.model.dto;


import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public abstract class AbstractServiceDto {

    public HttpEntity<AbstractServiceDto> prepareServiceRequestEntity(AbstractServiceDto serviceDto, HttpHeaders headers) {
        if (headers == null || CollectionUtils.isEmpty(headers.entrySet())) {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        return new HttpEntity<>(serviceDto, headers);
    }
}
