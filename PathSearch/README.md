# 无向图路径搜索类
调用示例：
``` Java
PathSearch pathSearch = new PathSearch();
pathSearch.addPath("a","b");
pathSearch.addPath("b","c");
pathSearch.addPath("c","e");
pathSearch.addPath("a","d");
pathSearch.addPath("d","e");

//搜索从e到a点的所有路径
List<List<String>> routes = pathSearch.searchAllPaths("e","a");
for (List<String> route : routes) {
    System.out.println(route);
}
```

运行结果：
```
[e, c, b, a]
[e, d, a]
```