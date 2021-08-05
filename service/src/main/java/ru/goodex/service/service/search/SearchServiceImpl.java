package ru.goodex.service.service.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.goodex.service.entity.profile.ProfileDTO;
import ru.goodex.service.repository.search.ProfileFulltextRepository;

@Service
public class SearchServiceImpl implements SearchService
{
    private final ProfileFulltextRepository profileFulltextRepository;

    @Autowired
    SearchServiceImpl(ProfileFulltextRepository profileFulltextRepository)
    {
        this.profileFulltextRepository = profileFulltextRepository;
    }

    @Override
    public Page<ProfileDTO> findByFirstName(String firstName)
    {
        return findByFirstName(firstName, 0, 10);
    }

    @Override
    public Page<ProfileDTO> findByFirstName(String firstName, int page, int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        return profileFulltextRepository.findByFirstName(firstName, pageable);
    }
}
