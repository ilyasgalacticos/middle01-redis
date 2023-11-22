package kz.bitlab.middle01reids.middle01reids.service;

import kz.bitlab.middle01reids.middle01reids.model.Product;
import kz.bitlab.middle01reids.middle01reids.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CacheService cacheService;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        final String cacheKey = "product:" + id;
        Product cachedProduct = (Product) cacheService.getCachedObject(cacheKey);
        if (cachedProduct != null) {
            return cachedProduct;
        }

        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(p -> cacheService.cacheObject(cacheKey, p, 1, TimeUnit.MINUTES));

        return product.orElse(null);
    }


}