package com.example.RestTask.Controller;

import com.example.RestTask.RequestResponse.*;
import com.example.RestTask.Service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public CreateNoteResponse create(@RequestBody CreateNoteRequest request) {
        return noteService.create(request);
    }

    @GetMapping
    public GetNoteResponse getNote(@RequestParam(name = "id") Long id) {
        return noteService.getNote(id);
    }

    @PatchMapping
    public UpdateNoteResponse update(@RequestBody UpdateNoteRequest request) {
        return noteService.update(request);
    }

    @DeleteMapping
    public DeleteNoteResponse delete(@RequestParam(name = "id") long id) {
        return noteService.delete(id);
    }
}
