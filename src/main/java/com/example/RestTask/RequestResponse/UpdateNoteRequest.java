package com.example.RestTask.RequestResponse;

import lombok.Data;

@Data
public class UpdateNoteRequest {
    private long id;
    private String title;
    private String content;
}
