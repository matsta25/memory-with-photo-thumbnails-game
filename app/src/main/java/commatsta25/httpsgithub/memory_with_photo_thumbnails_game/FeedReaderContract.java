package commatsta25.httpsgithub.memory_with_photo_thumbnails_game;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "paths";
        public static final String COLUMN_NAME_PATH = "path";
    }
}
