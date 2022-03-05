package ru.voronasever.voronaStore.repositories.elasticsearch;

import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.util.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import ru.voronasever.voronaStore.model.Product;

import java.util.List;


public interface IElasticProductRepo extends ElasticsearchRepository<Product, Integer> {

    Page<Product> findByName(String name, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
    Page<Product> findByNameUsingCustomQuery(String name, Pageable pageable);


}
