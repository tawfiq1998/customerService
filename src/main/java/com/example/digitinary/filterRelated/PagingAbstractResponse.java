package com.example.digitinary.filterRelated;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PagingAbstractResponse<T> {
    private int numberOfPages;
    private long totalElements;
    private List<T> data;
}
