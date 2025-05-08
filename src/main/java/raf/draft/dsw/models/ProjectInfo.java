package raf.draft.dsw.models;

import lombok.NonNull;

public record ProjectInfo(@NonNull String author,
                          @NonNull String name,
                          @NonNull String path) {}
