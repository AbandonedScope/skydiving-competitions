/*
 * This file is generated by jOOQ.
 */
package generated.tables;


import generated.Keys;
import generated.Public;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PassportInfo extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.passport_info</code>
     */
    public static final PassportInfo PASSPORT_INFO = new PassportInfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>public.passport_info.id</code>.
     */
    public final TableField<Record, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.passport_info.skydiver_id</code>.
     */
    public final TableField<Record, Long> SKYDIVER_ID = createField(DSL.name("skydiver_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.passport_info.series</code>.
     */
    public final TableField<Record, String> SERIES = createField(DSL.name("series"), SQLDataType.CHAR(2).nullable(false), this, "");

    /**
     * The column <code>public.passport_info.number</code>.
     */
    public final TableField<Record, String> NUMBER = createField(DSL.name("number"), SQLDataType.CHAR(7).nullable(false), this, "");

    /**
     * The column <code>public.passport_info.personal_number</code>.
     */
    public final TableField<Record, String> PERSONAL_NUMBER = createField(DSL.name("personal_number"), SQLDataType.CHAR(14).nullable(false), this, "");

    /**
     * The column <code>public.passport_info.issuing_authority</code>.
     */
    public final TableField<Record, String> ISSUING_AUTHORITY = createField(DSL.name("issuing_authority"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.passport_info.issued_date</code>.
     */
    public final TableField<Record, LocalDateTime> ISSUED_DATE = createField(DSL.name("issued_date"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    private PassportInfo(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private PassportInfo(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.passport_info</code> table reference
     */
    public PassportInfo(String alias) {
        this(DSL.name(alias), PASSPORT_INFO);
    }

    /**
     * Create an aliased <code>public.passport_info</code> table reference
     */
    public PassportInfo(Name alias) {
        this(alias, PASSPORT_INFO);
    }

    /**
     * Create a <code>public.passport_info</code> table reference
     */
    public PassportInfo() {
        this(DSL.name("passport_info"), null);
    }

    public <O extends Record> PassportInfo(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, PASSPORT_INFO);
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
        return Keys.PASSPORT_INFO_PKEY;
    }

    @Override
    public PassportInfo as(String alias) {
        return new PassportInfo(DSL.name(alias), this);
    }

    @Override
    public PassportInfo as(Name alias) {
        return new PassportInfo(alias, this);
    }

    @Override
    public PassportInfo as(Table<?> alias) {
        return new PassportInfo(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public PassportInfo rename(String name) {
        return new PassportInfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PassportInfo rename(Name name) {
        return new PassportInfo(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public PassportInfo rename(Table<?> name) {
        return new PassportInfo(name.getQualifiedName(), null);
    }
}
