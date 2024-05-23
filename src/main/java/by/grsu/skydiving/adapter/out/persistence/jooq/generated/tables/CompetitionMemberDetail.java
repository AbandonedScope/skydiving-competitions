/*
 * This file is generated by jOOQ.
 */
package generated.tables;


import generated.Keys;
import generated.Public;
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
public class CompetitionMemberDetail extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.competition_member_detail</code>
     */
    public static final CompetitionMemberDetail COMPETITION_MEMBER_DETAIL = new CompetitionMemberDetail();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>public.competition_member_detail.id</code>.
     */
    public final TableField<Record, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.competition_member_detail.skydiver_id</code>.
     */
    public final TableField<Record, Long> SKYDIVER_ID = createField(DSL.name("skydiver_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.competition_member_detail.competition_id</code>.
     */
    public final TableField<Record, Long> COMPETITION_ID = createField(DSL.name("competition_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.competition_member_detail.team_id</code>.
     */
    public final TableField<Record, Long> TEAM_ID = createField(DSL.name("team_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.competition_member_detail.member_number</code>.
     */
    public final TableField<Record, Integer> MEMBER_NUMBER = createField(DSL.name("member_number"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.competition_member_detail.is_junior</code>.
     */
    public final TableField<Record, Boolean> IS_JUNIOR = createField(DSL.name("is_junior"), SQLDataType.BOOLEAN.defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), this, "");

    private CompetitionMemberDetail(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private CompetitionMemberDetail(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.competition_member_detail</code> table
     * reference
     */
    public CompetitionMemberDetail(String alias) {
        this(DSL.name(alias), COMPETITION_MEMBER_DETAIL);
    }

    /**
     * Create an aliased <code>public.competition_member_detail</code> table
     * reference
     */
    public CompetitionMemberDetail(Name alias) {
        this(alias, COMPETITION_MEMBER_DETAIL);
    }

    /**
     * Create a <code>public.competition_member_detail</code> table reference
     */
    public CompetitionMemberDetail() {
        this(DSL.name("competition_member_detail"), null);
    }

    public <O extends Record> CompetitionMemberDetail(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, COMPETITION_MEMBER_DETAIL);
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
        return Keys.COMPETITION_MEMBER_DETAIL_PKEY;
    }

    @Override
    public CompetitionMemberDetail as(String alias) {
        return new CompetitionMemberDetail(DSL.name(alias), this);
    }

    @Override
    public CompetitionMemberDetail as(Name alias) {
        return new CompetitionMemberDetail(alias, this);
    }

    @Override
    public CompetitionMemberDetail as(Table<?> alias) {
        return new CompetitionMemberDetail(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionMemberDetail rename(String name) {
        return new CompetitionMemberDetail(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionMemberDetail rename(Name name) {
        return new CompetitionMemberDetail(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public CompetitionMemberDetail rename(Table<?> name) {
        return new CompetitionMemberDetail(name.getQualifiedName(), null);
    }
}
