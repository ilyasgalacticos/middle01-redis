package kz.bitlab.middle01reids.middle01reids.api;


import kz.bitlab.middle01reids.middle01reids.model.Product;
import kz.bitlab.middle01reids.middle01reids.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "id") Long id){
        Product product = productService.getProduct(id);
        return product!=null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

}
