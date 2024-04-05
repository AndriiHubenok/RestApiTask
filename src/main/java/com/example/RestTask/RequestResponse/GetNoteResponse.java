package com.example.RestTask.RequestResponse;

import com.example.RestTask.Entity.Note;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetNoteResponse {
    private Error error;

    private Note note;

    public enum Error {
        OK,
        INVALID_NOTE_ID
    }

    public static GetNoteResponse success(Note note) {
        return builder().error(Error.OK).note(note).build();
    }

    public static GetNoteResponse failed(Error error) {
        return builder().error(error).note(null).build();
    }
}
