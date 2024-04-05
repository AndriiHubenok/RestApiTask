package com.example.RestTask.RequestResponse;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeleteNoteResponse {
    private Error error;

    public enum Error {
        OK,
        INSUFFICIENT_PRIVILEGES,
        INVALID_NOTE_ID
    }

    public static DeleteNoteResponse success() {
        return builder().error(Error.OK).build();
    }

    public static DeleteNoteResponse failed(Error error) {
        return builder().error(error).build();
    }
}
