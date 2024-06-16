/*
 * This file is generated by jOOQ.
 */
package generated.tables;


import generated.Keys;
import generated.Public;
import java.time.LocalDateTime;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Competition extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.competition</code>
     */
    public static final Competition COMPETITION = new Competition();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>public.competition.id</code>.
     */
    public final TableField<Record, Long> ID =
        createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.competition.name</code>.
     */
    public final TableField<Record, String> NAME =
        createField(DSL.name("name"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>public.competition.begin_date</code>.
     */
    public final TableField<Record, LocalDateTime> BEGIN_DATE =
        createField(DSL.name("begin_date"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>public.competition.end_date</code>.
     */
    public final TableField<Record, LocalDateTime> END_DATE =
        createField(DSL.name("end_date"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>public.competition.address</code>.
     */
    public final TableField<Record, String> ADDRESS =
        createField(DSL.name("address"), SQLDataType.VARCHAR(150).nullable(false), this, "");

    /**
     * The column <code>public.competition.status</code>.
     */
    public final TableField<Record, Integer> STATUS =
        createField(DSL.name("status"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.competition.is_deleted</code>.
     */
    public final TableField<Record, Boolean> IS_DELETED = createField(DSL.name("is_deleted"),
        SQLDataType.BOOLEAN.defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), this, "");

    private Competition(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Competition(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.competition</code> table reference
     */
    public Competition(String alias) {
        this(DSL.name(alias), COMPETITION);
    }

    /**
     * Create an aliased <code>public.competition</code> table reference
     */
    public Competition(Name alias) {
        this(alias, COMPETITION);
    }

    /**
     * Create a <code>public.competition</code> table reference
     */
    public Competition() {
        this(DSL.name("competition"), null);
    }

    public <O extends Record> Competition(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, COMPETITION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<Record, Long> getIdentity() {
        return (Identity<Record, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.COMPETITION_PKEY;
    }

    @Override
    public Competition as(String alias) {
        return new Competition(DSL.name(alias), this);
    }

    @Override
    public Competition as(Name alias) {
        return new Competition(alias, this);
    }

    @Override
    public Competition as(Table<?> alias) {
        return new Competition(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Competition rename(String name) {
        return new Competition(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Competition rename(Name name) {
        return new Competition(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Competition rename(Table<?> name) {
        return new Competition(name.getQualifiedName(), null);
    }
}
