/*
 * This file is generated by jOOQ.
 */
package generated;


import generated.tables.Competition;
import generated.tables.CompetitionMemberDetail;
import generated.tables.CompetitionStage;
import generated.tables.CompetitionStageRefereeTrans;
import generated.tables.CompetitionView;
import generated.tables.Jumping;
import generated.tables.PassportInfo;
import generated.tables.Referee;
import generated.tables.RefereeView;
import generated.tables.Skydiver;
import generated.tables.SkydiverView;
import generated.tables.Team;
import generated.tables.TeamView;
import generated.tables.UserInfo;
import generated.tables.UserInfoView;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.competition</code>.
     */
    public final Competition COMPETITION = Competition.COMPETITION;

    /**
     * The table <code>public.competition_member_detail</code>.
     */
    public final CompetitionMemberDetail COMPETITION_MEMBER_DETAIL = CompetitionMemberDetail.COMPETITION_MEMBER_DETAIL;

    /**
     * The table <code>public.competition_stage</code>.
     */
    public final CompetitionStage COMPETITION_STAGE = CompetitionStage.COMPETITION_STAGE;

    /**
     * The table <code>public.competition_stage_referee_trans</code>.
     */
    public final CompetitionStageRefereeTrans COMPETITION_STAGE_REFEREE_TRANS = CompetitionStageRefereeTrans.COMPETITION_STAGE_REFEREE_TRANS;

    /**
     * The table <code>public.competition_view</code>.
     */
    public final CompetitionView COMPETITION_VIEW = CompetitionView.COMPETITION_VIEW;

    /**
     * The table <code>public.jumping</code>.
     */
    public final Jumping JUMPING = Jumping.JUMPING;

    /**
     * The table <code>public.passport_info</code>.
     */
    public final PassportInfo PASSPORT_INFO = PassportInfo.PASSPORT_INFO;

    /**
     * The table <code>public.referee</code>.
     */
    public final Referee REFEREE = Referee.REFEREE;

    /**
     * The table <code>public.referee_view</code>.
     */
    public final RefereeView REFEREE_VIEW = RefereeView.REFEREE_VIEW;

    /**
     * The table <code>public.skydiver</code>.
     */
    public final Skydiver SKYDIVER = Skydiver.SKYDIVER;

    /**
     * The table <code>public.skydiver_view</code>.
     */
    public final SkydiverView SKYDIVER_VIEW = SkydiverView.SKYDIVER_VIEW;

    /**
     * The table <code>public.team</code>.
     */
    public final Team TEAM = Team.TEAM;

    /**
     * The table <code>public.team_view</code>.
     */
    public final TeamView TEAM_VIEW = TeamView.TEAM_VIEW;

    /**
     * The table <code>public.user_info</code>.
     */
    public final UserInfo USER_INFO = UserInfo.USER_INFO;

    /**
     * The table <code>public.user_info_view</code>.
     */
    public final UserInfoView USER_INFO_VIEW = UserInfoView.USER_INFO_VIEW;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Competition.COMPETITION,
            CompetitionMemberDetail.COMPETITION_MEMBER_DETAIL,
            CompetitionStage.COMPETITION_STAGE,
            CompetitionStageRefereeTrans.COMPETITION_STAGE_REFEREE_TRANS,
            CompetitionView.COMPETITION_VIEW,
            Jumping.JUMPING,
            PassportInfo.PASSPORT_INFO,
            Referee.REFEREE,
            RefereeView.REFEREE_VIEW,
            Skydiver.SKYDIVER,
            SkydiverView.SKYDIVER_VIEW,
            Team.TEAM,
            TeamView.TEAM_VIEW,
            UserInfo.USER_INFO,
            UserInfoView.USER_INFO_VIEW
        );
    }
}
