/*
 * This file is generated by jOOQ.
 */
package generated;


import generated.tables.Competition;
import generated.tables.CompetitionMemberDetail;
import generated.tables.CompetitionStage;
import generated.tables.CompetitionStageRefereeTrans;
import generated.tables.Jumping;
import generated.tables.PassportInfo;
import generated.tables.Referee;
import generated.tables.Skydiver;
import generated.tables.Team;
import generated.tables.UserInfo;

import org.jooq.ForeignKey;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<Record> COMPETITION_PKEY = Internal.createUniqueKey(Competition.COMPETITION, DSL.name("competition_pkey"), new TableField[] { Competition.COMPETITION.ID }, true);
    public static final UniqueKey<Record> COMPETITION_MEMBER_DETAIL_PKEY = Internal.createUniqueKey(CompetitionMemberDetail.COMPETITION_MEMBER_DETAIL, DSL.name("competition_member_detail_pkey"), new TableField[] { CompetitionMemberDetail.COMPETITION_MEMBER_DETAIL.ID }, true);
    public static final UniqueKey<Record> COMPETITION_STAGE_PKEY = Internal.createUniqueKey(CompetitionStage.COMPETITION_STAGE, DSL.name("competition_stage_pkey"), new TableField[] { CompetitionStage.COMPETITION_STAGE.ID }, true);
    public static final UniqueKey<Record> COMPETITION_STAGE_REFEREE_TRANS_PKEY = Internal.createUniqueKey(CompetitionStageRefereeTrans.COMPETITION_STAGE_REFEREE_TRANS, DSL.name("competition_stage_referee_trans_pkey"), new TableField[] { CompetitionStageRefereeTrans.COMPETITION_STAGE_REFEREE_TRANS.ID }, true);
    public static final UniqueKey<Record> JUMPING_PKEY = Internal.createUniqueKey(Jumping.JUMPING, DSL.name("jumping_pkey"), new TableField[] { Jumping.JUMPING.ID }, true);
    public static final UniqueKey<Record> PASSPORT_INFO_PKEY = Internal.createUniqueKey(PassportInfo.PASSPORT_INFO, DSL.name("passport_info_pkey"), new TableField[] { PassportInfo.PASSPORT_INFO.ID }, true);
    public static final UniqueKey<Record> REFEREE_PKEY = Internal.createUniqueKey(Referee.REFEREE, DSL.name("referee_pkey"), new TableField[] { Referee.REFEREE.ID }, true);
    public static final UniqueKey<Record> SKYDIVER_PKEY = Internal.createUniqueKey(Skydiver.SKYDIVER, DSL.name("skydiver_pkey"), new TableField[] { Skydiver.SKYDIVER.ID }, true);
    public static final UniqueKey<Record> TEAM_NAME_KEY = Internal.createUniqueKey(Team.TEAM, DSL.name("team_name_key"), new TableField[] { Team.TEAM.NAME }, true);
    public static final UniqueKey<Record> TEAM_PKEY = Internal.createUniqueKey(Team.TEAM, DSL.name("team_pkey"), new TableField[] { Team.TEAM.ID }, true);
    public static final UniqueKey<Record> USER_INFO_PKEY = Internal.createUniqueKey(UserInfo.USER_INFO, DSL.name("user_info_pkey"), new TableField[] { UserInfo.USER_INFO.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<Record, Record> COMPETITION_STAGE__COMPETITION_STAGE_COMPETITION_ID_FKEY = Internal.createForeignKey(CompetitionStage.COMPETITION_STAGE, DSL.name("competition_stage_competition_id_fkey"), new TableField[] { CompetitionStage.COMPETITION_STAGE.COMPETITION_ID }, Keys.COMPETITION_PKEY, new TableField[] { Competition.COMPETITION.ID }, true);
    public static final ForeignKey<Record, Record> COMPETITION_STAGE_REFEREE_TRANS__COMPETITION_STAGE_REFEREE_TRANS_COMPETITION_STAGE_ID_FKEY = Internal.createForeignKey(CompetitionStageRefereeTrans.COMPETITION_STAGE_REFEREE_TRANS, DSL.name("competition_stage_referee_trans_competition_stage_id_fkey"), new TableField[] { CompetitionStageRefereeTrans.COMPETITION_STAGE_REFEREE_TRANS.COMPETITION_STAGE_ID }, Keys.COMPETITION_STAGE_PKEY, new TableField[] { CompetitionStage.COMPETITION_STAGE.ID }, true);
    public static final ForeignKey<Record, Record> COMPETITION_STAGE_REFEREE_TRANS__COMPETITION_STAGE_REFEREE_TRANS_REFEREE_ID_FKEY = Internal.createForeignKey(CompetitionStageRefereeTrans.COMPETITION_STAGE_REFEREE_TRANS, DSL.name("competition_stage_referee_trans_referee_id_fkey"), new TableField[] { CompetitionStageRefereeTrans.COMPETITION_STAGE_REFEREE_TRANS.REFEREE_ID }, Keys.REFEREE_PKEY, new TableField[] { Referee.REFEREE.ID }, true);
    public static final ForeignKey<Record, Record> JUMPING__JUMPING_COMPETITION_MEMBER_DETAIL_ID_FKEY = Internal.createForeignKey(Jumping.JUMPING, DSL.name("jumping_competition_member_detail_id_fkey"), new TableField[] { Jumping.JUMPING.COMPETITION_MEMBER_DETAIL_ID }, Keys.COMPETITION_MEMBER_DETAIL_PKEY, new TableField[] { CompetitionMemberDetail.COMPETITION_MEMBER_DETAIL.ID }, true);
    public static final ForeignKey<Record, Record> JUMPING__JUMPING_REFEREE_ID_FKEY = Internal.createForeignKey(Jumping.JUMPING, DSL.name("jumping_referee_id_fkey"), new TableField[] { Jumping.JUMPING.REFEREE_ID }, Keys.REFEREE_PKEY, new TableField[] { Referee.REFEREE.ID }, true);
}
