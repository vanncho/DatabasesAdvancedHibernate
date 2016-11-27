package app.domains.dtos;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsListXmlDto implements Serializable {

    @XmlElement(name = "product")
    private List<ProductDto> productDtos;

    public ProductsListXmlDto() {
        this.productDtos = new ArrayList<>();
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }
}
