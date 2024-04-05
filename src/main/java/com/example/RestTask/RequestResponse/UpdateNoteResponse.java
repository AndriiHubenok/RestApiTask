package com.example.RestTask.RequestResponse;

import com.example.RestTask.Entity.Note;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateNoteResponse {
    private Error error;

    private Note updatedNote;

    public enum Error {
        OK,
        INSUFFICIENT_PRIVILEGES,
        INVALID_NOTE_ID,
        INVALID_TITLE_LENGTH,
        INVALID_CONTENT_LENGTH
    }

    public static UpdateNoteResponse success(Note updatedNote) {
        return builder().error(Error.OK).updatedNote(updatedNote).build();
    }

    public static UpdateNoteResponse failed(Error error) {
        return builder().error(error).updatedNote(null).build();
    }
}
