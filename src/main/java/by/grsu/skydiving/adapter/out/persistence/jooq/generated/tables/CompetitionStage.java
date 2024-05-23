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
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class CompetitionStage extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.competition_stage</code>
     */
    public static final CompetitionStage COMPETITION_STAGE = new CompetitionStage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>public.competition_stage.id</code>.
     */
    public final TableField<Record, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.competition_stage.competition_id</code>.
     */
    public final TableField<Record, Long> COMPETITION_ID = createField(DSL.name("competition_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.competition_stage.number</code>.
     */
    public final TableField<Record, Integer> NUMBER = createField(DSL.name("number"), SQLDataType.INTEGER.nullable(false), this, "");

    private CompetitionStage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private CompetitionStage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.competition_stage</code> table reference
     */
    public CompetitionStage(String alias) {
        this(DSL.name(alias), COMPETITION_STAGE);
    }

    /**
     * Create an aliased <code>public.competition_stage</code> table reference
     */
    public CompetitionStage(Name alias) {
        this(alias, COMPETITION_STAGE);
    }

    /**
     * Create a <code>public.competition_stage</code> table reference
     */
    public CompetitionStage() {
        this(DSL.name("competition_stage"), null);
    }

    public <O extends Record> CompetitionStage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, COMPETITION_STAGE);
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
        return Keys.COMPETITION_STAGE_PKEY;
    }

    @Override
    public List<ForeignKey<Record, ?>> getReferences() {
        return Arrays.asList(Keys.COMPETITION_STAGE__COMPETITION_STAGE_COMPETITION_ID_FKEY);
    }

    private transient Competition _competition;

    /**
     * Get the implicit join path to the <code>public.competition</code> table.
     */
    public Competition competition() {
        if (_competition == null)
            _competition = new Competition(this, Keys.COMPETITION_STAGE__COMPETITION_STAGE_COMPETITION_ID_FKEY);

        return _competition;
    }

    @Override
    public CompetitionStage as(String alias) {
        return new CompetitionStage(DSL.name(alias), this);
    }

    @Override
    public CompetitionStage as(Name alias) {
        return new CompetitionStage(alias, this);
    }

    @Override
    public CompetitionStage as(Table<?> alias) {
        return new CompetitionStage(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionStage rename(String name) {
        return new CompetitionStage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionStage rename(Name name) {
        return new CompetitionStage(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionStage rename(Table<?> name) {
        return new CompetitionStage(name.getQualifiedName(), null);
    }
}
