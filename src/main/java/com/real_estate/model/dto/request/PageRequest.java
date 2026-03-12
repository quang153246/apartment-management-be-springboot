package com.real_estate.model.dto.request;

import java.util.List;
import org.springdoc.core.converters.models.Pageable;

public class PageRequest extends Pageable {
    public PageRequest(int page, int size, List<String> sort) {
        super(page, size, sort);
    }
}
