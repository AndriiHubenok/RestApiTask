package com.example.RestTask.RequestResponse;

import lombok.Data;

@Data
public class CreateNoteRequest {
    private String title;
    private String content;
}
