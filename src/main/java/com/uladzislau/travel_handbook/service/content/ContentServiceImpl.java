package com.uladzislau.travel_handbook.service.content;

import com.uladzislau.travel_handbook.model.Content;
import com.uladzislau.travel_handbook.repository.BaseRepository;
import com.uladzislau.travel_handbook.repository.ContentRepository;
import com.uladzislau.travel_handbook.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl extends BaseService<Content> implements ContentService {

    @Autowired
    private ContentRepository contentRepository;


    @Override
    protected BaseRepository<Content, Long> relatedRepository() {
        return contentRepository;
    }
}
