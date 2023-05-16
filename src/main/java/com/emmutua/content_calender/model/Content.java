package com.emmutua.content_calender.model;

import java.time.LocalDateTime;

public record Content(
        Integer id,
        String title,
        String desc,
        Status status,
        Type contentType,
        LocalDateTime dataCreated,
        LocalDateTime dataUpdated,
        String url
) {}