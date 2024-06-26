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
public class CompetitionCollegiumRefereeTrans extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>public.competition_collegium_referee_trans</code>
     */
    public static final CompetitionCollegiumRefereeTrans COMPETITION_COLLEGIUM_REFEREE_TRANS =
        new CompetitionCollegiumRefereeTrans();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>public.competition_collegium_referee_trans.id</code>.
     */
    public final TableField<Record, Long> ID =
        createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column
     * <code>public.competition_collegium_referee_trans.competition_collegium_id</code>.
     */
    public final TableField<Record, Long> COMPETITION_COLLEGIUM_ID =
        createField(DSL.name("competition_collegium_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column
     * <code>public.competition_collegium_referee_trans.referee_id</code>.
     */
    public final TableField<Record, Long> REFEREE_ID =
        createField(DSL.name("referee_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column
     * <code>public.competition_collegium_referee_trans.work_performed</code>.
     */
    public final TableField<Record, String> WORK_PERFORMED =
        createField(DSL.name("work_performed"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column
     * <code>public.competition_collegium_referee_trans.is_main_collegium</code>.
     */
    public final TableField<Record, Boolean> IS_MAIN_COLLEGIUM =
        createField(DSL.name("is_main_collegium"), SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column
     * <code>public.competition_collegium_referee_trans.referee_number</code>.
     */
    public final TableField<Record, Integer> REFEREE_NUMBER =
        createField(DSL.name("referee_number"), SQLDataType.INTEGER.nullable(false), this, "");

    private CompetitionCollegiumRefereeTrans(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private CompetitionCollegiumRefereeTrans(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.competition_collegium_referee_trans</code>
     * table reference
     */
    public CompetitionCollegiumRefereeTrans(String alias) {
        this(DSL.name(alias), COMPETITION_COLLEGIUM_REFEREE_TRANS);
    }

    /**
     * Create an aliased <code>public.competition_collegium_referee_trans</code>
     * table reference
     */
    public CompetitionCollegiumRefereeTrans(Name alias) {
        this(alias, COMPETITION_COLLEGIUM_REFEREE_TRANS);
    }

    /**
     * Create a <code>public.competition_collegium_referee_trans</code> table
     * reference
     */
    public CompetitionCollegiumRefereeTrans() {
        this(DSL.name("competition_collegium_referee_trans"), null);
    }

    public <O extends Record> CompetitionCollegiumRefereeTrans(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, COMPETITION_COLLEGIUM_REFEREE_TRANS);
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
        return Keys.COMPETITION_COLLEGIUM_REFEREE_TRANS_PK;
    }

    @Override
    public List<ForeignKey<Record, ?>> getReferences() {
        return Arrays.asList(
            Keys.COMPETITION_COLLEGIUM_REFEREE_TRANS__COMPETITION_COLLEGIUM_REFEREE_TRANS_COMPETITION_COLLEGIUM_ID_FK,
            Keys.COMPETITION_COLLEGIUM_REFEREE_TRANS__COMPETITION_COLLEGIUM_REFEREE_TRANS_REFEREE_ID_FK);
    }

    private transient CompetitionCollegium _competitionCollegium;
    private transient Referee _referee;

    /**
     * Get the implicit join path to the
     * <code>public.competition_collegium</code> table.
     */
    public CompetitionCollegium competitionCollegium() {
        if (_competitionCollegium == null) {
            _competitionCollegium = new CompetitionCollegium(this,
                Keys.COMPETITION_COLLEGIUM_REFEREE_TRANS__COMPETITION_COLLEGIUM_REFEREE_TRANS_COMPETITION_COLLEGIUM_ID_FK);
        }

        return _competitionCollegium;
    }

    /**
     * Get the implicit join path to the <code>public.referee</code> table.
     */
    public Referee referee() {
        if (_referee == null) {
            _referee = new Referee(this,
                Keys.COMPETITION_COLLEGIUM_REFEREE_TRANS__COMPETITION_COLLEGIUM_REFEREE_TRANS_REFEREE_ID_FK);
        }

        return _referee;
    }

    @Override
    public CompetitionCollegiumRefereeTrans as(String alias) {
        return new CompetitionCollegiumRefereeTrans(DSL.name(alias), this);
    }

    @Override
    public CompetitionCollegiumRefereeTrans as(Name alias) {
        return new CompetitionCollegiumRefereeTrans(alias, this);
    }

    @Override
    public CompetitionCollegiumRefereeTrans as(Table<?> alias) {
        return new CompetitionCollegiumRefereeTrans(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionCollegiumRefereeTrans rename(String name) {
        return new CompetitionCollegiumRefereeTrans(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionCollegiumRefereeTrans rename(Name name) {
        return new CompetitionCollegiumRefereeTrans(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionCollegiumRefereeTrans rename(Table<?> name) {
        return new CompetitionCollegiumRefereeTrans(name.getQualifiedName(), null);
    }
}
