package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.application.domain.model.pivot.CompetitionPivotTable;
import by.grsu.skydiving.application.port.in.GetCompetitionPivotTableUseCase;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("api/v1/pivot-table")
@RequiredArgsConstructor
public class PivotTableController {
    private final GetCompetitionPivotTableUseCase getCompetitionPivotTableUseCase;

    @GetMapping("/{competitionId}")
    public CompetitionPivotTable getPivotTable(@PathVariable
                                               long competitionId) {
        return getCompetitionPivotTableUseCase.getTable(competitionId);
    }
}
