package ru.goodex.service.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "ru.goodex.service.repository.search")
@ComponentScan(basePackages = { "ru.goodex.service.service.search" })
public class ElasticsearchConfig {


    @Value("${spring.es.hostandport}")
    private String hostandport;

    @Value("${spring.es.username}")
    private String username;

    @Value("${spring.es.password}")
    private String password;

    @Bean
    RestHighLevelClient client() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(hostandport)
                .withBasicAuth(username, password)
                .build();

        return RestClients.create(clientConfiguration)
                .rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }
}