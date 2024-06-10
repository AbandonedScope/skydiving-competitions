/*
 * This file is generated by jOOQ.
 */
package generated.tables;


import generated.Keys;
import generated.Public;

import java.util.Arrays;
import java.util.List;

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
public class TrickAttempt extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.trick_attempt</code>
     */
    public static final TrickAttempt TRICK_ATTEMPT = new TrickAttempt();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>public.trick_attempt.id</code>.
     */
    public final TableField<Record, Long> ID =
        createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.trick_attempt.trick_serie_id</code>.
     */
    public final TableField<Record, Long> TRICK_SERIE_ID =
        createField(DSL.name("trick_serie_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.trick_attempt.trick_type</code>.
     */
    public final TableField<Record, Integer> TRICK_TYPE =
        createField(DSL.name("trick_type"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.trick_attempt.arrow_penalty</code>.
     */
    public final TableField<Record, Integer> ARROW_PENALTY =
        createField(DSL.name("arrow_penalty"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.trick_attempt.d_penalty</code>.
     */
    public final TableField<Record, Integer> D_PENALTY =
        createField(DSL.name("d_penalty"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.trick_attempt.s_penalty</code>.
     */
    public final TableField<Record, Integer> S_PENALTY =
        createField(DSL.name("s_penalty"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.trick_attempt.minus_penalty</code>.
     */
    public final TableField<Record, Integer> MINUS_PENALTY =
        createField(DSL.name("minus_penalty"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.trick_attempt.plus_minus_penalty</code>.
     */
    public final TableField<Record, Integer> PLUS_MINUS_PENALTY =
        createField(DSL.name("plus_minus_penalty"), SQLDataType.INTEGER, this, "");

    private TrickAttempt(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private TrickAttempt(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.trick_attempt</code> table reference
     */
    public TrickAttempt(String alias) {
        this(DSL.name(alias), TRICK_ATTEMPT);
    }

    /**
     * Create an aliased <code>public.trick_attempt</code> table reference
     */
    public TrickAttempt(Name alias) {
        this(alias, TRICK_ATTEMPT);
    }

    /**
     * Create a <code>public.trick_attempt</code> table reference
     */
    public TrickAttempt() {
        this(DSL.name("trick_attempt"), null);
    }

    public <O extends Record> TrickAttempt(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, TRICK_ATTEMPT);
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
        return Keys.TRICK_ATTEMPT_PKEY;
    }

    @Override
    public List<ForeignKey<Record, ?>> getReferences() {
        return Arrays.asList(Keys.TRICK_ATTEMPT__TRICK_ATTEMPT_TRICK_SERIE_ID_FKEY);
    }

    private transient TrickSerie _trickSerie;

    /**
     * Get the implicit join path to the <code>public.trick_serie</code> table.
     */
    public TrickSerie trickSerie() {
        if (_trickSerie == null)
            _trickSerie = new TrickSerie(this, Keys.TRICK_ATTEMPT__TRICK_ATTEMPT_TRICK_SERIE_ID_FKEY);

        return _trickSerie;
    }

    @Override
    public TrickAttempt as(String alias) {
        return new TrickAttempt(DSL.name(alias), this);
    }

    @Override
    public TrickAttempt as(Name alias) {
        return new TrickAttempt(alias, this);
    }

    @Override
    public TrickAttempt as(Table<?> alias) {
        return new TrickAttempt(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public TrickAttempt rename(String name) {
        return new TrickAttempt(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TrickAttempt rename(Name name) {
        return new TrickAttempt(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public TrickAttempt rename(Table<?> name) {
        return new TrickAttempt(name.getQualifiedName(), null);
    }
}
