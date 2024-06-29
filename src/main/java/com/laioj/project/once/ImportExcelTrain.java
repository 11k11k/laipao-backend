package com.laioj.project.once;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ImportExcelTrain {
    public static void main(String[] args) {
        String fileName = "D:\\springcloudTest\\laipao-backend-focus\\src\\main\\resources\\excel\\demo.xlsx";
        List<XingQiuUserInfo> userInfoList = EasyExcel.read(fileName).head(XingQiuUserInfo.class).sheet().doReadSync();
        System.out.println("总数 = " + userInfoList.size());
//      使用hashMap存储，key为username，value为用户名
        Map<String, List<XingQiuUserInfo>> listMap = userInfoList.stream()
//                过滤掉为空的用户名
                .filter(userInfo -> StringUtils.isNotEmpty(userInfo.getUsername()))
//                按照username进行分组，也就是把username作为键
                .collect(Collectors.groupingBy(XingQiuUserInfo::getUsername));
        //Map.Entry<K, V> 是一个包含键和值的简单接口。常用的方法有：
        //K getKey(): 获取条目的键。
        //V getValue(): 获取条目的值。
        //V setValue(V value): 更新条目的值（可选操作）。
        for (Map.Entry<String, List<XingQiuUserInfo>> stringListEntry : listMap.entrySet()) {
            if (!stringListEntry.getValue().isEmpty()) {
                System.out.println("username= " + stringListEntry.getKey());
                System.out.println("1");
            }
        }

    }
}
