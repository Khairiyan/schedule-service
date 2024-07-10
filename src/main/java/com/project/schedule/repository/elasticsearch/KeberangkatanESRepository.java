package com.project.schedule.repository.elasticsearch;

import com.project.schedule.model.elasticsearch.KeberangkatanElasticSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeberangkatanESRepository extends ElasticsearchRepository<KeberangkatanElasticSearch, Integer>{
}
