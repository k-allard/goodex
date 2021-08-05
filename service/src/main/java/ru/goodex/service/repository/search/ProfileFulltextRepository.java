package ru.goodex.service.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ru.goodex.service.entity.profile.ProfileDTO;

@Repository
public interface ProfileFulltextRepository extends ElasticsearchRepository<ProfileDTO, String> {
    Page<ProfileDTO> findByFirstName(String firstName, Pageable pageable);
}
