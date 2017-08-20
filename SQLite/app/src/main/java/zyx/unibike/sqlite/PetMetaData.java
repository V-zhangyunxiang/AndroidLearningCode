package zyx.unibike.sqlite;

import android.provider.BaseColumns;

/**
 * Created by VULCAN on 2017/7/29.
 */

public final class PetMetaData {

    private PetMetaData() {
    }
    //表的定义
    public static abstract class DogTable implements BaseColumns {
        public static final String TABLE_NAME = "dog";
        public static final String NAME = "name";
        public static final String AGE = "age";
    }
}
