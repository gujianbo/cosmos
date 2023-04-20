package com.jianbogu.cosmos.application.operator.search;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.jianbogu.cosmos.application.collection.WOSDTO;
import com.jianbogu.cosmos.application.context.WOSRequestContext;
import com.jianbogu.cosmos.application.utils.BeanUtils;
import com.jianbogu.cosmos.core.collection.BaseDTO;
import com.jianbogu.cosmos.core.context.RequestContext;
import com.jianbogu.cosmos.core.operator.RecallOperator;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.common.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WOSSearchOperator extends RecallOperator {
    Logger logger = LoggerFactory.getLogger(getClass());
//    private RestHighLevelClient restHighLevelClient = BeanUtils.getBean(RestHighLevelClient.class);
    @Override
    public List<BaseDTO> doRecall(RequestContext context, List<BaseDTO> items) throws IOException {
        RestHighLevelClient restHighLevelClient = BeanUtils.getBean(RestHighLevelClient.class);
        WOSRequestContext wosRequestContext = (WOSRequestContext)context;
        SearchSourceBuilder builder = new SearchSourceBuilder();
        QueryStringQueryBuilder queryString = QueryBuilders.queryStringQuery(wosRequestContext.getQuery());
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        builder.query(queryString);
        builder.highlighter(highlightBuilder);
        builder.from(0);
        builder.size(wosRequestContext.getPageSize());

        SearchRequest searchRequest = new SearchRequest("doc");
        searchRequest.source(builder);
        logger.info("ES Query：" + builder.toString());
        logger.info("restHighLevelClient is null：" + (restHighLevelClient==null));
        SearchResponse searchResponse = null;

        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        }catch (IOException e) {
            logger.info(e.toString());
            return new ArrayList<>();
        }

        List<BaseDTO> items_new = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
//            logger.info(hit.toString());
            WOSDTO wosdto = new WOSDTO();

            Map<String, Object> sourceMap = hit.getSourceAsMap();
            List<String> authors = (List<String>) sourceMap.get("authors");
            wosdto.setAuthors(authors);
            List<String> categories = (List<String>) sourceMap.get("categories");
            wosdto.setCategories(categories);
            String title = (String)sourceMap.get("title");
            wosdto.setTitle(title);
            String abs = (String)sourceMap.get("abstract");
            wosdto.setAbs(abs);
            String id = (String)sourceMap.get("id");
            wosdto.setWOSId(id);

            long long_id = 0;
            try {
                long_id = Long.valueOf(id.split(":")[1]);
            }catch (Exception e){
                logger.error("id:" + id + " is error!");
            }
            wosdto.setId(long_id);

            double score = hit.getScore();
            wosdto.setScore(score);

            HighlightField titleHighLight = hit.getHighlightFields().get("title");
            if(titleHighLight != null) {
                for (Text fragment : titleHighLight.getFragments()) {
                    wosdto.setHighlightTitle(fragment.string());
                }
            }else{
                wosdto.setHighlightTitle(wosdto.getTitle());
            }
            items_new.add(wosdto);
        }

        return items_new;
    }
}
