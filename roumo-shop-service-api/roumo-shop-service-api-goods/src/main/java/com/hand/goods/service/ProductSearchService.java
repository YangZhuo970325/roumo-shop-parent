package com.hand.goods.service;

import com.hand.base.BaseResponse;
import com.hand.product.output.dto.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ProductSearchService {

    @GetMapping("/search")
    public BaseResponse<List<ProductDto>> search(String name);
}
