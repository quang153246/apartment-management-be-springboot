package com.real_estate.model.dto.request;

import org.springdoc.core.converters.models.Pageable;

import java.util.List;

public class PageRequest  extends Pageable {
    public PageRequest(int page, int size, List<String> sort) {
        super(page, size, sort);
    }
}
