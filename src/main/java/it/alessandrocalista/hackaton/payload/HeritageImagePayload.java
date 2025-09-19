package it.alessandrocalista.hackaton.payload;

public interface HeritageImagePayload {

    record ImportPart(
            String date,
            String dateAccessed,
            String author,
            String copyright,
            String license,
            String source,
            String link
    ) {

    }
}
