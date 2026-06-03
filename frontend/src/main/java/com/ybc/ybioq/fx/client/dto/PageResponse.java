package com.ybc.ybioq.fx.client.dto;

import java.util.List;

public record PageResponse<T>(
        List<T> content,
        int totalPages,
        long totalElements,
        int number,
        boolean first,
        boolean last
) {}
