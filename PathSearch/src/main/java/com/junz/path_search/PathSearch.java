package com.junz.path_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 无向图路径搜索类
 * @author junz
 * @see 1.0
 */
public class PathSearch {
    //存储路径点位的数据结构，key是点位，value是与该点位直接相连的点位列表
    private HashMap<String, List<String>> points;

    public PathSearch() {
        //初始化存储路径点位的数据结构的实例
        points = new HashMap<>();
    }

    /**
     * 添加路径，实际上添加的是路径对应的两个点位
     * @param pLeft 路径的左端点位名称
     * @param pRight 路径的右端点位名称
     */
    public void addPath(String pLeft,String pRight){
        //先以左端为key，存储对应相连的一个点位
        List<String> pointAdjoins = points.get(pLeft);
        if (pointAdjoins == null) {
            pointAdjoins = new ArrayList<String>();
            points.put(pLeft, pointAdjoins);
        }
        pointAdjoins.add(pRight);

        //先以左右为key，存储对应相连的一个点位
        pointAdjoins = points.get(pRight);
        if (pointAdjoins == null) {
            pointAdjoins = new ArrayList<String>();
            points.put(pRight, pointAdjoins);
        }
        pointAdjoins.add(pLeft);
    }

    /**
     * 搜索某两个点位的所有连通路径
     * @param start 起始点位
     * @param end 结束点位
     * @return 两个点位连通路径的集合，连通路径也是一个集合，存储的是路径上经过的点位的集合
     */
    public List<List<String>> searchAllPaths(String start, String end) {
        List<List<String>> routes = new ArrayList<>();
        List<String> rights = points.get(start);
        if (rights != null) {
            for (String right : rights) {
                List<String> route = new ArrayList<>();
                route.add(start);
                route.add(right);
                chain(routes, route, right, end);
            }
        }
        return routes;
    }

    /**
     * 递归计算从某一个点位开始到结束点位之间途径的所有点位集合
     * @param routes 两个点位之间路径的所有点位集合
     * @param route 当前路径，包含路径的两个点位
     * @param right_most_currently 路径的右端点位
     * @param end 结束点位
     */
    public void chain(List<List<String>> routes, List<String> route, String right_most_currently, String end) {
        //如果路径的右端点位等于结束点位，则本条路径搜索结束
        if (right_most_currently.equals(end)) {
            routes.add(route);
            return;
        }
        //获取与路径右端点位相邻的点位集合
        List<String> rights = points.get(right_most_currently);

        if (rights != null) {
            for (String right : rights) {
                //为了防止重复路径，则排除掉当前路径中已存在的点位
                if (!route.contains(right)) {
                    //把当前相邻的点位作为路径的右端点位，继续搜索相邻的点位
                    List<String> new_route = new ArrayList<String>(route);
                    new_route.add(right);
                    chain(routes, new_route, right, end);
                }
            }
        }
    }
}
