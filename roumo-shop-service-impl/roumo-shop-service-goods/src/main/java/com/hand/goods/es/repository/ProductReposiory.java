package com.hand.goods.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.hand.goods.es.entity.ProductEntity;

public interface ProductReposiory extends ElasticsearchRepository<ProductEntity, Long> {

}
 