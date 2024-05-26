/*
 * This file is generated by jOOQ.
 */
package generated.tables;


import generated.Keys;
import generated.Public;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.ForeignKey;
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
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Skydiver extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.skydiver</code>
     */
    public static final Skydiver SKYDIVER = new Skydiver();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>public.skydiver.id</code>.
     */
    public final TableField<Record, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.skydiver.gender</code>.
     */
    public final TableField<Record, Integer> GENDER = createField(DSL.name("gender"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.skydiver.birth_date</code>.
     */
    public final TableField<Record, LocalDateTime> BIRTH_DATE = createField(DSL.name("birth_date"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>public.skydiver.place_of_birth</code>.
     */
    public final TableField<Record, String> PLACE_OF_BIRTH = createField(DSL.name("place_of_birth"), SQLDataType.VARCHAR(40), this, "");

    /**
     * The column <code>public.skydiver.place_of_work</code>.
     */
    public final TableField<Record, String> PLACE_OF_WORK = createField(DSL.name("place_of_work"), SQLDataType.VARCHAR(40), this, "");

    /**
     * The column <code>public.skydiver.education</code>.
     */
    public final TableField<Record, String> EDUCATION = createField(DSL.name("education"), SQLDataType.VARCHAR(40), this, "");

    /**
     * The column <code>public.skydiver.phone_number</code>.
     */
    public final TableField<Record, String> PHONE_NUMBER = createField(DSL.name("phone_number"), SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>public.skydiver.couch_name</code>.
     */
    public final TableField<Record, String> COUCH_NAME = createField(DSL.name("couch_name"), SQLDataType.VARCHAR(100), this, "");

    /**
     * The column <code>public.skydiver.height</code>.
     */
    public final TableField<Record, Double> HEIGHT = createField(DSL.name("height"), SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>public.skydiver.weight</code>.
     */
    public final TableField<Record, Double> WEIGHT = createField(DSL.name("weight"), SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>public.skydiver.shoe_size</code>.
     */
    public final TableField<Record, Integer> SHOE_SIZE = createField(DSL.name("shoe_size"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.skydiver.jacket_size</code>.
     */
    public final TableField<Record, Integer> JACKET_SIZE = createField(DSL.name("jacket_size"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.skydiver.pants_size</code>.
     */
    public final TableField<Record, Integer> PANTS_SIZE = createField(DSL.name("pants_size"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.skydiver.begin_of_sport_career</code>.
     */
    public final TableField<Record, LocalDateTime> BEGIN_OF_SPORT_CAREER = createField(DSL.name("begin_of_sport_career"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>public.skydiver.sport_specialization</code>.
     */
    public final TableField<Record, String> SPORT_SPECIALIZATION = createField(DSL.name("sport_specialization"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.skydiver.sport_degree</code>.
     */
    public final TableField<Record, Integer> SPORT_DEGREE = createField(DSL.name("sport_degree"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.skydiver.jumping_amount</code>.
     */
    public final TableField<Record, Integer> JUMPING_AMOUNT = createField(DSL.name("jumping_amount"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field(DSL.raw("0"), SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.skydiver.is_deleted</code>.
     */
    public final TableField<Record, Boolean> IS_DELETED = createField(DSL.name("is_deleted"), SQLDataType.BOOLEAN.defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.skydiver.is_internal</code>.
     */
    public final TableField<Record, Boolean> IS_INTERNAL = createField(DSL.name("is_internal"), SQLDataType.BOOLEAN.defaultValue(DSL.field(DSL.raw("true"), SQLDataType.BOOLEAN)), this, "");

    private Skydiver(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Skydiver(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.skydiver</code> table reference
     */
    public Skydiver(String alias) {
        this(DSL.name(alias), SKYDIVER);
    }

    /**
     * Create an aliased <code>public.skydiver</code> table reference
     */
    public Skydiver(Name alias) {
        this(alias, SKYDIVER);
    }

    /**
     * Create a <code>public.skydiver</code> table reference
     */
    public Skydiver() {
        this(DSL.name("skydiver"), null);
    }

    public <O extends Record> Skydiver(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SKYDIVER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.SKYDIVER_PKEY;
    }

    @Override
    public Skydiver as(String alias) {
        return new Skydiver(DSL.name(alias), this);
    }

    @Override
    public Skydiver as(Name alias) {
        return new Skydiver(alias, this);
    }

    @Override
    public Skydiver as(Table<?> alias) {
        return new Skydiver(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Skydiver rename(String name) {
        return new Skydiver(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Skydiver rename(Name name) {
        return new Skydiver(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Skydiver rename(Table<?> name) {
        return new Skydiver(name.getQualifiedName(), null);
    }
}
