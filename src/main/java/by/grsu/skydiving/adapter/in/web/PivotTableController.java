package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("api/v1/pivot-table")
@RequiredArgsConstructor
public class PivotTableController {
}
