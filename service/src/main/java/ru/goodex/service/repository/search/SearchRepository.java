package ru.goodex.service.repository.search;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.search.SearchHit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ru.goodex.service.entity.profile.ProfileDTO;


public class SearchRepository {
    SearchRequest searchRequest = new SearchRequest();
    SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
    SearchHit[] searchHits = response.getHits().getHits();
    List<ProfileDTO> results =
            Arrays.stream(searchHits)
                    .map(hit -> JSON.parseObject(hit.getSourceAsString(), ProfileDTO.class))
                    .collect(Collectors.toList());
}
