package com.uladzislau.travel_handbook.service.content;

import com.uladzislau.travel_handbook.dto.ContentDto;
import com.uladzislau.travel_handbook.exception.ResourceNotFoundException;
import com.uladzislau.travel_handbook.model.City;
import com.uladzislau.travel_handbook.model.Content;
import com.uladzislau.travel_handbook.repository.BaseRepository;
import com.uladzislau.travel_handbook.repository.ContentRepository;
import com.uladzislau.travel_handbook.service.BaseService;
import com.uladzislau.travel_handbook.service.city.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl extends BaseService<Content> implements ContentService {

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private CityService cityService;


    @Override
    protected BaseRepository<Content, Long> relatedRepository() {
        return contentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentDto> readByCityId(long cityId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentDto> readAllByCityName(String cityName) {
        return contentRepository.findAllByCityName(cityName).stream()
                .map(content -> ContentDto.builder()
                        .id(content.getId())
                        .text(content.getText())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void create(ContentDto contentDto) {
        Long cityId = contentDto.getCityId();
        if (cityId == null) {
            throw new RuntimeException("Field cityId must be non null");
        }
        City city = cityService.findById(cityId).orElseThrow(
                () -> new ResourceNotFoundException("City with id " + cityId + " wasn't found."));
        Content content = Content.builder()
                .city(city)
                .text(contentDto.getText())
                .build();

        save(content);
    }

    @Override
    @Transactional
    public ContentDto update(long id, ContentDto contentDto) {
        Content content = findByIdOrElseThrow(id);
        City city = cityService.findById(contentDto.getCityId()).orElseThrow(
                () -> new ResourceNotFoundException("City with id " + contentDto.getCityId() + " wasn't found."));
        content.setText(contentDto.getText());
        content.setCity(city);

        save(content);
        contentDto.setId(id);
        return contentDto;
    }

    @Override
    @Transactional
    public void delete(long id) {
        delete(findByIdOrElseThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public ContentDto readOneByCityName(String city) {
        List<ContentDto> content = readAllByCityName(city);
        if (!content.isEmpty()) {
            return content.get(new Random().nextInt(content.size()));
        }
        return null;
    }
}
