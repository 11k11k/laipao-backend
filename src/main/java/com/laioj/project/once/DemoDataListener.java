//package com.laioj.project.once;
//
//import cn.hutool.json.JSONUtil;
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.read.listener.ReadListener;
//import com.alibaba.excel.util.ListUtils;
//import com.alibaba.excel.util.StringUtils;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.json.Json;
//import java.util.List;
//
//import static cn.hutool.http.ContentType.JSON;
//@Deprecated
//// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
//@Slf4j
//public class DemoDataListener implements ReadListener<XingQiuUserInfo> {
//
//    /**
//     * 这个每一条数据解析都会来调用
//     *
//     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
//     * @param context
//     */
//    @Override
//    public void invoke(XingQiuUserInfo data, AnalysisContext context) {
//        log.info("解析到一条数据:{}", JSONUtil.toJsonStr(data));
//
//    }
//
//    /**
//     * 所有数据解析完成了 都会来调用
//     *
//     * @param context
//     */
//    @Override
//    public void doAfterAllAnalysed(AnalysisContext context) {
//        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
//        log.info(context.toString());
//        log.info("所有数据解析完成！");
//    }
//
//
//}