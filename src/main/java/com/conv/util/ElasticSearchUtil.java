package com.conv.util;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ElasticSearchUtil {

    private static final String INDEXNAME  = "convinent";
    private static final String TYPE = "bookmark";

    public synchronized  static TransportClient client() throws UnknownHostException {
        Settings setting = Settings.builder()
                .put("cluster.name","myes")
                .put("client.transport.sniff", true)
                .build();
        InetSocketTransportAddress master = null;
        master = new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9300);
        TransportClient client = new PreBuiltTransportClient(setting).addTransportAddress(master);
        return client;
    }

    public static void closeClient(Client client){
        if(client !=null){
            client.close();
        }
    }

    public static String addWeb(String webAddress,String webName,String userId) throws UnknownHostException{
        if(webAddress!=null&&webName!=null){
            List<String> list = queryByNmae(userId,webName);
            if(list.size()<=1) {
                try {
                    XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject()
                            .field("websiteAddress", webAddress)
                            .field("websiteName", webName)
                            .field("userId", userId)
                            .endObject();
                    IndexResponse response = ElasticSearchUtil.client().prepareIndex(INDEXNAME, TYPE)
                            .setSource(contentBuilder).get();
                    return "success";
                } catch (IOException e) {
                    e.printStackTrace();
                    return "error";
                }
            }else
                return "hasWebName";
        }else
            return "notNull";
    }

    public static HashMap<String,String> queryWeb(String userId) throws UnknownHostException{
        HashMap<String,String> map = new HashMap<>();
        if(userId.isEmpty()){
            map.put("error","error");
            return map;
        }
        QueryBuilder query = QueryBuilders.termQuery("userId", userId);
        SearchResponse result = ElasticSearchUtil.client().prepareSearch(INDEXNAME).setTypes(TYPE).setQuery(query).execute().actionGet();
        SearchHits hits = result.getHits();
        if(hits.getTotalHits()>0){
            for(SearchHit hit : hits){
                map.put(hit.getId(),hit.getSourceAsString());
            }
        }
        return map;
    }

    public static String deleteWeb(String id) throws UnknownHostException{
        DeleteResponse response = ElasticSearchUtil.client().prepareDelete("convinent","bookmark",id).get();
        if(response.getResult().toString().equals("DELETED")){
            return "success";
        }
        return "fail";
    }

    public static List<String> queryByNmae(String userId, String webSiteName) throws UnknownHostException{
        List<String> list = new ArrayList<>();
        if(webSiteName.isEmpty()||userId.isEmpty()){
            return null;
        }
        MatchPhraseQueryBuilder matchPhraseQueryBuilder1 = QueryBuilders.matchPhraseQuery("websiteName",webSiteName);
        MatchPhraseQueryBuilder matchPhraseQueryBuilder2 = QueryBuilders.matchPhraseQuery("userId",userId);
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(matchPhraseQueryBuilder1).must(matchPhraseQueryBuilder2);
        SearchResponse response = ElasticSearchUtil.client().prepareSearch(INDEXNAME)
                .setTypes(TYPE).setQuery(queryBuilder).execute().actionGet();
        SearchHits hits = response.getHits();
        if(hits.totalHits > 0) {
            list.add("success");
            for (SearchHit hit : hits) {
                list.add(hit.getSourceAsString());
            }
        }else
            list.add("notHave");
        return list;
    }
}
