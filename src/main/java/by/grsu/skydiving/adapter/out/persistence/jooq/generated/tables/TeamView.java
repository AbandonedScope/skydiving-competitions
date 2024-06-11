/*
 * This file is generated by jOOQ.
 */
package generated.tables;


import generated.Public;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class TeamView extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.team_view</code>
     */
    public static final TeamView TEAM_VIEW = new TeamView();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>public.team_view.id</code>.
     */
    public final TableField<Record, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.team_view.name</code>.
     */
    public final TableField<Record, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100), this, "");

    private TeamView(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private TeamView(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.view("""
            create view "team_view" as  SELECT id,
              name
             FROM team
            WHERE (is_deleted IS FALSE);
            """));
    }

    /**
     * Create an aliased <code>public.team_view</code> table reference
     */
    public TeamView(String alias) {
        this(DSL.name(alias), TEAM_VIEW);
    }

    /**
     * Create an aliased <code>public.team_view</code> table reference
     */
    public TeamView(Name alias) {
        this(alias, TEAM_VIEW);
    }

    /**
     * Create a <code>public.team_view</code> table reference
     */
    public TeamView() {
        this(DSL.name("team_view"), null);
    }

    public <O extends Record> TeamView(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, TEAM_VIEW);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public TeamView as(String alias) {
        return new TeamView(DSL.name(alias), this);
    }

    @Override
    public TeamView as(Name alias) {
        return new TeamView(alias, this);
    }

    @Override
    public TeamView as(Table<?> alias) {
        return new TeamView(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public TeamView rename(String name) {
        return new TeamView(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TeamView rename(Name name) {
        return new TeamView(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public TeamView rename(Table<?> name) {
        return new TeamView(name.getQualifiedName(), null);
    }
}
