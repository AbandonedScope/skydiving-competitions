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
public class CompetitionStageRefereeTrans extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>public.competition_stage_referee_trans</code>
     */
    public static final CompetitionStageRefereeTrans COMPETITION_STAGE_REFEREE_TRANS = new CompetitionStageRefereeTrans();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>public.competition_stage_referee_trans.id</code>.
     */
    public final TableField<Record, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column
     * <code>public.competition_stage_referee_trans.competition_stage_id</code>.
     */
    public final TableField<Record, Long> COMPETITION_STAGE_ID = createField(DSL.name("competition_stage_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column
     * <code>public.competition_stage_referee_trans.referee_id</code>.
     */
    public final TableField<Record, Long> REFEREE_ID = createField(DSL.name("referee_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column
     * <code>public.competition_stage_referee_trans.work_performed</code>.
     */
    public final TableField<Record, String> WORK_PERFORMED = createField(DSL.name("work_performed"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column
     * <code>public.competition_stage_referee_trans.is_main_collegium</code>.
     */
    public final TableField<Record, Boolean> IS_MAIN_COLLEGIUM = createField(DSL.name("is_main_collegium"), SQLDataType.BOOLEAN.nullable(false), this, "");

    private CompetitionStageRefereeTrans(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private CompetitionStageRefereeTrans(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.competition_stage_referee_trans</code>
     * table reference
     */
    public CompetitionStageRefereeTrans(String alias) {
        this(DSL.name(alias), COMPETITION_STAGE_REFEREE_TRANS);
    }

    /**
     * Create an aliased <code>public.competition_stage_referee_trans</code>
     * table reference
     */
    public CompetitionStageRefereeTrans(Name alias) {
        this(alias, COMPETITION_STAGE_REFEREE_TRANS);
    }

    /**
     * Create a <code>public.competition_stage_referee_trans</code> table
     * reference
     */
    public CompetitionStageRefereeTrans() {
        this(DSL.name("competition_stage_referee_trans"), null);
    }

    public <O extends Record> CompetitionStageRefereeTrans(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, COMPETITION_STAGE_REFEREE_TRANS);
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
        return Keys.COMPETITION_STAGE_REFEREE_TRANS_PKEY;
    }

    @Override
    public List<ForeignKey<Record, ?>> getReferences() {
        return Arrays.asList(Keys.COMPETITION_STAGE_REFEREE_TRANS__COMPETITION_STAGE_REFEREE_TRANS_COMPETITION_STAGE_ID_FKEY, Keys.COMPETITION_STAGE_REFEREE_TRANS__COMPETITION_STAGE_REFEREE_TRANS_REFEREE_ID_FKEY);
    }

    private transient CompetitionStage _competitionStage;
    private transient Referee _referee;

    /**
     * Get the implicit join path to the <code>public.competition_stage</code>
     * table.
     */
    public CompetitionStage competitionStage() {
        if (_competitionStage == null)
            _competitionStage = new CompetitionStage(this, Keys.COMPETITION_STAGE_REFEREE_TRANS__COMPETITION_STAGE_REFEREE_TRANS_COMPETITION_STAGE_ID_FKEY);

        return _competitionStage;
    }

    /**
     * Get the implicit join path to the <code>public.referee</code> table.
     */
    public Referee referee() {
        if (_referee == null)
            _referee = new Referee(this, Keys.COMPETITION_STAGE_REFEREE_TRANS__COMPETITION_STAGE_REFEREE_TRANS_REFEREE_ID_FKEY);

        return _referee;
    }

    @Override
    public CompetitionStageRefereeTrans as(String alias) {
        return new CompetitionStageRefereeTrans(DSL.name(alias), this);
    }

    @Override
    public CompetitionStageRefereeTrans as(Name alias) {
        return new CompetitionStageRefereeTrans(alias, this);
    }

    @Override
    public CompetitionStageRefereeTrans as(Table<?> alias) {
        return new CompetitionStageRefereeTrans(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionStageRefereeTrans rename(String name) {
        return new CompetitionStageRefereeTrans(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionStageRefereeTrans rename(Name name) {
        return new CompetitionStageRefereeTrans(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionStageRefereeTrans rename(Table<?> name) {
        return new CompetitionStageRefereeTrans(name.getQualifiedName(), null);
    }
}
