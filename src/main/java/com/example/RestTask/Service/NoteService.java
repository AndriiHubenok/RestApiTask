package com.example.RestTask.Service;

import com.example.RestTask.Entity.Note;
import com.example.RestTask.Repository.NoteRepository;
import com.example.RestTask.RequestResponse.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_CONTENT_LENGTH = 1000;

    private final NoteRepository repository;

    public CreateNoteResponse create(CreateNoteRequest request) {
        Optional<CreateNoteResponse.Error> validationError = validateCreateFields(request);

        if (validationError.isPresent()) {
            return CreateNoteResponse.failed(validationError.get());
        }

        Note createdNote = repository.save(Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build());

        return CreateNoteResponse.success(createdNote.getId());
    }

    public GetNoteResponse getNote(Long id) {
        Optional<Note> optionalNote = repository.findById(id);

        if (optionalNote.isEmpty()) {
            return GetNoteResponse.failed(GetNoteResponse.Error.INVALID_NOTE_ID);
        }

        Note note = optionalNote.get();

        repository.getNote(note.getId());

        return GetNoteResponse.success(note);
    }

    public UpdateNoteResponse update(UpdateNoteRequest request) {
        Optional<Note> optionalNote = repository.findById(request.getId());

        if (optionalNote.isEmpty()) {
            return UpdateNoteResponse.failed(UpdateNoteResponse.Error.INVALID_NOTE_ID);
        }

        Note note = optionalNote.get();

        Optional<UpdateNoteResponse.Error> validationError = validateUpdateFields(request);

        if (validationError.isPresent()) {
            return UpdateNoteResponse.failed(validationError.get());
        }

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        repository.save(note);

        return UpdateNoteResponse.success(note);
    }

    public DeleteNoteResponse delete(long id) {
        Optional<Note> optionalNote = repository.findById(id);

        if (optionalNote.isEmpty()) {
            return DeleteNoteResponse.failed(DeleteNoteResponse.Error.INVALID_NOTE_ID);
        }

        Note note = optionalNote.get();

        repository.delete(note);

        return DeleteNoteResponse.success();
    }

    private Optional<CreateNoteResponse.Error> validateCreateFields(CreateNoteRequest request) {
        if (Objects.isNull(request.getTitle()) || request.getTitle().length() > MAX_TITLE_LENGTH) {
            return Optional.of(CreateNoteResponse.Error.INVALID_TITLE);
        }

        if (Objects.isNull(request.getContent()) || request.getContent().length() > MAX_CONTENT_LENGTH) {
            return Optional.of(CreateNoteResponse.Error.INVALID_TITLE);
        }

        return Optional.empty();
    }

    private Optional<UpdateNoteResponse.Error> validateUpdateFields(UpdateNoteRequest request) {
        if (Objects.nonNull(request.getTitle()) && request.getTitle().length() > MAX_TITLE_LENGTH) {
            return Optional.of(UpdateNoteResponse.Error.INVALID_TITLE_LENGTH);
        }

        if (Objects.nonNull(request.getContent()) && request.getContent().length() > MAX_CONTENT_LENGTH) {
            return Optional.of(UpdateNoteResponse.Error.INVALID_TITLE_LENGTH);
        }

        return Optional.empty();
    }
}
