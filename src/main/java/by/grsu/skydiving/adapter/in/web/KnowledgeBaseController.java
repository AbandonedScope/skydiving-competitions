package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.common.WebAdapter;
import by.grsu.skydiving.common.knowledge.EnumModel;
import by.grsu.skydiving.common.knowledge.KnowledgeBaseUtils;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("api/v1/knowledge-base")
@RequiredArgsConstructor
public class KnowledgeBaseController {

    @GetMapping
    public Map<String, List<EnumModel>> getKnowledgeBase() {
        return KnowledgeBaseUtils.getEnums();
    }
}
