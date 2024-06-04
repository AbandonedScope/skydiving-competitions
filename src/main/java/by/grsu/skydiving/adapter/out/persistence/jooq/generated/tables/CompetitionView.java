/*
 * This file is generated by jOOQ.
 */
package generated.tables;


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
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class CompetitionView extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.competition_view</code>
     */
    public static final CompetitionView COMPETITION_VIEW = new CompetitionView();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>public.competition_view.id</code>.
     */
    public final TableField<Record, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.competition_view.name</code>.
     */
    public final TableField<Record, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.competition_view.begin_date</code>.
     */
    public final TableField<Record, LocalDateTime> BEGIN_DATE = createField(DSL.name("begin_date"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>public.competition_view.end_date</code>.
     */
    public final TableField<Record, LocalDateTime> END_DATE = createField(DSL.name("end_date"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>public.competition_view.address</code>.
     */
    public final TableField<Record, String> ADDRESS = createField(DSL.name("address"), SQLDataType.VARCHAR(150), this, "");

    /**
     * The column <code>public.competition_view.status</code>.
     */
    public final TableField<Record, Integer> STATUS = createField(DSL.name("status"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.competition_view.number_of_stages</code>.
     */
    public final TableField<Record, Integer> NUMBER_OF_STAGES = createField(DSL.name("number_of_stages"), SQLDataType.INTEGER, this, "");

    private CompetitionView(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private CompetitionView(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.view("""
        create view "competition_view" as  SELECT competition.id,
          competition.name,
          competition.begin_date,
          competition.end_date,
          competition.address,
          competition.status,
          competition.number_of_stages
         FROM competition
        WHERE (competition.is_deleted = false);
        """));
    }

    /**
     * Create an aliased <code>public.competition_view</code> table reference
     */
    public CompetitionView(String alias) {
        this(DSL.name(alias), COMPETITION_VIEW);
    }

    /**
     * Create an aliased <code>public.competition_view</code> table reference
     */
    public CompetitionView(Name alias) {
        this(alias, COMPETITION_VIEW);
    }

    /**
     * Create a <code>public.competition_view</code> table reference
     */
    public CompetitionView() {
        this(DSL.name("competition_view"), null);
    }

    public <O extends Record> CompetitionView(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, COMPETITION_VIEW);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public CompetitionView as(String alias) {
        return new CompetitionView(DSL.name(alias), this);
    }

    @Override
    public CompetitionView as(Name alias) {
        return new CompetitionView(alias, this);
    }

    @Override
    public CompetitionView as(Table<?> alias) {
        return new CompetitionView(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionView rename(String name) {
        return new CompetitionView(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionView rename(Name name) {
        return new CompetitionView(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionView rename(Table<?> name) {
        return new CompetitionView(name.getQualifiedName(), null);
    }
}
