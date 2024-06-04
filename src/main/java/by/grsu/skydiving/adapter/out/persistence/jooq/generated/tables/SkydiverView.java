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
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class SkydiverView extends TableImpl<Record> {

    /**
     * The reference instance of <code>public.skydiver_view</code>
     */
    public static final SkydiverView SKYDIVER_VIEW = new SkydiverView();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>public.skydiver_view.id</code>.
     */
    public final TableField<Record, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT, this, "");
    /**
     * The column <code>public.skydiver_view.gender</code>.
     */
    public final TableField<Record, Integer> GENDER = createField(DSL.name("gender"), SQLDataType.INTEGER, this, "");
    /**
     * The column <code>public.skydiver_view.birth_date</code>.
     */
    public final TableField<Record, LocalDateTime> BIRTH_DATE =
        createField(DSL.name("birth_date"), SQLDataType.LOCALDATETIME(6), this, "");
    /**
     * The column <code>public.skydiver_view.place_of_birth</code>.
     */
    public final TableField<Record, String> PLACE_OF_BIRTH =
        createField(DSL.name("place_of_birth"), SQLDataType.VARCHAR(40), this, "");
    /**
     * The column <code>public.skydiver_view.place_of_work</code>.
     */
    public final TableField<Record, String> PLACE_OF_WORK =
        createField(DSL.name("place_of_work"), SQLDataType.VARCHAR(40), this, "");
    /**
     * The column <code>public.skydiver_view.education</code>.
     */
    public final TableField<Record, String> EDUCATION =
        createField(DSL.name("education"), SQLDataType.VARCHAR(40), this, "");
    /**
     * The column <code>public.skydiver_view.phone_number</code>.
     */
    public final TableField<Record, String> PHONE_NUMBER =
        createField(DSL.name("phone_number"), SQLDataType.VARCHAR(20), this, "");
    /**
     * The column <code>public.skydiver_view.couch_name</code>.
     */
    public final TableField<Record, String> COUCH_NAME =
        createField(DSL.name("couch_name"), SQLDataType.VARCHAR(100), this, "");
    /**
     * The column <code>public.skydiver_view.height</code>.
     */
    public final TableField<Record, Double> HEIGHT = createField(DSL.name("height"), SQLDataType.DOUBLE, this, "");
    /**
     * The column <code>public.skydiver_view.weight</code>.
     */
    public final TableField<Record, Double> WEIGHT = createField(DSL.name("weight"), SQLDataType.DOUBLE, this, "");
    /**
     * The column <code>public.skydiver_view.shoe_size</code>.
     */
    public final TableField<Record, Integer> SHOE_SIZE =
        createField(DSL.name("shoe_size"), SQLDataType.INTEGER, this, "");
    /**
     * The column <code>public.skydiver_view.jacket_size</code>.
     */
    public final TableField<Record, Integer> JACKET_SIZE =
        createField(DSL.name("jacket_size"), SQLDataType.INTEGER, this, "");
    /**
     * The column <code>public.skydiver_view.pants_size</code>.
     */
    public final TableField<Record, Integer> PANTS_SIZE =
        createField(DSL.name("pants_size"), SQLDataType.INTEGER, this, "");
    /**
     * The column <code>public.skydiver_view.begin_of_sport_career</code>.
     */
    public final TableField<Record, LocalDateTime> BEGIN_OF_SPORT_CAREER =
        createField(DSL.name("begin_of_sport_career"), SQLDataType.LOCALDATETIME(6), this, "");
    /**
     * The column <code>public.skydiver_view.sport_specialization</code>.
     */
    public final TableField<Record, String> SPORT_SPECIALIZATION =
        createField(DSL.name("sport_specialization"), SQLDataType.VARCHAR(50), this, "");
    /**
     * The column <code>public.skydiver_view.sport_degree</code>.
     */
    public final TableField<Record, Integer> SPORT_DEGREE =
        createField(DSL.name("sport_degree"), SQLDataType.INTEGER, this, "");
    /**
     * The column <code>public.skydiver_view.jumping_amount</code>.
     */
    public final TableField<Record, Integer> JUMPING_AMOUNT =
        createField(DSL.name("jumping_amount"), SQLDataType.INTEGER, this, "");
    /**
     * The column <code>public.skydiver_view.is_internal</code>.
     */
    public final TableField<Record, Boolean> IS_INTERNAL =
        createField(DSL.name("is_internal"), SQLDataType.BOOLEAN, this, "");

    private SkydiverView(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SkydiverView(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.view("""
            create view "skydiver_view" as  SELECT skydiver.id,
              skydiver.gender,
              skydiver.birth_date,
              skydiver.place_of_birth,
              skydiver.place_of_work,
              skydiver.education,
              skydiver.phone_number,
              skydiver.couch_name,
              skydiver.height,
              skydiver.weight,
              skydiver.shoe_size,
              skydiver.jacket_size,
              skydiver.pants_size,
              skydiver.begin_of_sport_career,
              skydiver.sport_specialization,
              skydiver.sport_degree,
              skydiver.jumping_amount,
              skydiver.is_internal
             FROM skydiver
            WHERE (skydiver.is_deleted IS FALSE);
            """));
    }

    /**
     * Create an aliased <code>public.skydiver_view</code> table reference
     */
    public SkydiverView(String alias) {
        this(DSL.name(alias), SKYDIVER_VIEW);
    }

    /**
     * Create an aliased <code>public.skydiver_view</code> table reference
     */
    public SkydiverView(Name alias) {
        this(alias, SKYDIVER_VIEW);
    }

    /**
     * Create a <code>public.skydiver_view</code> table reference
     */
    public SkydiverView() {
        this(DSL.name("skydiver_view"), null);
    }

    public <O extends Record> SkydiverView(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SKYDIVER_VIEW);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public SkydiverView as(String alias) {
        return new SkydiverView(DSL.name(alias), this);
    }

    @Override
    public SkydiverView as(Name alias) {
        return new SkydiverView(alias, this);
    }

    @Override
    public SkydiverView as(Table<?> alias) {
        return new SkydiverView(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SkydiverView rename(String name) {
        return new SkydiverView(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SkydiverView rename(Name name) {
        return new SkydiverView(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SkydiverView rename(Table<?> name) {
        return new SkydiverView(name.getQualifiedName(), null);
    }
}
