//package com.laioj.project.once;
//
//import cn.hutool.json.JSONUtil;
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.read.listener.PageReadListener;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.File;
//import java.util.List;
//
//@Slf4j
//public class  ImportExcel {
//
//    public static void main(String[] args) {
//        // todo 记得改为自己的测试文件
//        String fileName = "D:\\springcloudTest\\laipao-backend-focus\\src\\main\\resources\\excel\\demo.xlsx";
////        readByListener(fileName);
//        synchronousRead(fileName);
//    }
//
//    // 写法1：JDK8+ ,不用额外写一个DemoDataListener
//    // since: 3.0.0-beta1
//    public static void readByListener(String fileName){
//
//        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
//        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
//        EasyExcel.read(fileName, XingQiuUserInfo.class,
//                new DemoDataListener()).sheet().doRead();
//    }
////    同步读
//    public static void synchronousRead(String fileName){
//        List<Object> totalDataList =
//                EasyExcel.read(fileName).head(XingQiuUserInfo.class).sheet().doReadSync();
//        totalDataList.forEach(System.out::println);
//
//    }
//
//
//}