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
public class Team extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.team</code>
     */
    public static final Team TEAM = new Team();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>public.team.id</code>.
     */
    public final TableField<Record, Long> ID =
        createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.team.name</code>.
     */
    public final TableField<Record, String> NAME =
        createField(DSL.name("name"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.team.is_deleted</code>.
     */
    public final TableField<Record, Boolean> IS_DELETED = createField(DSL.name("is_deleted"),
        SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), this, "");

    private Team(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Team(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.team</code> table reference
     */
    public Team(String alias) {
        this(DSL.name(alias), TEAM);
    }

    /**
     * Create an aliased <code>public.team</code> table reference
     */
    public Team(Name alias) {
        this(alias, TEAM);
    }

    /**
     * Create a <code>public.team</code> table reference
     */
    public Team() {
        this(DSL.name("team"), null);
    }

    public <O extends Record> Team(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, TEAM);
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
        return Keys.TEAM_PKEY;
    }

    @Override
    public List<UniqueKey<Record>> getUniqueKeys() {
        return Arrays.asList(Keys.TEAM_NAME_KEY);
    }

    @Override
    public Team as(String alias) {
        return new Team(DSL.name(alias), this);
    }

    @Override
    public Team as(Name alias) {
        return new Team(alias, this);
    }

    @Override
    public Team as(Table<?> alias) {
        return new Team(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Team rename(String name) {
        return new Team(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Team rename(Name name) {
        return new Team(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Team rename(Table<?> name) {
        return new Team(name.getQualifiedName(), null);
    }
}
