package ru.goodex.service.service.search;

import org.springframework.data.domain.Page;
import ru.goodex.service.entity.profile.ProfileDTO;

public interface SearchService {
    public Page<ProfileDTO> findByFirstName(String firstName);
    public Page<ProfileDTO> findByFirstName(String firstName, int page, int pageSize);
}
