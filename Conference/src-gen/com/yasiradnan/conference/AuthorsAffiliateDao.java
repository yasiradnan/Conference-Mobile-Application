package com.yasiradnan.conference;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.SqlUtils;

import com.yasiradnan.conference.AuthorsAffiliate;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table AUTHORS_AFFILIATE.
*/
public class AuthorsAffiliateDao extends AbstractDao<AuthorsAffiliate, Void> {

    public static final String TABLENAME = "AUTHORS_AFFILIATE";

    /**
     * Properties of entity AuthorsAffiliate.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property AbstractauthorId = new Property(0, long.class, "abstractauthorId", false, "ABSTRACTAUTHOR_ID");
        public final static Property AbstractaffiliationId = new Property(1, long.class, "abstractaffiliationId", false, "ABSTRACTAFFILIATION_ID");
    };

    private DaoSession daoSession;


    public AuthorsAffiliateDao(DaoConfig config) {
        super(config);
    }
    
    public AuthorsAffiliateDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'AUTHORS_AFFILIATE' (" + //
                "'ABSTRACTAUTHOR_ID' INTEGER NOT NULL ," + // 0: abstractauthorId
                "'ABSTRACTAFFILIATION_ID' INTEGER NOT NULL );"); // 1: abstractaffiliationId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'AUTHORS_AFFILIATE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, AuthorsAffiliate entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getAbstractauthorId());
        stmt.bindLong(2, entity.getAbstractaffiliationId());
    }

    @Override
    protected void attachEntity(AuthorsAffiliate entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public AuthorsAffiliate readEntity(Cursor cursor, int offset) {
        AuthorsAffiliate entity = new AuthorsAffiliate( //
            cursor.getLong(offset + 0), // abstractauthorId
            cursor.getLong(offset + 1) // abstractaffiliationId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, AuthorsAffiliate entity, int offset) {
        entity.setAbstractauthorId(cursor.getLong(offset + 0));
        entity.setAbstractaffiliationId(cursor.getLong(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(AuthorsAffiliate entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(AuthorsAffiliate entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getAbstractAuthorDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getAbstractAffiliationDao().getAllColumns());
            builder.append(" FROM AUTHORS_AFFILIATE T");
            builder.append(" LEFT JOIN ABSTRACT_AUTHOR T0 ON T.'ABSTRACTAUTHOR_ID'=T0.'_id'");
            builder.append(" LEFT JOIN ABSTRACT_AFFILIATION T1 ON T.'ABSTRACTAFFILIATION_ID'=T1.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected AuthorsAffiliate loadCurrentDeep(Cursor cursor, boolean lock) {
        AuthorsAffiliate entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        AbstractAuthor abstractAuthor = loadCurrentOther(daoSession.getAbstractAuthorDao(), cursor, offset);
         if(abstractAuthor != null) {
            entity.setAbstractAuthor(abstractAuthor);
        }
        offset += daoSession.getAbstractAuthorDao().getAllColumns().length;

        AbstractAffiliation abstractAffiliation = loadCurrentOther(daoSession.getAbstractAffiliationDao(), cursor, offset);
         if(abstractAffiliation != null) {
            entity.setAbstractAffiliation(abstractAffiliation);
        }

        return entity;    
    }

    public AuthorsAffiliate loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<AuthorsAffiliate> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<AuthorsAffiliate> list = new ArrayList<AuthorsAffiliate>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<AuthorsAffiliate> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<AuthorsAffiliate> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}