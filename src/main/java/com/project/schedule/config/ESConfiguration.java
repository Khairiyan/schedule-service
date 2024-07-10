package com.project.schedule.config;

import com.project.schedule.config.properties.ESConfigurationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.repository.query.AbstractElasticsearchRepositoryQuery;

import java.io.IOException;
import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class ESConfiguration extends ElasticsearchConfiguration {

    private final ESConfigurationProperties esConfigurationProperties;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(esConfigurationProperties.urisOne(), esConfigurationProperties.urisTwo())
                .withConnectTimeout(Duration.ofSeconds(5))
                .withSocketTimeout(Duration.ofSeconds(3))
                .withBasicAuth(esConfigurationProperties.username(), esConfigurationProperties.password())
                .build();
    }
//
//
//    @Bean
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo(esConfigurationProperties.uris())
//                .build();
//        try (RestHighLevelClient client = RestClients.create(clientConfiguration)
//                .rest() ) {
//            return client;
//        } catch (IOException e){
//            throw new ElasticsearchException(e.getMessage());
//        }
//    } private final ESConfigurationProperties esConfigurationProperties;


//    @Bean
//    public RestHighLevelClient elasticsearchClient(){
//        log.info("username : {}", esConfigurationProperties.username());
//        log.info("password : {}", esConfigurationProperties.password());
//        BasicCredentialsProvider provider = new BasicCredentialsProvider();
//        provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(esConfigurationProperties.username(), esConfigurationProperties.password()));
//
//        RestClient restClient = RestClient.builder(HttpHost.create(esConfigurationProperties.uris()))
//                .setHttpClientConfigCallback(httpAsyncClientBuilder ->
//                        httpAsyncClientBuilder.setDefaultCredentialsProvider(provider))
//                .build();
//
//        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
//
//        return new ElasticsearchClient(transport);
//    }


}